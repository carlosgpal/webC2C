package com.c2c;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.c2c.model.User;
import com.c2c.model.UserInterface;

@SpringBootApplication
public class App {
	
    // @Autowired
    // private UserInterface userItf;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

    // @PostConstruct
    // public void init() {
    //     User john = new User("12312","Jhon","pass1",new Date(123213), true, "ads");
    //     User claire = new User("12343","Claire","pass2",new Date(123213), false, "ads");

    //     userItf.save(john);
    //     userItf.save(claire);

    //     userItf.flush();
    // }
}
