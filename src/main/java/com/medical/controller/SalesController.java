package com.medical.controller;

import com.medical.entity.Sale;
import com.medical.repository.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin("*")
public class SalesController {

    @Autowired
    private SaleRepository repo;

    // ✅ GET ALL SALES
    @GetMapping
    public List<Sale> getAllSales() {
        return repo.findAll();
    }

    // ✅ GET TODAY SALES
    @GetMapping("/today")
    public List<Sale> getTodaySales() {
        return repo.findByDate(LocalDate.now());
    }
}