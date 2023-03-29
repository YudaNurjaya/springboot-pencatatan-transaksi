package com.enigma.livecodeecommerce.controller;

import com.enigma.livecodeecommerce.model.request.DailyReport;
import com.enigma.livecodeecommerce.model.request.MonthlyReport;
import com.enigma.livecodeecommerce.model.response.SuccessResponse;
import com.enigma.livecodeecommerce.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    @GetMapping("/daily")
    public ResponseEntity dailyReports(@RequestParam String date) throws ParseException {
        List<DailyReport> get = reportService.dailyReports(date);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<DailyReport>>("Success",get));
    }
    @GetMapping("/monthly")
    public ResponseEntity monthlyReports(@RequestParam String start, String end) throws ParseException {
        List<MonthlyReport> get = reportService.monthlyReports(start, end);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<MonthlyReport>>("Success",get));
    }
}
