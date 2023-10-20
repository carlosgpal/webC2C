package com.c2c;

// import java.util.Date;

// import javax.annotation.PostConstruct;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.c2c.model.Product;
// import com.c2c.model.ProductInterface;
// import com.c2c.model.User;
// import com.c2c.model.UserInterface;

@SpringBootApplication
public class App {

    // @Autowired
    // private UserInterface userIf;
    // @Autowired
    // private ProductInterface productIf;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // @PostConstruct
    // public void init() {
    //     User john = new User("12312", "Jhon", "pass1", new Date(123213), true, "ads");
    //     User claire = new User("12343", "Claire", "pass2", new Date(123213), false, "ads");
    //     User juan = new User("1111", "Juan", "pass1", new Date(123213), true, "ads");
    //     User fernando = new User("2222", "Fernando", "pass2", new Date(123213), false, "ads");
    //     Product chopsticks = new Product("1212", "chopsticks barato", "bueno bonito y barato", 0.5, new Date(123213),
    //             "Aquí", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
    //             "https://image.png", "tag", "tag", "tag", "tag", "tag", juan);
    //     Product product2 = new Product("1213", "product2", "bueno bonito y barato", 0.5, new Date(123213), "Aquí",
    //             "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
    //             "tag", "tag", "tag", "tag", "tag", juan);
    //     Product product3 = new Product("1214", "product3", "bueno bonito y barato", 0.5, new Date(123213), "Aquí",
    //             "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
    //             "tag", "tag", "tag", "tag", "tag", juan);
    //     Product product4 = new Product("1215", "product4", "bueno bonito y barato", 0.5, new Date(123213), "Aquí",
    //             "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
    //             "tag", "tag", "tag", "tag", "tag", juan);
    //     Product product5 = new Product("1216", "product5", "bueno bonito y barato", 0.5, new Date(123213), "Aquí",
    //             "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
    //             "tag", "tag", "tag", "tag", "tag", juan);
    //     Product toaster = new Product("2121", "toaster barato", "bueno bonito y barato", 0.8, new Date(412342), "Aquí",
    //             "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
    //             "tag", "tag", "tag", "tag", "tag", fernando);
    //     userIf.save(john);
    //     userIf.save(claire);
    //     userIf.save(juan);
    //     userIf.save(fernando);

    //     userIf.flush();

    //     productIf.save(chopsticks);
    //     productIf.save(product2);
    //     productIf.save(product3);
    //     productIf.save(product4);
    //     productIf.save(product5);
    //     productIf.save(toaster);

    //     productIf.flush();
    // }
}
