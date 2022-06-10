package dev.kimani.utils.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kimani.utils.daos.BasketballDaoPostgres;
import dev.kimani.utils.entities.Player;
import dev.kimani.utils.services.AuthService;
import dev.kimani.utils.util.exceptions.UsernameNotAvailableException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    ObjectMapper mapper = new ObjectMapper();
    AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        BasketballDaoPostgres basketballDaoPostgres = new BasketballDaoPostgres();
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String body = stringBuilder.toString();

        Map<String,String[]> map = req.getParameterMap();
        int counter = 0;
        for (String key : map.keySet()) {
            String[] value = map.get(key);


            if (key.equals("id")) {
                try {

                    int idNumber = Integer.parseInt(value[counter]);
                    Player player = basketballDaoPostgres.getPlayerById(idNumber);
                    String respPayload = mapper.writeValueAsString(player);
                    resp.setContentType("application/json");
                    resp.getWriter().write(respPayload);
                    resp.setStatus(201);


                } catch (UsernameNotAvailableException e) {

                    e.printStackTrace();

                    PrintWriter writer = resp.getWriter();
                    writer.println("No player by id");
                }


            } else if (key.equals("username")) {

                try {

                    Player player = basketballDaoPostgres.getPlayerByUsername(value[counter]);
                    String respPayload = mapper.writeValueAsString(player);
                    resp.setContentType("application/json");
                    resp.getWriter().write(respPayload);

                } catch (UsernameNotAvailableException e) {
                    e.printStackTrace();

                    PrintWriter writer = resp.getWriter();
                    writer.println("No player by username");
                }
            } else if (key.equals("roster")) {
                List<Player> players = basketballDaoPostgres.getAllPlayers();
                String respPayload = mapper.writeValueAsString(players);
                resp.setContentType("application/json");
                resp.getWriter().write(respPayload);
            } else {
                resp.setStatus(404);
            }


        }

        counter++;
    }
}
