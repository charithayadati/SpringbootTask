package com.stackroute.configuration;


import com.stackroute.domain.Track;
import com.stackroute.userservice.TrackService;
import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration

public class Config {

    TrackService trackService;

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean=new ServletRegistrationBean(new WebdavServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.stackroute.Muzix.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo()).tags(new Tag("Track Controller", "CURD Operations"));
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("Track Management REST API")

                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
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

