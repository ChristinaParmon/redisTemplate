package com.example.demo.service;

import com.example.demo.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private static final String STUDENT = "Student";

    private final RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Long, Student> hashOperations;

    @Autowired
    public StudentServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Student save(Student student) {
        hashOperations.put(STUDENT, student.getId(), student);
        return student;
    }

    @Override
    public Student findById(Long id) {
        return hashOperations.get(STUDENT, id);
    }
}
