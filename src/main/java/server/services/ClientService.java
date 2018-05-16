package server.services;

import server.dao.ClientDAO;
import server.model.Client;

import java.util.List;

public class ClientService {


    public static void createClient(int id, String address, String email, String name, String password, String phone, String username){
        ClientDAO.save(new Client(id, address, email, name, password, phone, username));
    }

    public static void updateClient(int id, String address, String email, String name, String password, String phone, String username){
        ClientDAO.update(new Client(id, address, email, name, password, phone, username));
    }

    public static void deleteClient(int id){
        ClientDAO.delete(id);
    }

    public static Client readClient(int id){
        return ClientDAO.read(id);
    }

    public static List<Client> loadClient(){
        return ClientDAO.load();
    }

    public static Client getClient(String username){ return ClientDAO.getClient(username);}

}
