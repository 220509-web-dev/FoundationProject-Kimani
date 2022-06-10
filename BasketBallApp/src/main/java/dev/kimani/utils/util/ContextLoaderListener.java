package dev.kimani.utils.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kimani.utils.daos.BasketballDaoPostgres;
import dev.kimani.utils.filters.CustomFilter;
import dev.kimani.utils.servlets.*;

import javax.servlet.*;
import java.time.LocalDateTime;
import java.util.EnumSet;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        BasketballDaoPostgres basketballDaoPostgres = new BasketballDaoPostgres();
        PlayerServlet playerServlet = new PlayerServlet(mapper, basketballDaoPostgres);
        IdServlet idServlet = new IdServlet(mapper, basketballDaoPostgres);
        RosterServlet rosterServlet = new RosterServlet(mapper, basketballDaoPostgres);
        SanityServlet sanityServlet = new SanityServlet();
        UserServlet userServlet = new UserServlet();
        AuthServlet authServlet = new AuthServlet(mapper);

        ServletContext context = sce.getServletContext();

//        CustomFilter customFilter = new CustomFilter();
//        context.addFilter("CustomFilter", customFilter).addMappingForUrlPatterns(EnumSet.of(DispatcherType.INCLUDE), true, "/*");

        context.addServlet("SanityServlet", sanityServlet).addMapping("/sanity/*");
        context.addServlet("PlayerServlet",  playerServlet).addMapping("/player/*");
        context.addServlet("IdServlet",  idServlet).addMapping("/id/*");
        context.addServlet("RosterServlet",  rosterServlet).addMapping("/roster/*");
        context.addServlet("UserServlet", userServlet).addMapping("/user/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth/*");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }
}
