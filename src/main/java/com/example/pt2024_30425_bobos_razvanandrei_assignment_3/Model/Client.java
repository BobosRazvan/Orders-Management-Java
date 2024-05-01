package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model;
/**
 * Represents a Client entity.
 */
public class Client {

    int id;
    String name;

    public Client(int id,String name){
        this.name = name;
        this.id = id;
    }
    public Client() {
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
