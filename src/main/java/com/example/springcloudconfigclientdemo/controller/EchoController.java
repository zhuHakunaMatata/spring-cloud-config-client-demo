package com.example.springcloudconfigclientdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kyle on 2018/10/8.
 */
@RestController
@RefreshScope
public class EchoController {

    @Value("${my.name}")
    private String name;

    @GetMapping("/echo")
    public String echo(){
        return this.name;
    }

}
