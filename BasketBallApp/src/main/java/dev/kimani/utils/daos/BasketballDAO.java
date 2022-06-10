package dev.kimani.utils.daos;

import dev.kimani.utils.entities.Player;

import java.util.List;

public interface BasketballDAO {

    //Create
    void createPlayer(Player player);

    //Read
    Player getPlayerById(int id);
    Player getPlayerByUsername(String username);
    List<Player> getAllPlayers();

    //Update
    void updatePlayer(Player player);





    //Delete
    void deletePlayerById(int id);

    //Testing
    void resetTable();
}
