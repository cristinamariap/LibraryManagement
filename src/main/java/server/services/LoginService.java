package server.services;

import server.dao.ClientDAO;

public class LoginService {

    private String adminUsername = "cristina";
    private String adminPassword = " ";
    public LoginService() {}

    public boolean isLogin(String user, String password, String option  ){

        if (option.equals("Admin")){
            return adminUsername.equals(user) && adminPassword.equals(password);
        } else if(option.equals("Client")){
            return ClientDAO.load().stream().anyMatch(client -> client.getUsername().equals(user) && client.getPassword().equals(password));
        }
        return false;
    }



}
