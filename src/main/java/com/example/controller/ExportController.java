package com.example.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ExportCsvService;
import com.example.service.ExportExcelService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/export")
public class ExportController {

	@Autowired
	private ExportCsvService exportCsvService;

	@Autowired
	private ExportExcelService exportExcelService;

	@GetMapping("/csv")
	public ResponseEntity<?> exportData() throws IOException {
		ByteArrayOutputStream baos = exportCsvService.exportData();
		InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(baos.toByteArray()));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=data.csv").body(resource);
	}

	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws IOException {
		byte[] excelFile = exportExcelService.exportExcel();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
		response.getOutputStream().write(excelFile);

	}

}
