package dev.kimani.tests;

import dev.kimani.utils.daos.BasketballDaoPostgres;
import dev.kimani.utils.entities.Player;
import dev.kimani.utils.util.LogLevel;
import dev.kimani.utils.util.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BasketballDaoPostgresTest {

    static BasketballDaoPostgres basketballDaoPostgres = new BasketballDaoPostgres();

    @Test
    void getPlayerById() {
        Player player = basketballDaoPostgres.getPlayerById(14);
        if(player.getFirstName().isEmpty()) {
            Logger logger = new Logger();
            logger.log("getPlayerById Test failed. No player first name found.", LogLevel.ERROR);
        } else {
            Logger logger2 = new Logger();
            logger2.log("getPlayerById Test Passed!", LogLevel.DEBUG);
        }
        Assertions.assertNotNull(player);
    }

    @Test
    void getPlayerByUsername() {
        Player player = basketballDaoPostgres.getPlayerByUsername("mjordan");
        if(player.getUsername().isEmpty()) {
            Logger logger = new Logger();
            logger.log("getPlayerByUsername Test failed. No player username found.", LogLevel.ERROR);
        } else {
            Logger logger2 = new Logger();
            logger2.log("getPlayerByUsername Test Passed!", LogLevel.DEBUG);
        }
        Assertions.assertNotNull(player);
    }

    @Test
    void getAllPlayers() {
        List<Player> players = basketballDaoPostgres.getAllPlayers();
        Assertions.assertNotNull(players);
        if(players.isEmpty()) {
            Logger logger = new Logger();
            logger.log("getAllPlayers Test failed. No player first name found.", LogLevel.ERROR);
        }  else {
            Logger logger2 = new Logger();
            logger2.log("getAllPlayers Test Passed!", LogLevel.DEBUG);
        }
    }

}

