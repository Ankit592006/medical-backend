package com.medical.controller;

import com.medical.entity.Medicine;
import com.medical.service.MedicineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin("*")
public class MedicineController {

    @Autowired
    private MedicineService service;

     
    @PostMapping
    public Medicine addMedicine(@RequestBody Medicine m) {
        return service.addMedicine(m);
    }

    
    @GetMapping
    public List<Medicine> getAllMedicines() {
        return service.getAllMedicines();
    }

     
    @DeleteMapping("/{id}")
    public String deleteMedicine(@PathVariable Long id) {
        service.deleteMedicine(id);
        return "Deleted successfully";
    }

     
    @PutMapping("/{id}")
    public Medicine updateMedicine(@PathVariable Long id, @RequestBody Medicine m) {
        return service.updateMedicine(id, m);
    }

     
    @GetMapping("/low-stock")
    public List<Medicine> getLowStock() {
        return service.getLowStock();
    }

     
    @GetMapping("/expiring")
    public List<Medicine> getExpiringSoon() {
        return service.getExpiringSoon();
    }
    
    
    @PostMapping("/sell")
    public String sellMedicine(@RequestParam Long id, @RequestParam int qty) {
        service.sellMedicine(id, qty);
        return "Medicine sold successfully";
    }
}