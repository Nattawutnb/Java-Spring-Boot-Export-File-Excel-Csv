package com.example.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.model.Employees;

@Service
public class ExportCsvService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public ByteArrayOutputStream exportData() throws IOException {
		List<Employees> data = mongoTemplate.findAll(Employees.class);
		return generateCsvData(data);
	}

	private ByteArrayOutputStream generateCsvData(List<Employees> data) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ICsvBeanWriter csvWriter = new CsvBeanWriter(new OutputStreamWriter(baos), CsvPreference.STANDARD_PREFERENCE);

		String[] header = { "id", "name", "salary", "address", "department_id" };
		csvWriter.writeHeader(header);

		for (Employees item : data) {
			csvWriter.write(item, header);

		}

		csvWriter.flush();
		csvWriter.close();
		return baos;

	}

}
