package dev.kimani.utils.daos;


import dev.kimani.utils.entities.Player;
import dev.kimani.utils.util.ConnectionUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasketballDaoPostgres implements BasketballDAO{


    @Override
    public void createPlayer(Player player) {

        // try connecting to database
        try(Connection connection = ConnectionUtil.getInstance().getConnection()) {
            String sql = "INSERT INTO players VALUES ( DEFAULT, ?, ?, ?, ?, ?, ?)";

            // set the sql code variable as the prepared statement to be passed to the database
            PreparedStatement ps = connection.prepareStatement(sql);

            Random randomJersey = new Random();
            int randomJerseyNumber = randomJersey.nextInt(36);

            // set new entry column values
            ps.setInt(1, randomJerseyNumber);
            ps.setString(2, player.getFirstName());
            ps.setString(3, player.getLastName());
            ps.setString(4, player.getUsername());
            ps.setString(5, player.getPassword());
            ps.setString(6, player.getPosition());

            // Execute the prepared statement to create new player
            ps.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }



    @Override
    public Player getPlayerById(int id) {

        try(Connection connection = ConnectionUtil.getInstance().getConnection()) {
            String sql = "SELECT * FROM players WHERE id = ?";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();

            Player player = new Player();
            player.setId(id);
            player.setPlayerNumber(rs.getInt("player_number"));
            player.setFirstName(rs.getString("first_name"));
            player.setLastName(rs.getString("last_name"));
            player.setUsername(rs.getString("username"));
            player.setPassword(rs.getString("password"));
            player.setPosition(rs.getString("position"));
            return player;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Player getPlayerByUsername(String username) {
        try(Connection connection = ConnectionUtil.getInstance().getConnection()) {
            String sql = "SELECT * FROM players WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            rs.next();

            Player player = new Player();
            player.setId(rs.getInt("id"));
            player.setPlayerNumber(rs.getInt("player_number"));
            player.setFirstName(rs.getString("first_name"));
            player.setLastName(rs.getString("last_name"));
            player.setUsername(rs.getString("username"));
            player.setPassword(rs.getString("password"));
            player.setPosition(rs.getString("position"));
            return player;

        } catch (SQLException exception) {
            System.err.println("An error occurred within BasketballDaoPostgres");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Player> getAllPlayers() {

        try(Connection connection = ConnectionUtil.getInstance().getConnection()){
            String sql = "SELECT * FROM players";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Player> players =new ArrayList<Player>();

            while (rs.next()){
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setPlayerNumber(rs.getInt("player_number"));
                player.setFirstName(rs.getString("first_name"));
                player.setLastName(rs.getString("last_name"));
                player.setUsername(rs.getString("username"));
                player.setPassword(rs.getString("password"));
                player.setPosition(rs.getString("position"));
                players.add(player);
            }

            return players;


        } catch (SQLException exception) {
            System.err.println("An error occurred within BasketballDaoPostgres");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePlayer(Player player) {

        try(Connection connection = ConnectionUtil.getInstance().getConnection()) {
            String sql = "UPDATE players SET player_number = ?, first_name = ?, last_name = ?, username = ?, password = ?,  position = ?";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, player.getPlayerNumber());
            ps.setString(2, player.getFirstName());
            ps.setString(3, player.getLastName());
            ps.setString(4, player.getUsername());
            ps.setString(5, player.getPassword());
            ps.setString(6, player.getPosition());

            ps.execute();

        } catch (SQLException | NullPointerException exception) {
            exception.printStackTrace();
        }
    }



    @Override
    public void deletePlayerById(int id) {

        try(Connection connection = ConnectionUtil.getInstance().getConnection()) {
            String sql = "delete from players where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void resetTable() {

        try(Connection connection = ConnectionUtil.getInstance().getConnection()) {


            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/basketball-mock-data.sql"));
            Statement statement = connection.createStatement();
            String line;
            while((line = reader.readLine()) != null) {
                statement.execute(line);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
