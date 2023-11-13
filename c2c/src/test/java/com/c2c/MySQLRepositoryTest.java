package com.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.c2c.model.Image;
import com.c2c.model.Product;
import com.c2c.model.Tag;
import com.c2c.model.User;
import com.c2c.repository.ImageRepository;
import com.c2c.repository.ProductRepository;
import com.c2c.repository.TagRepository;
import com.c2c.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class MySQLRepositoryTest {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    private TagRepository tagRepo;

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
        imageRepo.save(image1);
        imageRepo.save(image2);
        imageRepo.save(image3);
        imageRepo.save(image4);
        imageRepo.save(image5);
        tagRepo.save(tag1);
        tagRepo.save(tag2);
        tagRepo.save(tag3);
        tagRepo.save(tag4);
        tagRepo.save(tag5);

        productRepo.save(chopsticks);
        productRepo.save(product2);
        productRepo.save(product3);
        productRepo.save(product4);
        productRepo.save(product5);
        productRepo.save(toaster);

        userRepo.save(john);
        userRepo.save(claire);
        userRepo.save(juan);
        userRepo.save(fernando);

        userRepo.flush();
        productRepo.flush();
        imageRepo.flush();
        tagRepo.flush();
    }

    @Test
    public void testSaveUser() {

        assertEquals(4, userRepo.findAll().size());
    }

    @Test
    public void testSaveProduct() {

        assertEquals(6, productRepo.findAll().size());
    }

    @Test
    public void testSaveImage() {

        assertEquals(5, imageRepo.findAll().size());
    }

    @Test
    public void testSaveTag() {

        assertEquals(5, tagRepo.findAll().size());
    }

    @Test
    public void testFindUserById() {
        User user1 = userRepo.findByIduser("12343");

        assertEquals("12343", user1.getIduser());
        assertEquals("Claire", user1.getName());
        assertEquals("Claire@email.com", user1.getEmail());
        assertEquals("pass2", user1.getPass());
        assertEquals(LocalDateTime.of(2023, 11, 13, 12, 30, 0), user1.getLasttime());
        assertEquals(false, user1.getIsverify());
        assertEquals("ads", user1.getVerifylink());
        assertEquals(3, user1.getProducts().size());
        assertEquals(product4.getIdproduct(), user1.getProducts().get(0).getIdproduct());
        assertEquals(product5.getIdproduct(), user1.getProducts().get(1).getIdproduct());
        assertEquals(toaster.getIdproduct(), user1.getProducts().get(2).getIdproduct());
    }

    @Test
    public void testFindProductById() {
        Product product1 = productRepo.findByIdproduct("1212");

        assertEquals("1212", product1.getIdproduct());
        assertEquals("chopsticks barato", product1.getName());
        assertEquals("bueno bonito y barato", product1.getDescription());
        assertEquals(0.5, product1.getPrice());
        assertEquals(LocalDateTime.of(2023, 11, 13, 12, 30, 0), product1.getDate());
        assertEquals("Aquí", product1.getPlace());
        assertEquals(2, product1.getImages().size());
        assertEquals(image1.getIdimage(), product1.getImages().get(0).getIdimage());
        assertEquals(image2.getIdimage(), product1.getImages().get(1).getIdimage());
        assertEquals(2, product1.getTags().size());
        assertEquals(tag1.getIdtag(), product1.getTags().get(0).getIdtag());
        assertEquals(tag2.getIdtag(), product1.getTags().get(1).getIdtag());
        assertEquals(1, product1.getUsers().size());
        assertEquals(john.getIduser(), product1.getUsers().get(0).getIduser());
    }

    public void testFindImageById() {
        Image image1 = imageRepo.findByIdimage("123");

        assertEquals("123", image1.getIdimage());
        assertEquals("https://image.png", image1.getLink());
        assertEquals(2, image1.getProducts().size());
        assertEquals(chopsticks.getIdproduct(), image1.getProducts().get(0).getIdproduct());
        assertEquals(product4.getIdproduct(), image1.getProducts().get(1).getIdproduct());
    }

    public void testFindTagById() {
        Tag tag1 = tagRepo.findByIdtag("123");

        assertEquals("123", tag1.getIdtag());
        assertEquals("tag", tag1.getName());
        assertEquals(2, tag1.getProducts().size());
        assertEquals(chopsticks.getIdproduct(), tag1.getProducts().get(0).getIdproduct());
        assertEquals(product5.getIdproduct(), tag1.getProducts().get(1).getIdproduct());
    }
}
