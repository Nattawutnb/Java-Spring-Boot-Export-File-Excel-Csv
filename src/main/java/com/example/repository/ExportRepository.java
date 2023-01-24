package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.Employees;

public interface ExportRepository extends MongoRepository<Employees, String> {

}
