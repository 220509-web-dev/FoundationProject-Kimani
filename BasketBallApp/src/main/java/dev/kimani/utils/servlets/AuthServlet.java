package dev.kimani.utils.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kimani.utils.daos.BasketballDaoPostgres;
import dev.kimani.utils.entities.Player;
import dev.kimani.utils.services.AuthService;
import dev.kimani.utils.util.exceptions.InvalidCredentialException;
import dev.kimani.utils.util.exceptions.UsernameNotAvailableException;
import org.postgresql.util.PSQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AuthServlet extends HttpServlet {
    ObjectMapper mapper = new ObjectMapper();
    AuthService authService = new AuthService();
    BasketballDaoPostgres basketballDaoPostgres = new BasketballDaoPostgres();



    public AuthServlet( ObjectMapper mapper, AuthService authService) {
        this.authService = authService;
        this.mapper = mapper;
    }

    public AuthServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - AuthServlet received a POST request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request method: " + req.getMethod());

        String uri = req.getRequestURI().replace("/basketball/auth/", "");

        if (uri.equals("register")) {

            try {
//
                Player newPlayer = mapper.readValue(req.getInputStream(), Player.class);
                HttpSession session = req.getSession();
                session.setAttribute("auth-player", newPlayer);
                authService.registration(newPlayer);

                PrintWriter writer = resp.getWriter();
                String respPayload = mapper.writeValueAsString(newPlayer);
                resp.setContentType("application/json");
                resp.getWriter().write(respPayload);
                writer.println("User registered successfully");

                resp.setStatus(204);
            } catch (UsernameNotAvailableException e) {

                e.printStackTrace();

                resp.setStatus(400);
                PrintWriter writer = resp.getWriter();
                writer.println("Username is already taken");
            } catch (PSQLException e) {
                e.printStackTrace();
            }
        } else if (uri.equals("login")) {

            try {
                Player newPlayer = mapper.readValue(req.getInputStream(), Player.class);
                HttpSession session = req.getSession();
                session.setAttribute("auth-player", basketballDaoPostgres.getPlayerByUsername(newPlayer.getUsername()));
                authService.Login(newPlayer.getUsername(), newPlayer.getPassword());
                PrintWriter writer = resp.getWriter();
                String respPayload = mapper.writeValueAsString(newPlayer);
                resp.setContentType("application/json");
                writer.write(respPayload);
                writer.println("User successfully logged in!");
                resp.setStatus(204);

            } catch (InvalidCredentialException e) {
                e.printStackTrace();
                resp.setStatus(400);
                PrintWriter writer = resp.getWriter();
                writer.println("Invalid username or password. Please try again.");
            }


        }


    }

}
