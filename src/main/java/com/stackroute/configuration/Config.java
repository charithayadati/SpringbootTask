package com.stackroute.configuration;


import com.stackroute.domain.Track;
import com.stackroute.userservice.TrackService;
import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;



@Configuration

public class Config {

    TrackService trackService;

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean=new ServletRegistrationBean(new WebdavServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent cfr) {
        try {
            trackService.saveTrack(new Track(1,"nee neeli","Dear Comrade"));
            trackService.saveTrack(new Track(2,"telusuna","sontham"));
            trackService.saveTrack(new Track(3,"believer","album"));
            System.out.println("Context Refreshed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

