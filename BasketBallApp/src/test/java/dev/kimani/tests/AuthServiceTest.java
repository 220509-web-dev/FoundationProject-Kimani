package dev.kimani.tests;

import dev.kimani.utils.daos.BasketballDaoPostgres;
import dev.kimani.utils.entities.Player;
import dev.kimani.utils.services.AuthService;
import dev.kimani.utils.util.exceptions.InvalidCredentialException;
import dev.kimani.utils.util.exceptions.UsernameNotAvailableException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class AuthServiceTest {
//
//    static AuthService authService;
//    static BasketballDaoPostgres basketballDaoPostgres;
//
//    Player playerToRegister;
//    Player genericPlayer;
//
//    @BeforeAll
//    public static void setUpBeforeClass() {
//
//        authService = new AuthService();
//        basketballDaoPostgres = new BasketballDaoPostgres();
//
//    }
//
//
//    @BeforeEach
//    public void setUp() {
//
//
//        basketballDaoPostgres = mock(BasketballDaoPostgres.class);
//        authService.basketballDaoPostgres = basketballDaoPostgres;
//        playerToRegister = new Player("Test", "Tester", "McTester", "Testing", "T");
//        genericPlayer = new Player("Test", "Tester", "McTester", "Testing", "T");
//    }
//
//
//    @Test
//    public void testRegistrationFailsWhenUsernameIsTaken() throws NullPointerException{
//
//        when(basketballDaoPostgres.getPlayerByUsername(any(String.class))).thenReturn(genericPlayer);
//
//        assertThrows(UsernameNotAvailableException.class,
//                () -> authService.registration(playerToRegister));
//
//        verify(basketballDaoPostgres).getPlayerByUsername(genericPlayer.getUsername());
//        verify(basketballDaoPostgres, never()).createPlayer(playerToRegister);
//
//    }
//
//
//    @Test
//    public void testLoginFailsWhenPasswordIncorrect() throws NullPointerException{
//
//        when(basketballDaoPostgres.getPlayerByUsername(any(String.class))).thenReturn(genericPlayer);
//
//        assertThrows(InvalidCredentialException.class,
//                () -> authService.Login(genericPlayer.getUsername(), "wrongpassword"));
//
//
//        verify(basketballDaoPostgres).getPlayerByUsername(genericPlayer.getUsername());
//
//    }
//
//
//    @Test
//    public void testLoginFailsWhenUsernameDoesNotExist() throws NullPointerException {
//        when(basketballDaoPostgres.getPlayerByUsername(any(String.class))).thenReturn(null);
//
//        assertThrows(InvalidCredentialException.class,
//                () -> authService.Login(genericPlayer.getUsername(), genericPlayer.getPassword()));
//
//        verify(basketballDaoPostgres).getPlayerByUsername(genericPlayer.getUsername());
//
//
//    }
}
