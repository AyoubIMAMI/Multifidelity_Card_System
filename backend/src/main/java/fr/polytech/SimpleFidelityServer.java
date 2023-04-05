package fr.polytech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SimpleFidelityServer {

    public static void main(String[] args) {
        SpringApplication.run(SimpleFidelityServer.class, args);
    }

}
