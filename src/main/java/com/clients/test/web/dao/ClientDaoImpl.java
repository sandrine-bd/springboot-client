package com.clients.test.web.dao;

import com.clients.test.web.model.Client;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    private static List<Client> clients = new ArrayList<>();

    static {
        clients.add(new Client(1, "Dupuis", "John", LocalDate.of(1986, 12, 12), "12BG56789"));
        clients.add(new Client(2, "Gretel", "Zoe", LocalDate.of(1998, 01, 24), "5L6S322EA"));
        clients.add(new Client(3, "Hello", "Toto", LocalDate.of(20113, 05, 03), "963LSD45M"));
    }

    @Override
    public List<Client> getAllClients() {
        return clients;
    }

    @Override
    public Client getClientById(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client addClient(Client client) {
        clients.add(client);
        return client;
    }

    @Override
    public Client updateClient(Client client) {
        Client existing = getClientById(client.getId());
        if (existing != null) {
            existing.setLastName(client.getLastName());
            existing.setFirstName(client.getFirstName());
            existing.setBirthDate(client.getBirthDate());
            existing.setLicenseNumber(client.getLicenseNumber());
        }
        return existing;
    }

    @Override
    public void deleteClient(int id) {
        clients.removeIf(client -> client.getId() == id);
    }
}
