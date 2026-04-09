package com.medical.service;

import com.medical.entity.Medicine;
import com.medical.entity.Sale;
import com.medical.repository.MedicineRepository;
import com.medical.repository.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository repo;
    
    @Autowired
    private SaleRepository saleRepo;

    @Autowired
    private MedicineRepository medRepo;
     
    public Medicine addMedicine(Medicine m) {
        return repo.save(m);
    }

    
    public List<Medicine> getAllMedicines() {
        return repo.findAll();
    }

     
    public void deleteMedicine(Long id) {
        repo.deleteById(id);
    }

    // UPDATE
    public Medicine updateMedicine(Long id, Medicine m) {
        m.setId(id);
        return repo.save(m);
    }
    
    public void sellMedicine(Long id, int qty) {

        Medicine m = medRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        // STOCK CHECK
        if (m.getQuantity() < qty) {
            throw new RuntimeException("Not enough stock available");
        }

        // REDUCE STOCK
        m.setQuantity(m.getQuantity() - qty);
        medRepo.save(m);

        // CREATE SALE RECORD
        Sale sale = new Sale();
        sale.setMedicineName(m.getName());
        sale.setQuantity(qty);
        sale.setTotalPrice(qty * m.getPrice());
        sale.setDate(LocalDate.now());

        saleRepo.save(sale);
    }

    // LOW STOCK
    public List<Medicine> getLowStock() {
        return repo.findByQuantityLessThan(10);
    }

    // EXPIRING SOON (within 7 days)
    public List<Medicine> getExpiringSoon() {
        return repo.findByExpiryDateBefore(LocalDate.now().plusDays(7));
    }
}