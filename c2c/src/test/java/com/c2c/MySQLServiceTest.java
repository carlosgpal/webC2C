package com.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.c2c.model.Image;
import com.c2c.model.Product;
import com.c2c.model.Tag;
import com.c2c.model.User;
import com.c2c.repository.ImageRepository;
import com.c2c.repository.ProductRepository;
import com.c2c.repository.TagRepository;
import com.c2c.repository.UserRepository;
import com.c2c.service.ImageService;
import com.c2c.service.ProductService;
import com.c2c.service.TagService;
import com.c2c.service.UserService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class MySQLServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private TagService tagService;

    private static User john = new User("12312", "Jhon", "Jhon@email.com", "pass1",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), true, "ads");
    private static User claire = new User("12343", "Claire", "Claire@email.com", "pass2",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), false, "ads");
    private static User juan = new User("1111", "Juan", "Juan@email.com", "pass3",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), true, "ads");
    private static User fernando = new User("2222", "Fernando", "Fernando@email.com", "pass4",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), false, "ads");
    private static Product chopsticks = new Product("1212", "chopsticks barato", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0),
            "Aquí");
    private static Product product2 = new Product("1213", "product2", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    private static Product product3 = new Product("1214", "product3", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    private static Product product4 = new Product("1215", "product4", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    private static Product product5 = new Product("1216", "product5", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    private static Product toaster = new Product("2121", "toaster barato", "bueno bonito y barato", 0.8,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí");
    private static Image image1 = new Image("123", "https://image.png");
    private static Image image2 = new Image("124", "https://image.png");
    private static Image image3 = new Image("125", "https://image.png");
    private static Image image4 = new Image("126", "https://image.png");
    private static Image image5 = new Image("127", "https://image.png");
    private static Tag tag1 = new Tag("123", "tag");
    private static Tag tag2 = new Tag("124", "tag");
    private static Tag tag3 = new Tag("125", "tag");
    private static Tag tag4 = new Tag("126", "tag");
    private static Tag tag5 = new Tag("127", "tag");

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
        // Not invalid
        assertEquals(4, userService.getAllUsers().size());

        userRepository.deleteAll();

        // Invalid
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.getAllUsers();
        });

        String expectedMessage = "No users found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetAllProducts() {
        // Not invalid
        assertEquals(6, productService.getAllProducts().size());

        // Invalid
        productRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productService.getAllProducts();
        });

        String expectedMessage = "No products found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetAllImages() {
        // Not invalid
        assertEquals(5, imageService.getAllImages().size());

        // Invalid
        imageRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            imageService.getAllImages();
        });

        String expectedMessage = "No images found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetAllTags() {
        // Not invalid
        assertEquals(5, tagService.getAllTags().size());

        // Invalid
        tagRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            tagService.getAllTags();
        });

        String expectedMessage = "No tags found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Transactional
    public void testGetUserById() {
        // Not invalid
        User user = userService.getUserById("12312");

        assertEquals("12312", user.getIduser());
        assertEquals("Jhon", user.getName());
        assertEquals("Jhon@email.com", user.getEmail());
        assertEquals("pass1", user.getPass());
        assertEquals(LocalDateTime.of(2023, 11, 13, 12, 30, 0), user.getLasttime());
        assertEquals("ads", user.getVerifylink());
        assertEquals(true, user.getIsverify());
        assertEquals(1, user.getProducts().size());
        assertEquals("1212", user.getProducts().get(0).getIdproduct());

        // Invalid
        userRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserById("12312");
        });

        String expectedMessage = "User with ID: 12312 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Transactional
    public void testGetProductById() {
        // Not invalid
        Product product = productService.getProductById("1212");

        assertEquals("1212", product.getIdproduct());
        assertEquals("chopsticks barato", product.getName());
        assertEquals("bueno bonito y barato", product.getDescription());
        assertEquals(0.5, product.getPrice());
        assertEquals(LocalDateTime.of(2023, 11, 13, 12, 30, 0), product.getDate());
        assertEquals("Aquí", product.getPlace());
        assertEquals(1, product.getUsers().size());
        assertEquals("12312", product.getUsers().get(0).getIduser());
        assertEquals(2, product.getImages().size());
        assertEquals("123", product.getImages().get(0).getIdimage());
        assertEquals("124", product.getImages().get(1).getIdimage());
        assertEquals(2, product.getTags().size());
        assertEquals("123", product.getTags().get(0).getIdtag());
        assertEquals("124", product.getTags().get(1).getIdtag());

        // Invalid
        productRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productService.getProductById("1212");
        });

        String expectedMessage = "Product with ID: 1212 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Transactional
    public void testGetImageById() {
        // Not invalid
        Image image = imageService.getImageById("123");

        assertEquals("123", image.getIdimage());
        assertEquals("https://image.png", image.getLink());
        assertEquals(3, image.getProducts().size());
        assertEquals("1212", image.getProducts().get(0).getIdproduct());
        assertEquals("1215", image.getProducts().get(1).getIdproduct());
        assertEquals("1216", image.getProducts().get(2).getIdproduct());

        // Invalid
        imageRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            imageService.getImageById("123");
        });

        String expectedMessage = "Image with ID: 123 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Transactional
    public void testGetTagById() {
        // Not invalid
        Tag tag = tagService.getTagById("123");

        assertEquals("123", tag.getIdtag());
        assertEquals("tag", tag.getName());
        assertEquals(3, tag.getProducts().size());
        assertEquals("1212", tag.getProducts().get(0).getIdproduct());
        assertEquals("1216", tag.getProducts().get(1).getIdproduct());
        assertEquals("2121", tag.getProducts().get(2).getIdproduct());

        // Invalid
        tagRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            tagService.getTagById("123");
        });

        String expectedMessage = "Tag with ID: 123 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateUser() {
        // Invalid
        User invalidUser = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(invalidUser);
        });

        String expectedMessage = "User cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateProduct() {
        // Invalid
        Product invalidProduct = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(invalidProduct);
        });

        String expectedMessage = "Product cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateImage() {
        // Invalid
        Image invalidImage = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            imageService.createImage(invalidImage);
        });

        String expectedMessage = "Image cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateTag() {
        // Invalid
        Tag invalidTag = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tagService.createTag(invalidTag);
        });

        String expectedMessage = "Tag cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateOrUpdateUser() {
        // Invalid
        User invalidUser = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createOrUpdateUser("12343", invalidUser);
        });

        String expectedMessage = "User cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateOrUpdateProduct() {
        // Invalid
        Product invalidProduct = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.createOrUpdateProduct("1212", invalidProduct);
        });

        String expectedMessage = "Product cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateOrUpdateImage() {
        // Invalid
        Image invalidImage = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            imageService.createOrUpdateImage("123", invalidImage);
        });

        String expectedMessage = "Image cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateOrUpdateTag() {
        // Invalid
        Tag invalidTag = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tagService.createOrUpdateTag("123", invalidTag);
        });

        String expectedMessage = "Tag cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testUpdateUser() {
        // Invalid
        User invalidUser = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.updateUser("12343", invalidUser);
        });

        String expectedMessage = "User cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testUpdateProduct() {
        // Invalid
        Product invalidProduct = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.updateProduct("1212", invalidProduct);
        });

        String expectedMessage = "Product cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testUpdateImage() {
        // Invalid
        Image invalidImage = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            imageService.updateImage("123", invalidImage);
        });

        String expectedMessage = "Image cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testUpdateTag() {
        // Invalid
        Tag invalidTag = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tagService.updateTag("123", invalidTag);
        });

        String expectedMessage = "Tag cannot be null";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testDeleteUser() {
        // Not invalid
        userService.deleteUser("12343");

        assertEquals(3, userService.getAllUsers().size());
        assertEquals(3, productService.getAllProducts().size());

        // Invalid
        userRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.deleteUser("12343");
        });

        String expectedMessage = "User with ID: 12343 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeleteProduct() {
        // Not invalid
        productService.deleteProduct("1212");

        assertEquals(5, productService.getAllProducts().size());
        assertEquals(4, userService.getAllUsers().size());
        assertEquals(5, imageService.getAllImages().size());
        assertEquals(5, tagService.getAllTags().size());

        // Invalid
        productRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productService.deleteProduct("1212");
        });

        String expectedMessage = "Product with ID: 1212 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeleteImage() {
        // Not invalid
        imageService.deleteImage("123");

        assertEquals(4, imageService.getAllImages().size());
        assertEquals(6, productService.getAllProducts().size());

        // Invalid
        imageRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            imageService.deleteImage("123");
        });

        String expectedMessage = "Image with ID: 123 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeleteTag() {
        // Not invalid
        tagService.deleteTag("123");

        assertEquals(4, tagService.getAllTags().size());
        assertEquals(6, productService.getAllProducts().size());

        // Invalid
        tagRepository.deleteAll();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            tagService.deleteTag("123");
        });

        String expectedMessage = "Tag with ID: 123 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
