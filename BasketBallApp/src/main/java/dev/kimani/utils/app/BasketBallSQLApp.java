package dev.kimani.utils.app;

import dev.kimani.utils.daos.BasketballDaoPostgres;
import dev.kimani.utils.entities.Player;
import dev.kimani.utils.services.AuthService;

import java.sql.SQLException;
import java.util.List;

public class BasketBallSQLApp {

    public static void main(String[] args) throws SQLException {
        //Create an instance of the DAO interface
        BasketballDaoPostgres basketballDAO = new BasketballDaoPostgres();
//        basketballDAO.resetTable();

        //Get player by id
//        Player player = basketballDAO.getPlayerById(14);
//        System.out.println(player);

        //Get player by username
        Player player1 = basketballDAO.getPlayerByUsername("kdurant");
        System.out.println(player1);

//        AuthService authService = new AuthService();
//        authService.Login("mjordan", "jumpman23");

        //Create a new player ('Kobe' already created, must add new information to run)
        //Player newPlayer = new Player( 0, 1, "Kobe", "Bryant", "kbryant", "blackmamba", "SG");
        //basketballDAO.createPlayer(newPlayer);
        //Player mamba = basketballDAO.getPlayerByUsername("kbryant");
        //System.out.println(mamba);

        //Get all players
//        List<Player> allPlayers = basketballDAO.getAllPlayers();
//        System.out.println(allPlayers);



    }
}
