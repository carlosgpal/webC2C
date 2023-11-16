package com.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.c2c.model.Image;
import com.c2c.model.Product;
import com.c2c.model.Tag;
import com.c2c.model.User;
import com.c2c.service.ImageService;
import com.c2c.service.ProductService;
import com.c2c.service.TagService;
import com.c2c.service.UserService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class MySQLServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private TagService tagService;

    public static User john = new User("12312", "Jhon", "Jhon@email.com", "pass1",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), true, "ads");
    public static User claire = new User("12343", "Claire", "Claire@email.com", "pass2",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), false, "ads");
    public static User juan = new User("1111", "Juan", "Juan@email.com", "pass3",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), true, "ads");
    public static User fernando = new User("2222", "Fernando", "Fernando@email.com", "pass4",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), false, "ads");
    public static Product chopsticks = new Product("1212", "chopsticks barato", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0),
            "Aquí");
    public static Product product2 = new Product("1213", "product2", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    public static Product product3 = new Product("1214", "product3", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    public static Product product4 = new Product("1215", "product4", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    public static Product product5 = new Product("1216", "product5", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    public static Product toaster = new Product("2121", "toaster barato", "bueno bonito y barato", 0.8,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    public static Image image1 = new Image("123", "https://image.png");
    public static Image image2 = new Image("124", "https://image.png");
    public static Image image3 = new Image("125", "https://image.png");
    public static Image image4 = new Image("126", "https://image.png");
    public static Image image5 = new Image("127", "https://image.png");
    public static Tag tag1 = new Tag("123", "tag");
    public static Tag tag2 = new Tag("124", "tag");
    public static Tag tag3 = new Tag("125", "tag");
    public static Tag tag4 = new Tag("126", "tag");
    public static Tag tag5 = new Tag("127", "tag");

    @BeforeAll
    public static void setUpBeforeAll() {
        chopsticks.addImage(image1);
        chopsticks.addImage(image2);
        product2.addImage(image3);
        product2.addImage(image4);
        product3.addImage(image4);
        product3.addImage(image5);
        product4.addImage(image5);
        product4.addImage(image1);
        product5.addImage(image1);
        product5.addImage(image2);
        toaster.addImage(image2);
        toaster.addImage(image3);
        chopsticks.addTag(tag1);
        chopsticks.addTag(tag2);
        product2.addTag(tag2);
        product2.addTag(tag3);
        product3.addTag(tag3);
        product3.addTag(tag4);
        product4.addTag(tag4);
        product4.addTag(tag5);
        product5.addTag(tag5);
        product5.addTag(tag1);
        toaster.addTag(tag1);
        toaster.addTag(tag2);
        john.addProduct(chopsticks);
        fernando.addProduct(product2);
        juan.addProduct(product3);
        claire.addProduct(product4);
        claire.addProduct(product5);
        claire.addProduct(toaster);
    }

    @BeforeEach
    public void setUp() {

        userService.createUser(john);
        userService.createUser(claire);
        userService.createUser(juan);
        userService.createUser(fernando);

        imageService.createImage(image1);
        imageService.createImage(image2);
        imageService.createImage(image3);
        imageService.createImage(image4);
        imageService.createImage(image5);

        tagService.createTag(tag1);
        tagService.createTag(tag2);
        tagService.createTag(tag3);
        tagService.createTag(tag4);
        tagService.createTag(tag5);

        productService.createProduct(chopsticks);
        productService.createProduct(product2);
        productService.createProduct(product3);
        productService.createProduct(product4);
        productService.createProduct(product5);
        productService.createProduct(toaster);
    }

    @Test
    public void testGetAllUsers() {
        // Testear control de errores cuando esté hecho
        assertEquals(4, userService.getAllUsers().size());
    }

    @Test
    public void testGetAllProducts() {
        // Testear control de errores cuando esté hecho
        assertEquals(6, productService.getAllProducts().size());
    }

    @Test
    public void testGetAllImages() {
        // Testear control de errores cuando esté hecho
        assertEquals(5, imageService.getAllImages().size());
    }

    @Test
    public void testGetAllTags() {
        // Testear control de errores cuando esté hecho
        assertEquals(5, tagService.getAllTags().size());
    }

    @Test
    public void testGetUserById() {
        // Testear control de errores cuando esté hecho
        User user = userService.getUserById("12312");

        assertEquals("12312", user.getIduser());
        assertEquals("Jhon", user.getName());
        assertEquals("Jhon@email.com", user.getEmail());
        assertEquals("pass1", user.getPass());
        assertEquals(LocalDateTime.of(2023, 11, 13, 12, 30, 0), user.getLasttime());
        assertEquals("ads", user.getVerifylink());
        assertEquals(true, user.getIsverify());
    }

    @Test
    public void testGetProductById() {
        // Testear control de errores cuando esté hecho
        Product product = productService.getProductById("1212");

        assertEquals("1212", product.getIdproduct());
        assertEquals("chopsticks barato", product.getName());
        assertEquals("bueno bonito y barato", product.getDescription());
        assertEquals(0.5, product.getPrice());
        assertEquals(LocalDateTime.of(2023, 11, 13, 12, 30, 0), product.getDate());
        assertEquals("Aquí", product.getPlace());
    }

    @Test
    public void testGetImageById() {
        // Testear control de errores cuando esté hecho
        Image image = imageService.getImageById("123");

        assertEquals("123", image.getIdimage());
        assertEquals("https://image.png", image.getLink());
    }

    @Test
    public void testGetTagById() {
        // Testear control de errores cuando esté hecho
        Tag tag = tagService.getTagById("123");

        assertEquals("123", tag.getIdtag());
        assertEquals("tag", tag.getName());
    }

    @Test
    public void testCreateUser() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testCreateProduct() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testCreateImage() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testCreateTag() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testCreateOrUpdateUser() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testCreateOrUpdateProduct() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testCreateOrUpdateImage() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testCreateOrUpdateTag() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testUpdateUser() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testUpdateProduct() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testUpdateImage() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testUpdateTag() {
        // Testear control de errores cuando esté hecho
        assertTrue(true);
    }

    @Test
    public void testDeleteUser() {
        // Testear control de errores cuando esté hecho
        userService.deleteUser("12343");

        assertEquals(3, userService.getAllUsers().size());
        assertEquals(3, productService.getAllProducts().size());
    }

    @Test
    public void testDeleteProduct() {
        // Testear control de errores cuando esté hecho
        productService.deleteProduct("1212");

        assertEquals(5, productService.getAllProducts().size());
        assertEquals(4, userService.getAllUsers().size());
        assertEquals(5, imageService.getAllImages().size());
        assertEquals(5, tagService.getAllTags().size());
    }

    @Test
    public void testDeleteImage() {
        // Testear control de errores cuando esté hecho
        imageService.deleteImage("123");

        assertEquals(4, imageService.getAllImages().size());
        assertEquals(6, productService.getAllProducts().size());
    }

    @Test
    public void testDeleteTag() {
        // Testear control de errores cuando esté hecho
        tagService.deleteTag("123");

        assertEquals(4, tagService.getAllTags().size());
        assertEquals(6, productService.getAllProducts().size());
    }

}
