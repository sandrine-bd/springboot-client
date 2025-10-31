package com.clients.test.web.controller;

import com.clients.test.web.dao.ClientDao;
import com.clients.test.web.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    private final ClientDao clientDao;

    public ClientController(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @GetMapping("/")
    public String accueillir() {
        return "Bienvenue";
    }

    @GetMapping("/clients")
    public List<Client> listerClients() {
        return clientDao.findAll();
    }

    @GetMapping("/clients/{id}")
    public Client afficherUnClient(@PathVariable int id) {
        return clientDao.findById(id);
    }
}
