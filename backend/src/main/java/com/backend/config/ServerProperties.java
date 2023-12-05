package com.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// Configuration class to set up different environments
@Configuration
@ConfigurationProperties(prefix = "server")
public class ServerProperties {
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}