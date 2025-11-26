package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private double price;
  private String stockStatus;
  private double rating;
  private String description;
  private String customerReview;

  // No-arg constructor (Required by JPA)
  public Product() {}

  // Constructor with parameters
  public Product(
      String name,
      double price,
      String stockStatus,
      double rating,
      String description,
      String customerReview) {
    this.name = name;
    this.price = price;
    this.stockStatus = stockStatus;
    this.rating = rating;
    this.description = description;
    this.customerReview = customerReview;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getStockStatus() {
    return stockStatus;
  }

  public void setStockStatus(String stockStatus) {
    this.stockStatus = stockStatus;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCustomerReview() {
    return customerReview;
  }

  public void setCustomerReview(String customerReview) {
    this.customerReview = customerReview;
  }
}
