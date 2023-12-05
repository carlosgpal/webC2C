package com.backend;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.backend.dto.*;
import com.backend.service.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class MySQLServiceTest {

    // Autowire the required services
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private TagService tagService;

    // Create image and tag objects
    ImageDTO image1 = new ImageDTO("123", "https://image.png");
    ImageDTO image2 = new ImageDTO("124", "https://image.png");
    ImageDTO image3 = new ImageDTO("125", "https://image.png");
    ImageDTO image4 = new ImageDTO("126", "https://image.png");
    ImageDTO image5 = new ImageDTO("127", "https://image.png");

    TagDTO tag1 = new TagDTO("123", "tag");
    TagDTO tag2 = new TagDTO("124", "tag");
    TagDTO tag3 = new TagDTO("125", "tag");
    TagDTO tag4 = new TagDTO("126", "tag");
    TagDTO tag5 = new TagDTO("127", "tag");

    // Create product objects
    ProductDTO product1 = new ProductDTO("1212", "chopsticks barato", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image1, image2), List.of(tag1, tag2));
    ProductDTO product2 = new ProductDTO("1213", "chopsticks caro", "buenobonitoycaro", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image3, image4, image5), List.of(tag4, tag2));
    ProductDTO product3 = new ProductDTO("1214", "chopsticks medio", "bueno bonito y medio", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image1, image2, image3, image4, image5),
            List.of(tag5, tag3));
    ProductDTO product4 = new ProductDTO("1215", "chopsticks barato", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image1, image2), List.of(tag3, tag4, tag1));
    ProductDTO product5 = new ProductDTO("1216", "chopsticks barato", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image1, image2),
            List.of(tag1, tag2, tag3, tag4, tag5));

    // Create user objects
    UserDTO jhon = new UserDTO("1", "Jhon", "Jhon@example.com", "pass1",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0),
            true, "ads", List.of(product1));
    UserDTO claire = new UserDTO("2", "Claire", "Claire@example.com", "pass2",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), true, "ads", List.of(product2,
                    product3));
    UserDTO juan = new UserDTO("3", "Juan", "Juan@example.com", "pass3",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0),
            true, "ads", List.of(product4, product5));
    UserDTO fernando = new UserDTO("4", "Fernando", "Fernando@example.com",
            "pass4",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), true, "ads",
            List.of(product2, product3, product4, product5));

    @BeforeEach
    public void setUp() {
        // Create images
        imageService.createImage(image1);
        imageService.createImage(image2);
        imageService.createImage(image3);
        imageService.createImage(image4);
        imageService.createImage(image5);

        // Create tags
        tagService.createTag(tag1);
        tagService.createTag(tag2);
        tagService.createTag(tag3);
        tagService.createTag(tag4);
        tagService.createTag(tag5);

        // Create products
        productService.createProduct(product1);
        productService.createProduct(product2);
        productService.createProduct(product3);
        productService.createProduct(product4);
        productService.createProduct(product5);

        // Create users
        userService.createUser(jhon);
        userService.createUser(claire);
        userService.createUser(juan);
        userService.createUser(fernando);
    }

    // Test cases for UserService

    @Test
    @Transactional
    public void testGetAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(4, users.size());
    }

    @Test
    @Transactional
    public void testGetUserById() {
        UserDTO user = userService.getUserById("1");
        assertNotNull(user);
        assertEquals("Jhon", user.getName());
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        UserDTO userToUpdate = userService.getUserById("1");
        userToUpdate.setName("John Updated");
        UserDTO updatedUser = userService.createUser(userToUpdate);
        assertNotNull(updatedUser);
        assertEquals("John Updated", updatedUser.getName());
    }

    // @Test
    // @Transactional
    // public void testDeleteUser() {
    // userService.deleteUser("1");
    // assertThrows(Exception.class, () -> userService.getUserById("1"));
    // List<ProductDTO> products = productService.getAllProducts();
    // List<ImageDTO> images = imageService.getAllImages();
    // List<TagDTO> tags = tagService.getAllTags();
    // assertEquals(4, products.size());
    // assertEquals(5, images.size());
    // assertEquals(5, tags.size());
    // }

    // Test cases for ProductService

    @Test
    @Transactional
    public void testGetAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(5, products.size());
    }

    @Test
    @Transactional
    public void testGetProductById() {
        ProductDTO product = productService.getProductById("1212");
        assertNotNull(product);
        assertEquals("chopsticks barato", product.getName());
    }

    @Test
    @Transactional
    public void testUpdateProduct() {
        ProductDTO productToUpdate = productService.getProductById("1212");
        productToUpdate.setName("Updated Chopsticks");
        ProductDTO updatedProduct = productService.createProduct(productToUpdate);
        assertNotNull(updatedProduct);
        assertEquals("Updated Chopsticks", updatedProduct.getName());
    }

    // @Test
    // @Transactional
    // public void testDeleteProduct() {
    // productService.deleteProduct("1212");
    // assertThrows(Exception.class, () -> productService.getProductById("1212"));
    // List<UserDTO> users = userService.getAllUsers();
    // assertEquals(4, users.size());
    // List<ImageDTO> images = imageService.getAllImages();
    // assertEquals(5, images.size());
    // List<TagDTO> tags = tagService.getAllTags();
    // assertEquals(5, tags.size());
    // }

    // Test cases for ImageService

    @Test
    public void testGetAllImages() {
        List<ImageDTO> images = imageService.getAllImages();
        assertNotNull(images);
        assertEquals(5, images.size());
    }

    @Test
    public void testGetImageById() {
        ImageDTO image = imageService.getImageById("123");
        assertNotNull(image);
        assertEquals("https://image.png", image.getLink());
    }

    @Test
    public void testUpdateImage() {
        ImageDTO imageToUpdate = imageService.getImageById("123");
        imageToUpdate.setLink("https://updatedimage.png");
        ImageDTO updatedImage = imageService.createImage(imageToUpdate);
        assertNotNull(updatedImage);
        assertEquals("https://updatedimage.png", updatedImage.getLink());
    }

    // @Test
    // @Transactional
    // public void testDeleteImage() {
    // imageService.deleteImage("123");
    // assertThrows(Exception.class, () -> imageService.getImageById("123"));
    // List<ProductDTO> products = productService.getAllProducts();
    // assertEquals(5, products.size());
    // }

    // Test cases for TagService

    @Test
    public void testGetAllTags() {
        List<TagDTO> tags = tagService.getAllTags();
        assertNotNull(tags);
        assertEquals(5, tags.size());
    }

    @Test
    public void testGetTagById() {
        TagDTO tag = tagService.getTagById("123");
        assertNotNull(tag);
        assertEquals("tag", tag.getName());
    }

    @Test
    public void testUpdateTag() {
        TagDTO tagToUpdate = tagService.getTagById("123");
        tagToUpdate.setName("Updated Tag");
        TagDTO updatedTag = tagService.createTag(tagToUpdate);
        assertNotNull(updatedTag);
        assertEquals("Updated Tag", updatedTag.getName());
    }

    // @Test
    // @Transactional
    // public void testDeleteTag() {
    // tagService.deleteTag("123");
    // assertThrows(Exception.class, () -> tagService.getTagById("123"));
    // List<ProductDTO> products = productService.getAllProducts();
    // assertEquals(5, products.size());
    // }
}
