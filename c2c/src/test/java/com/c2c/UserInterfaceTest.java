package com.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import com.c2c.model.User;
import com.c2c.model.UserInterface;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
// @AutoConfigureTestDatabase (connection = EmbeddedDatabaseConnection.H2)
public class UserInterfaceTest {
  @Autowired
  private UserInterface userItf;

  @Test
  public void saveUser() {

    User john = new User("12312", "Jhon", "pass1", new Date(123213), true, "ads");
    User claire = new User("12343", "Claire", "pass2", new Date(123213), false, "ads");

    userItf.save(john);
    userItf.save(claire);

    userItf.flush();

    User user1 = userItf.findByIduser("12343");
    assertEquals("Claire", user1.getName());
    assertEquals(2, userItf.findAll().size());
    assertEquals("pass2", user1.getPass());

  }
}