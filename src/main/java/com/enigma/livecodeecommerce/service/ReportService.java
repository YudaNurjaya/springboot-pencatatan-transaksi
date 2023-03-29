package com.enigma.livecodeecommerce.service;
import com.enigma.livecodeecommerce.model.Product;
import com.enigma.livecodeecommerce.model.Transaction;
import com.enigma.livecodeecommerce.model.TransactionDetail;
import com.enigma.livecodeecommerce.model.request.DailyReport;
import com.enigma.livecodeecommerce.model.request.MonthlyReport;
import com.enigma.livecodeecommerce.repository.TransactionDetailRepository;
import com.enigma.livecodeecommerce.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ReportService {
    TransactionRepository transactionRepository;
    TransactionDetailRepository detailRepository;
    @Autowired
    public ReportService(TransactionRepository transactionRepository, TransactionDetailRepository detailRepository) {
        this.transactionRepository = transactionRepository;
        this.detailRepository = detailRepository;
    }
    public List<DailyReport> dailyReports(String day) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
        List<Transaction> get = transactionRepository.findByDate(date);
        List<TransactionDetail> find = detailRepository.findByTransactionIn(get);

        Map<Integer, DailyReport> dailyReportMap = new HashMap<>();
        for(TransactionDetail transactionDetail: find){
            Product product = transactionDetail.getProduct();
            DailyReport dailyReport = dailyReportMap.getOrDefault(product.getId(), new DailyReport());
            dailyReport.setDate(day);
            dailyReport.setQty(transactionDetail.getQty());
            dailyReport.setTransaksiId(transactionDetail.getTransaction().getId());
            dailyReport.setProductName(product.getName());
            dailyReport.setGrandTotal(BigDecimal.valueOf(transactionDetail.getQty()*transactionDetail.getProduct().getPrice().getPrice()));
            dailyReportMap.put(product.getId(),dailyReport);
        }
        List<DailyReport> reportList = new ArrayList<>(dailyReportMap.values());
        return reportList;
    }
    public List<MonthlyReport> monthlyReports(String startDate, String endDate) throws ParseException {
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        List<Transaction> get = transactionRepository.findByDateBetween(start,end);
        List<TransactionDetail> find = detailRepository.findByTransactionIn(get);

        Map<Integer, MonthlyReport> dailyReportMap = new HashMap<>();
        for(TransactionDetail transactionDetail: find){
            Product product = transactionDetail.getProduct();
            MonthlyReport monthlyReports = dailyReportMap.getOrDefault(product.getId(), new MonthlyReport());
            monthlyReports.setQty(transactionDetail.getQty());
            monthlyReports.setDate(transactionDetail.getTransaction().getDate());
            monthlyReports.setTransaksiId(transactionDetail.getTransaction().getId());
            monthlyReports.setProductName(product.getName());
            monthlyReports.setGrandTotal(BigDecimal.valueOf(transactionDetail.getQty()*transactionDetail.getProduct().getPrice().getPrice()));
            dailyReportMap.put(product.getId(),monthlyReports);
        }
        List<MonthlyReport> reportList = new ArrayList<>(dailyReportMap.values());
        return reportList;
    }
}
