package com.example.hw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hw.model.Product;
import com.example.hw.service.ProductListBean;

@Controller
public class ProductController {

  // Member variable for the bean
  private final ProductListBean productListBean;

  // Constructor injection
  public ProductController(ProductListBean productListBean) {
    this.productListBean = productListBean;
  }

  /**
   * Handles requests for the index page ("/").
   * Adds the full product list to the model.
   */
  @GetMapping("/")
  public String showIndexPage(Model model) {
    // Add the list to the model
    model.addAttribute("products", productListBean.getProductList());
    return "index"; // Returns templates/index.html
  }

  /**
   * Handles requests for the product detail page.
   * Finds the product by ID and adds it to the model.
   * The URL will be like /productdetail?id=P002
   */
  @GetMapping("/productdetail")
  public String showDetailPage(@RequestParam("id") String id, Model model) {
    // Find the specific product
    Product product = productListBean.findById(id);

    // Add the single product to the model
    model.addAttribute("product", product);
    return "productdetail"; // Returns templates/productdetail.html
  }
}
