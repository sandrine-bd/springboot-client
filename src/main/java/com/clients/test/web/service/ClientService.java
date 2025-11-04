package com.clients.test.web.service;

import com.clients.test.web.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    List<Client> findAll();
    Client findById(int id);
    Client save(Client client);
    void deleteById(int id);
}
