package com.c2c.controller;
// package com.c2c;

// import org.springframework.web.bind.annotation.RestController;

// import com.c2c.model.Product;
// import com.c2c.model.User;
// import com.c2c.repository.UserRepository;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import java.util.List;
// import java.util.ArrayList;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     @Autowired
//     private UserRepository userIf;

//     // Get all users
//     @GetMapping
//     public ResponseEntity<List<User>> getAllUsers() {
//         List<User> users = new ArrayList<>();
//         userIf.findAll().forEach(users::add);
//         return ResponseEntity.ok(users);
//     }

//     // Get user by Id
//     @GetMapping("/{iduser}")
//     public ResponseEntity<User> getUserById(@PathVariable String iduser) {
//         User user = userIf.findByIduser(iduser);
//         if (user == null) {
//             return ResponseEntity.notFound().build();
//         }
//         return ResponseEntity.ok(user);
//     }

//     // Get products by user_iduser
//     @GetMapping("/{iduser}/products")
//     public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable String iduser) {
//         User user = userIf.findByIduser(iduser);
//         if (user == null) {
//             return ResponseEntity.notFound().build();
//         }
//         List<Product> products = userIf.findByUser_iduser(user);
//         return ResponseEntity.ok(products);
//     }

//     // Create a New User
//     @PostMapping
//     public ResponseEntity<User> createUser(@RequestBody User newUser) {
//         User user = userIf.save(newUser);
//         return ResponseEntity.ok(user);
//     }

//     // Update a User
//     @PutMapping("/{iduser}")
//     public ResponseEntity<User> updateUser(@PathVariable String iduser, @RequestBody User updatedUser) {
//         User user = userIf.findByIduser(iduser);
//         if (user == null) {
//             return ResponseEntity.notFound().build();
//         }
//         user.setName(updatedUser.getName());
//         user.setPass(updatedUser.getPass());
//         user.setLasttime(updatedUser.getLasttime());
//         user.setIsverify(updatedUser.getIsverify());
//         user.setVerifylink(updatedUser.getVerifylink());
//         userIf.save(user);
//         return ResponseEntity.ok(user);
//     }

//     // Delete a User
//     @DeleteMapping("/{iduser}")
//     public ResponseEntity<Void> deleteUser(@PathVariable String iduser) {
//         User user = userIf.findByIduser(iduser);
//         if (user == null) {
//             return ResponseEntity.notFound().build();
//         }
//         userIf.delete(user);
//         return ResponseEntity.noContent().build();
//     }
// }
