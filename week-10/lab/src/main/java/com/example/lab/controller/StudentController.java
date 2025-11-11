package com.example.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lab.model.Student;
import com.example.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  // Display list of students
  @GetMapping
  public String listStudents(Model model) {
    model.addAttribute("students", studentService.getAllStudents());
    return "students/list";
  }

  // Show form to create new student
  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("student", new Student());
    return "students/form";
  }

  // Show form to edit existing student
  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    Student student = studentService.getStudentById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
    model.addAttribute("student", student);
    return "students/form";
  }

  // Save student (create or update)
  @PostMapping("/save")
  public String saveStudent(@ModelAttribute Student student) {
    studentService.saveStudent(student);
    return "redirect:/students";
  }

  // Delete student
  @GetMapping("/delete/{id}")
  public String deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
    return "redirect:/students";
  }
}
