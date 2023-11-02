package com.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import com.c2c.model.User;
import com.c2c.model.UserInterface;
import com.c2c.model.Product;
import com.c2c.model.ProductInterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class MySQLQueryTest {
    @Autowired
    private UserInterface userIf;
    @Autowired
    private ProductInterface productIf;

    User john = new User("12312", "Jhon", "pass1", new Date(123213), true, "ads");
    User claire = new User("12343", "Claire", "pass2", new Date(123213), false, "ads");
    User juan = new User("1111", "Juan", "pass1", new Date(123213), true, "ads");
    User fernando = new User("2222", "Fernando", "pass2", new Date(123213), false, "ads");
    Product chopsticks = new Product("1212", "chopsticks barato", "bueno bonito y barato", 0.5, new Date(123213),
            "Aquí", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
            "https://image.png", "tag", "tag", "tag", "tag", "tag", juan);
    Product product2 = new Product("1213", "product2", "bueno bonito y barato", 0.5, new Date(123213), "Aquí",
            "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
            "tag", "tag", "tag", "tag", "tag", juan);
    Product product3 = new Product("1214", "product3", "bueno bonito y barato", 0.5, new Date(123213), "Aquí",
            "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
            "tag", "tag", "tag", "tag", "tag", juan);
    Product product4 = new Product("1215", "product4", "bueno bonito y barato", 0.5, new Date(123213), "Aquí",
            "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
            "tag", "tag", "tag", "tag", "tag", juan);
    Product product5 = new Product("1216", "product5", "bueno bonito y barato", 0.5, new Date(123213), "Aquí",
            "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
            "tag", "tag", "tag", "tag", "tag", juan);
    Product toaster = new Product("2121", "toaster barato", "bueno bonito y barato", 0.8, new Date(412342), "Aquí",
            "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png",
            "tag", "tag", "tag", "tag", "tag", fernando);

    @BeforeEach
    public void setUp() {
        userIf.save(john);
        userIf.save(claire);
        userIf.save(juan);
        userIf.save(fernando);

        userIf.flush();

        productIf.save(chopsticks);
        productIf.save(product2);
        productIf.save(product3);
        productIf.save(product4);
        productIf.save(product5);
        productIf.save(toaster);

        productIf.flush();
    }

    @Test
    public void testSaveUser() {

        assertEquals(4, userIf.findAll().size());
    }

    @Test
    public void testSaveProduct() {

        assertEquals(6, productIf.findAll().size());
    }

    @Test
    public void testFindUserById() {
        User user1 = userIf.findByIduser("12343");

        assertEquals("12343", user1.getIduser());
        assertEquals("Claire", user1.getName());
        assertEquals("pass2", user1.getPass());
        assertEquals(new Date(123213), user1.getLasttime());
        assertEquals(false, user1.getIsverify());
        assertEquals("ads", user1.getVerifylink());
    }

    @Test
    public void testFindProductById() {
        Product prod1 = productIf.findByIdproduct("1212");

        assertEquals("1212", prod1.getIdproduct());
        assertEquals("chopsticks barato", prod1.getName());
        assertEquals("bueno bonito y barato", prod1.getDescription());
        assertEquals(0.5, prod1.getPrice());
        assertEquals(new Date(123213), prod1.getDate());
        assertEquals("Aquí", prod1.getPlace());
        assertEquals("https://image.png", prod1.getImage1());
        assertEquals("https://image.png", prod1.getImage2());
        assertEquals("https://image.png", prod1.getImage3());
        assertEquals("https://image.png", prod1.getImage4());
        assertEquals("https://image.png", prod1.getImage5());
        assertEquals("tag", prod1.getTag1());
        assertEquals("tag", prod1.getTag2());
        assertEquals("tag", prod1.getTag3());
        assertEquals("tag", prod1.getTag4());
        assertEquals("tag", prod1.getTag5());
        assertEquals(juan.getIduser(), prod1.getUser_iduser().getIduser());
    }

    @Test
    public void testFindProductByUser() {
        User user1 = userIf.findByIduser("1111");
        List<Product> prodList = userIf.findByUser_iduser(user1);

        assertEquals("chopsticks barato", prodList.get(0).getName());
        assertEquals("product2", prodList.get(1).getName());
        assertEquals("product3", prodList.get(2).getName());
        assertEquals("product4", prodList.get(3).getName());
        assertEquals("product5", prodList.get(4).getName());
    }
}
