package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess.ClientDAO;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Client;

import java.util.List;

public class ClientBLL {

    private ClientDAO clientDAO;

    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public Client getClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new IllegalStateException("Client with ID " + id + " not found.");
        }

        return client;
    }

    public void addClient(Client client) {
        validateClient(client);
        clientDAO.insert(client);
    }

    public void updateClient(Client client) {
        validateClient(client);
        clientDAO.update(client);
    }

    public void deleteClient(int id) {
        clientDAO.delete(id);
    }

    private void validateClient(Client client) {

        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be empty.");
        }

    }


}
