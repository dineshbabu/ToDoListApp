package com.my.todo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.todo.repository.ToDoItemRepositoryImpl;
import com.my.todo.service.ToDoItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan("com.my.todo")
@EnableSwagger2
public class ToDoAppConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public ToDoItemServiceImpl toDoListServiceImpl(){
        return new ToDoItemServiceImpl();
    }

    @Bean
    public ToDoItemRepositoryImpl getToDoRepositoryImpl(){
        return new ToDoItemRepositoryImpl();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.my.todo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return (container -> {
            container.setPort(environment.getProperty("service.todoapp.port", Integer.class));
            container.setContextPath(environment.getProperty("service.todoapp.contextpath"));
        });
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
