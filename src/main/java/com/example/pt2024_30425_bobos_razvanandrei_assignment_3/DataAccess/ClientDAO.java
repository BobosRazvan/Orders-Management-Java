package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Client;
/**
 * Data Access Object (DAO) for managing Client entities in the database.
 */
public class ClientDAO extends AbstractDAO<Client> {

    public ClientDAO() {
        super(Client.class);
    }

}
