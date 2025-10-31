package com.clients.test.web.controller;

import com.clients.test.web.dao.ClientDao;
import com.clients.test.web.model.Client;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "tag_at_class_level", description = "Clients related class level tag")
public class ClientController {

    private final ClientDao clientDao;

    @Autowired
    public ClientController(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @GetMapping("/")
    public String welcome() {
        return "Bienvenue sur l'API Client";
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    @Tag(name = "find")
    @Tag(name = "common_tag_at_method_level")
    @Tag(name = "findClient", description = "Find Clients related tag")
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        Client client = clientDao.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Tag(name = "create")
    @Tag(name = "common_tag_at_method_level")
    @Tag(name = "createClient")
    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client createdClient = clientDao.addClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @Tag(name = "update")
    @Tag(name = "common_tag_at_method_level")
    @Tag(name = "updateClient")
    @PutMapping("/clients")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Client updatedClient = clientDao.updateClient(client);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Tag(name = "delete")
    @Tag(name = "common_tag_at_method_level")
    @Tag(name = "deleteClient")
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        Client existing = clientDao.getClientById(id);
        if (existing != null) {
            clientDao.deleteClient(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
