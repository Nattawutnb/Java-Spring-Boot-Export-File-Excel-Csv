package com.example.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Employees {

	@Id
	private String id;
	private String name;
	private Integer salary;
	private String address;
	private String department_id;

}
