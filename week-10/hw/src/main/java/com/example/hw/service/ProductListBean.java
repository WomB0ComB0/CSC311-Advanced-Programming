package com.example.hw.service;

import com.example.hw.model.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component // Creates a bean of this class
public class ProductListBean {

  // Member variable for the list
  private final List<Product> productList;

  // Constructor loads data from CSV
  public ProductListBean() {
    this.productList = new ArrayList<>();

    // Load the CSV file from the resources directory
    Resource resource = new ClassPathResource("products.csv");

    try (InputStream inputStream = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;

      // Skip the header row
      reader.readLine();

      // Read each line, split by comma, create Product, add to list
      while ((line = reader.readLine()) != null) {
        // Split the line by commas
        // Using a limit ensures the last two fields (with potential commas)
        // are handled correctly, although for this specific CSV, a simple split is
        // fine.
        // For robustness with commas in descriptions/reviews, a proper CSV parser is
        // better.
        // For this assignment, we assume simple comma separation.
        String[] data = line.split(",");

        // Create and add the product
        Product product = new Product(
            data[0], // id
            data[1], // name
            Double.parseDouble(data[2]), // price
            data[3], // stock status
            Double.parseDouble(data[4]), // rating
            data[5], // description
            data[6] // customer review
        );
        this.productList.add(product);
      }

    } catch (IOException e) {
      e.printStackTrace();
      // Handle exception properly in a real app
    }
  }

  // Get method for the list
  public List<Product> getProductList() {
    return productList;
  }

  // Helper method to find a single product by its ID
  public Product findById(String id) {
    for (Product product : productList) {
      if (product.getId().equals(id)) {
        return product;
      }
    }
    return null; // Not found
  }
}
