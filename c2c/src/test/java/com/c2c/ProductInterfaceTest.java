package com.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import com.c2c.model.User;
import com.c2c.model.UserInterface;
import com.c2c.model.Product;
import com.c2c.model.ProductInterface;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
// @AutoConfigureTestDatabase (connection = EmbeddedDatabaseConnection.H2)
public class ProductInterfaceTest {
  @Autowired
  private UserInterface userItf;
  @Autowired
  private ProductInterface productItf;

  @Test
  public void saveProduct() {

    // First save users for asigning them to products
    User juan = new User("1111", "Juan", "pass1", new Date(123213), true, "ads");
    User fernando = new User("2222", "Fernando", "pass2", new Date(123213), false, "ads");
    userItf.save(juan);
    userItf.save(fernando);

    userItf.flush();
    
    Product chopsticks = new Product("1212", "chopsticks barato", "bueno bonito y barato", 0.5, new Date(123213), "Aquí", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "tag", "tag", "tag", "tag", "tag", juan);
    Product toaster = new Product("2121", "toaster barato", "bueno bonito y barato", 0.8, new Date(412342), "Aquí", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "tag", "tag", "tag", "tag", "tag", fernando);;

    productItf.save(chopsticks);
    productItf.save(toaster);

    productItf.flush();

    Product prod1 = productItf.findByIdproduct("1212");
    assertEquals("chopsticks barato", prod1.getName());
    assertEquals(2, productItf.findAll().size());
    assertEquals(0.5, prod1.getPrice());
    assertEquals(juan.getIduser(), prod1.getUser_iduser().getIduser());

  }
}
