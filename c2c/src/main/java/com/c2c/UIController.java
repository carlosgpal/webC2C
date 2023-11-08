package com.c2c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.c2c.model.Product;

import java.io.IOException;
import java.util.List;

@Controller
public class UIController {

    @Autowired
    private ProductController productController;

    @GetMapping("/")
    public String viewHomePage(Model model) throws IOException {
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        if (response.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("listProduct", response.getBody());
            return "index";
        } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return "errorPageInternalServerError";
        } else {
            return "genericErrorPage";
        }
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) throws IOException {
        ResponseEntity<Product> response = productController.createProduct(product);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/";
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return "redirect:/errorPageBadRequest";
        } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return "redirect:/errorPageInternalServerError";
        } else {
            return "redirect:/genericErrorPage";
        }
    }

    @PutMapping("/updateProduct/{idproduct}")
    public String updateProduct(@ModelAttribute("product") Product product) throws IOException {
        ResponseEntity<Product> response = productController.updateProduct(product.getIdproduct(), product);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/";
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return "redirect:/errorPageBadRequest";
        } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return "redirect:/errorPageInternalServerError";
        } else {
            return "redirect:/genericErrorPage";
        }
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id, Model model) throws IOException {
        ResponseEntity<Product> response = productController.getProductById(id);

        if (response.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("product", response.getBody());
            return "updateProductDocument";
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return "productNotFoundPage";
        } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return "errorPageInternalServerError";
        } else {
            return "genericErrorPage";
        }
    }

    @GetMapping("/showNewProductForm")
    public String showNewEmployeeForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "newProductDocument";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") String id) throws IOException {
        ResponseEntity<Void> response = productController.deleteProduct(id);

        if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return "redirect:/";
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return "productNotFoundPage";
        } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return "errorPageInternalServerError";
        } else
            return "genericErrorPage";
    }
}
