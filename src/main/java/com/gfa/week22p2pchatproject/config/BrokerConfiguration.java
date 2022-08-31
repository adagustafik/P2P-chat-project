package com.gfa.week22p2pchatproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class BrokerConfiguration {

    @Bean
    public URL getBrokerPath() throws MalformedURLException {
        return new URL("https://rafale-p2p-chat.herokuapp.com");
    }
}
