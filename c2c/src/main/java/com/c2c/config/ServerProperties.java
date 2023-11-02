package com.c2c.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server")
public class ServerProperties {
    private int port;

    // Getters y setters
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}