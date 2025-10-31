package com.clients.test.web.dao;

import com.clients.test.web.model.Client;

import java.util.List;

public interface ClientDao {
    List<Client> findAll();
    List<Client> findById(int id);
    Client save(Client client);
}
