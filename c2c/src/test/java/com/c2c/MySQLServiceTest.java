package com.c2c;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

import com.c2c.dto.*;
import com.c2c.service.*;
import com.c2c.service.exception.ImageNotFoundException;
import com.c2c.service.exception.ProductNotFoundException;
import com.c2c.service.exception.TagNotFoundException;
import com.c2c.service.exception.UserNotFoundException;

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

    ProductDTO product1 = new ProductDTO("1212", "chopsticks barato", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image1, image2), List.of(tag1, tag2));
    ProductDTO product2 = new ProductDTO("1213", "chopsticks caro", "bueno bonito y caro", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image3, image4, image5), List.of(tag4, tag2));
    ProductDTO product3 = new ProductDTO("1214", "chopsticks medio", "bueno bonito y medio", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image1, image2, image3, image4, image5),
            List.of(tag5, tag3));
    ProductDTO product4 = new ProductDTO("1215", "chopsticks barato", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image1, image2), List.of(tag3, tag4, tag1));
    ProductDTO product5 = new ProductDTO("1216", "chopsticks barato", "bueno bonito y barato", 0.5,
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), "Aquí", List.of(image1, image2),
            List.of(tag1, tag2, tag3, tag4, tag5));

    UserDTO jhon = new UserDTO("1", "Jhon", "Jhon@example.com", "pass1", LocalDateTime.of(2023, 11, 13, 12, 30, 0),
            true, "ads", List.of(product1));
    UserDTO claire = new UserDTO("2", "Claire", "Claire@example.com", "pass2",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), true, "ads", List.of(product2, product3));
    UserDTO juan = new UserDTO("3", "Juan", "Juan@example.com", "pass3", LocalDateTime.of(2023, 11, 13, 12, 30, 0),
            true, "ads", List.of(product4, product5));
    UserDTO fernando = new UserDTO("4", "Fernando", "Fernando@example.com", "pass4",
            LocalDateTime.of(2023, 11, 13, 12, 30, 0), true, "ads",
            List.of(product1, product2, product3, product4, product5));

    @BeforeEach
    public void setUp() {

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

        productService.createProduct(product1);
        productService.createProduct(product2);
        productService.createProduct(product3);
        productService.createProduct(product4);
        productService.createProduct(product5);

        userService.createUser(jhon);
        userService.createUser(claire);
        userService.createUser(juan);
        userService.createUser(fernando);
    }

    @Test
    public void testGetAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void testGetUserById() {
        UserDTO user = userService.getUserById("1");
        assertNotNull(user);
        assertEquals("Jhon", user.getName());
    }

    @Test
    public void testUpdateUser() {
        UserDTO userToUpdate = userService.getUserById("1");
        userToUpdate.setName("John Updated");
        UserDTO updatedUser = userService.createUser(userToUpdate);
        assertNotNull(updatedUser);
        assertEquals("John Updated", updatedUser.getName());
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser("1");
        assertThrows(UserNotFoundException.class, () -> userService.getUserById("1"));
    }

    @Test
    public void testGetAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    public void testGetProductById() {
        ProductDTO product = productService.getProductById("1212");
        assertNotNull(product);
        assertEquals("chopsticks barato", product.getName());
    }

    @Test
    public void testUpdateProduct() {
        ProductDTO productToUpdate = productService.getProductById("1212");
        productToUpdate.setName("Updated Chopsticks");
        ProductDTO updatedProduct = productService.createProduct(productToUpdate);
        assertNotNull(updatedProduct);
        assertEquals("Updated Chopsticks", updatedProduct.getName());
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct("1212");
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById("1212"));
    }

    @Test
    public void testGetAllImages() {
        List<ImageDTO> images = imageService.getAllImages();
        assertNotNull(images);
        assertFalse(images.isEmpty());
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

    @Test
    public void testDeleteImage() {
        imageService.deleteImage("123");
        assertThrows(ImageNotFoundException.class, () -> imageService.getImageById("123"));
    }

    @Test
    public void testGetAllTags() {
        List<TagDTO> tags = tagService.getAllTags();
        assertNotNull(tags);
        assertFalse(tags.isEmpty());
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

    @Test
    public void testDeleteTag() {
        tagService.deleteTag("123");
        assertThrows(TagNotFoundException.class, () -> tagService.getTagById("123"));
    }
}
