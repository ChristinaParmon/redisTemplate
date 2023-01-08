package com.example.demo.endpoint;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class EndPoint {
    @Autowired
    StudentService studentService;

    @GetMapping(path = "{id}")
    @Cacheable(value = "Student", key = "#id")
    public Student getStudent(@PathVariable("id") final Long id) {
        return studentService.findById(id);
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.save(student);
    }
}
