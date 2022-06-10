package dev.kimani.utils.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kimani.utils.daos.BasketballDaoPostgres;
import dev.kimani.utils.entities.Player;
import dev.kimani.utils.services.AuthService;
import dev.kimani.utils.services.PlayerService;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//@WebServlet(
//        urlPatterns = "/roster",
//        loadOnStartup = 2,
//        initParams =  {
//                @WebInitParam(name = "roster-servlet-key", value = "roster-servlet-value")
//        }
//)
public class RosterServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final BasketballDaoPostgres basketballDaoPostgres;
    private AuthService authService;


    public RosterServlet(ObjectMapper mapper, BasketballDaoPostgres basketballDaoPostgres) {
        this.mapper = mapper;
        this.basketballDaoPostgres = basketballDaoPostgres;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NullPointerException {
        List<Player> player = basketballDaoPostgres.getAllPlayers();
        String respPayload = mapper.writeValueAsString(player);
        resp.setContentType("application/json");
        resp.getWriter().write(respPayload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - IdServlet received a POST request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request method: " + req.getMethod());
        System.out.println("[LOG] - Request header, example: " + req.getHeader("example-header"));

        Map<String,String[]> map = req.getParameterMap();
        PlayerService playerService = new PlayerService();
        int counter = 0;

        for (String username : map.keySet()) {
            String[] password = map.get(username);
            Player player = authService.Login(username, password[counter]);
            counter++;
            if (player.getPassword().equals(password)){
                return; // I want to return player stats after logging in from different table
            }
        }
    }
}
