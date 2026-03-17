package com.springAi.ResumeBuilder.controller;

import com.springAi.ResumeBuilder.dto.ExportRequest;
import com.springAi.ResumeBuilder.service.ExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ExportController {

    private final ExportService exportService;

    @PostMapping("/pdf")
    public ResponseEntity<byte[]> exportPdf(@RequestBody ExportRequest request) {
        byte[] file = exportService.generatePdf(request.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }

    @PostMapping("/docx")
    public ResponseEntity<byte[]> exportDocx(@RequestBody ExportRequest request) {
        byte[] file = exportService.generateDocx(request.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.docx")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(file);
    }
}