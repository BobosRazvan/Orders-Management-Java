package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess.ClientDAO;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Client;

import java.util.List;

/**
 * Business logic layer for managing clients.
 */
public class ClientBLL {

    private ClientDAO clientDAO;

    /**
     * Constructor for ClientBLL class.
     */
    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * Retrieves all clients from the system.
     *
     * @return A list of all clients in the system.
     */
    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    /**
     * Retrieves a client by its ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The client with the specified ID.
     * @throws IllegalStateException if no client with the specified ID is found.
     */
    public Client getClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new IllegalStateException("Client with ID " + id + " not found.");
        }

        return client;
    }

    /**
     * Adds a new client to the system.
     *
     * @param client The client to be added.
     * @throws IllegalArgumentException if the client name is empty.
     */
    public void addClient(Client client) {
        validateClient(client);
        clientDAO.insert(client);
    }

    /**
     * Updates an existing client in the system.
     *
     * @param client The client to be updated.
     * @throws IllegalArgumentException if the client name is empty.
     */
    public void updateClient(Client client) {
        validateClient(client);
        clientDAO.update(client);
    }

    /**
     * Deletes a client from the system by its ID.
     *
     * @param id The ID of the client to be deleted.
     */
    public void deleteClient(int id) {
        clientDAO.delete(id);
    }

    /**
     * Validates a client.
     *
     * @param client The client to be validated.
     * @throws IllegalArgumentException if the client name is empty.
     */
    private void validateClient(Client client) {

        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be empty.");
        }

    }


}
