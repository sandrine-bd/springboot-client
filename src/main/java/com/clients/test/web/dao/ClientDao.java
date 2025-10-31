package com.clients.test.web.dao;

import com.clients.test.web.model.Client;

import java.util.List;

public interface ClientDao {
    List<Client> getAllClients();
    Client getClientById(int id);
    Client addClient(Client client);
    Client updateClient(Client client);
    void deleteClient(int id);
}
