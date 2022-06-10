package dev.kimani.utils.services;

import dev.kimani.utils.daos.BasketballDaoPostgres;
import dev.kimani.utils.entities.Player;
import dev.kimani.utils.util.ConnectionUtil;
import dev.kimani.utils.util.exceptions.InvalidCredentialException;
import dev.kimani.utils.util.exceptions.UsernameNotAvailableException;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    public BasketballDaoPostgres basketballDaoPostgres = new BasketballDaoPostgres();

    public Player Login(String username, String password) throws NullPointerException {
        Player player = basketballDaoPostgres.getPlayerByUsername(username);

        try{
            if ((player.getUsername() != null) && password.equals(player.getPassword())) {
                System.out.println("Logged in successfully!");
                System.out.println(player);
                return player;

            }else if ((player.getUsername() != null) && (!password.equals(player.getPassword()))) {
                System.out.println("Wrong password");
                throw new InvalidCredentialException("Password is incorrect.");
            } else {
                System.out.println("User does not exist!");
                throw new InvalidCredentialException("User does not exist.");
            }
        } catch(NullPointerException e){
            e.printStackTrace();
            throw new InvalidCredentialException();
        }

    }

    public void registration(Player draftPlayer) throws NullPointerException, PSQLException {

        try {
            if (basketballDaoPostgres.getAllPlayers().contains(draftPlayer.getUsername())) {

                throw new UsernameNotAvailableException();
            } else {
                basketballDaoPostgres.createPlayer(draftPlayer);
                System.out.println("Successful player registration!");
            }
        } catch (UsernameNotAvailableException e) {
            e.printStackTrace();
            throw new UsernameNotAvailableException();
        }


    }

}
