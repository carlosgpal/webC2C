package com.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

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
    Product product2 = new Product("1213", "product2", "bueno bonito y barato", 0.5, new Date(123213), "Aquí", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "tag", "tag", "tag", "tag", "tag", juan);
    Product product3 = new Product("1214", "product3", "bueno bonito y barato", 0.5, new Date(123213), "Aquí", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "tag", "tag", "tag", "tag", "tag", juan);
    Product product4 = new Product("1215", "product4", "bueno bonito y barato", 0.5, new Date(123213), "Aquí", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "tag", "tag", "tag", "tag", "tag", juan);
    Product product5 = new Product("1216", "product5", "bueno bonito y barato", 0.5, new Date(123213), "Aquí", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "tag", "tag", "tag", "tag", "tag", juan);
    Product toaster = new Product("2121", "toaster barato", "bueno bonito y barato", 0.8, new Date(412342), "Aquí", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "https://image.png", "tag", "tag", "tag", "tag", "tag", fernando);;

    productItf.save(chopsticks);
    productItf.save(product2);
    productItf.save(product3);
    productItf.save(product4);
    productItf.save(product5);
    productItf.save(toaster);

    productItf.flush();

    Product prod1 = productItf.findByIdproduct("1212");
    User user1 = userItf.findByIduser("1111");
    List<Product> prodList = productItf.findByUser_iduser(user1);
    assertEquals("chopsticks barato", prodList.get(0).getName());
    assertEquals("product2", prodList.get(1).getName());
    assertEquals("product3", prodList.get(2).getName());
    assertEquals("product4", prodList.get(3).getName());
    assertEquals("product5", prodList.get(4).getName());
    assertEquals("chopsticks barato", prod1.getName());
    assertEquals(6, productItf.findAll().size());
    assertEquals(0.5, prod1.getPrice());
    assertEquals(juan.getIduser(), prod1.getUser_iduser().getIduser());

  }
}
