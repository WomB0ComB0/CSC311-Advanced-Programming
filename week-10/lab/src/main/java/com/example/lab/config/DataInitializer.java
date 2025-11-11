package com.example.lab.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.lab.model.Student;
import com.example.lab.repository.StudentRepository;

@Configuration
public class DataInitializer {

  @Bean
  CommandLineRunner initDatabase(StudentRepository repository) {
    return args -> {
      // Add sample students if database is empty
      if (repository.count() == 0) {
        repository.save(new Student("John", "Doe", "john.doe@example.com", "Computer Science"));
        repository.save(new Student("Jane", "Smith", "jane.smith@example.com", "Data Science"));
        repository.save(new Student("Bob", "Johnson", "bob.johnson@example.com", "Software Engineering"));
        repository.save(new Student("Alice", "Williams", "alice.williams@example.com", "Information Systems"));

        System.out.println("Sample data initialized!");
      }
    };
  }
}
