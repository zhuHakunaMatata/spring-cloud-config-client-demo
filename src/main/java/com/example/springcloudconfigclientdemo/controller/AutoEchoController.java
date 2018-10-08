package com.example.springcloudconfigclientdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by kyle on 2018/10/8.
 */

@RestController
@RefreshScope
public class AutoEchoController {
    @Value("${my.name}")
    private String name;

    private ContextRefresher contextRefresher;

    @Autowired
    public AutoEchoController(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    @GetMapping("/autoEcho")
    public String autoEcho(){
        return this.name;
    }

    @Scheduled(fixedRate = 5*1000 , initialDelay = 3*1000)
    private void autoRefresh(){
        Set<String> updatedProperties = this.contextRefresher.refresh();
        updatedProperties.forEach(property->{
            System.err.println(property);
        });

    }

}
