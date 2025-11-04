package com.clients.test.web.controller;

import com.clients.test.web.exception.ClientNotFoundException;
import com.clients.test.web.model.Client;
import com.clients.test.web.service.ClientService;
import com.clients.test.web.service.LicenseValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Clients", description = "API de gestion de clients")
public class ClientController {

    private final ClientService clientService;
    private final LicenseValidationService licenseValidationService;

    @Autowired
    public ClientController(ClientService clientService, LicenseValidationService licenseValidationService) {
        this.clientService = clientService;
        this.licenseValidationService = licenseValidationService;
    }

    @GetMapping("/")
    public String welcome() {
        return "Bienvenue sur l'API Client";
    }

    @Operation(summary = "Get all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found clients",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find any clients", content = @Content)})
    @GetMapping("/clients")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @Operation(summary = "Get a client by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the client",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content) })
    @Tag(name = "find")
    @Tag(name = "common_tag_at_method_level")
    @Tag(name = "findClient", description = "Find Clients related tag")
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> findById(@Parameter(description = "id of client to be searched") @PathVariable int id) {
        Client client = clientService.findById(id);
        if (client == null) {
            throw new ClientNotFoundException("Aucun client trouvé avec l'id " + id);
        } else {
            return ResponseEntity.ok(client);
        }
    }

    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @Tag(name = "create")
    @Tag(name = "common_tag_at_method_level")
    @Tag(name = "createClient")
    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Client to create", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class),
                    examples = @ExampleObject(value = "{ \"Nom\": \"Durand\", \"Prénom\": \"Georges\", \"Date de naissance\": \"1980, 05, 24\", \"Numéro de permis\": \"D5R2H7W9A\" }")))
            @RequestBody Client client) {
        // vérifier le numéro de permis avant d'ajouter le client
        boolean isValid = licenseValidationService.isLicenseValid(client.getLicenseNumber());
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Client createdClient = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @Tag(name = "update")
    @Tag(name = "common_tag_at_method_level")
    @Tag(name = "updateClient")
    @PutMapping("/clients")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Client existing = clientService.findById(client.getId());
        if (existing == null) {
            throw new ClientNotFoundException("Impossible de modifier le client : id " + client.getId() + " introuvable");
        }
        Client updated = clientService.save(client);
        return ResponseEntity.ok(updated);
    }

    @Tag(name = "delete")
    @Tag(name = "common_tag_at_method_level")
    @Tag(name = "deleteClient")
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        Client existing = clientService.findById(id);
        if (existing == null) {
            throw new ClientNotFoundException("Impossible de supprimer le client : id " + id + " introuvable");
        }
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
