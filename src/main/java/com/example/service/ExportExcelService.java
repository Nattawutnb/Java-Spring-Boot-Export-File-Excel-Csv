package com.example.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employees;
import com.example.repository.ExportRepository;

@Service
public class ExportExcelService {

	@Autowired
	private ExportRepository repository;

	public byte[] exportExcel() throws IOException {

		List<Employees> data = repository.findAll();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("employees");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Salary");
		headerRow.createCell(3).setCellValue("Address");
		headerRow.createCell(4).setCellValue("Department_id");

		int rowNum = 1;
		for (Employees employees : data) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(employees.getId());
			row.createCell(1).setCellValue(employees.getName());
			row.createCell(2).setCellValue(employees.getSalary());
			row.createCell(3).setCellValue(employees.getAddress());
			row.createCell(4).setCellValue(employees.getDepartment_id());

		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return outputStream.toByteArray();

	}

}
