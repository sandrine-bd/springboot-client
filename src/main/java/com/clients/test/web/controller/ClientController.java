package com.clients.test.web.controller;

import com.clients.test.web.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ClientController {

    @GetMapping("/clients")
    public List<Client> afficherListeClients() {
        return null;
    }

    @GetMapping("/clients/{id}")
    public Client afficherUnClient(@PathVariable int id) {
        Client client = new Client(id, "Nom", "Prénom", LocalDate.of(1986, 12, 02), "12BG56789");
        return client;
    }
}
