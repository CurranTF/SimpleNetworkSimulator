// Generated by spring, do not touch!

package com.example.networksimulator;

import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class NetworkSimulatorApplication {
    public static String currentNetworkState;
    public static void main(String[] args) {
        SpringApplication.run(NetworkSimulatorApplication.class, args);
    }

    @PostMapping("/postNetwork")
    public void postNetwork (@RequestBody String networkEncoding ){
        System.out.println(networkEncoding);

    }

}
