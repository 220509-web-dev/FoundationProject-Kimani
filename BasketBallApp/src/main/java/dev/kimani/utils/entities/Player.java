package dev.kimani.utils.entities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Player {

    private int id;

    private int playerNumber;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String position;

    @Contract(pure = false)
    public Player() {
        super();
    }

    @Contract(pure = false)
    public Player( int id, int playerNumber, String firstName, String lastName, String username, String password, String position) {
        this.id = id;
        this.playerNumber = playerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public Player(String firstName, String lastName, String username, String password, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && playerNumber == player.playerNumber && firstName.equals(player.firstName) && lastName.equals(player.lastName) && username.equals(player.username) && password.equals(player.password) && position.equals(player.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerNumber, firstName, lastName, username, password, position);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerNumber=" + playerNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                "}\n";
    }
}
