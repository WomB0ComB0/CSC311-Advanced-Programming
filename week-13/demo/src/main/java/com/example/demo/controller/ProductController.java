package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

  @Autowired private ProductRepository productRepository;

  // 1. List Products
  @GetMapping
  public String listProducts(Model model) {
    model.addAttribute("products", productRepository.findAll());
    return "product-list";
  }

  // 2. Show Add Form
  @GetMapping("/add")
  public String showAddForm(Model model) {
    model.addAttribute("product", new Product());
    return "add-product";
  }

  // 3. Process Add Product
  @PostMapping("/add")
  public String addProduct(@ModelAttribute Product product) {
    productRepository.save(product);
    return "redirect:/products";
  }

  // 4. Show Edit Form (Fetch by ID)
  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable("id") Long id, Model model) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isPresent()) {
      model.addAttribute("product", product.get());
      return "edit-product";
    } else {
      return "redirect:/products"; // Handle invalid ID gracefully
    }
  }

  // 5. Process Update Product
  @PostMapping("/edit/{id}")
  public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
    product.setId(id); // Ensure we are updating the existing ID
    productRepository.save(product);
    return "redirect:/products";
  }

  // 6. Delete Product
  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable("id") Long id) {
    productRepository.deleteById(id);
    return "redirect:/products";
  }
}
