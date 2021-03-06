package com.example.debateregistration.controller;

import com.example.debateregistration.entity.Student;
import com.example.debateregistration.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String home(Model m) {
        List<Student> list = this.studentRepository.findAll();
        m.addAttribute("all_student_regs", list);
        return "index";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") long id, Model m) {
        Optional<Student> student = this.studentRepository.findById(id);
        Student st = student.get();
        m.addAttribute("student", st);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student, HttpSession session) {
        this.studentRepository.save(student);
        session.setAttribute("msg", "Your Registration added successfully");

        return "redirect:/add";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Student student, HttpSession session) {
        this.studentRepository.save(student);
        session.setAttribute("msg", "Your Registration updated successfully");

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") long id, HttpSession session) {
        this.studentRepository.deleteById(id);
        session.setAttribute("msg", "Registration deleted successfully");
        return "redirect:/";
    }
}
