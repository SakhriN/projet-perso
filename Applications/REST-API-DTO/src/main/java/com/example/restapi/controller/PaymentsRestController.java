package com.example.restapi.controller;

import com.example.restapi.dto.PaymentsDTO;
import com.example.restapi.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PaymentsRestController {

    @Autowired
    private PaymentsService paymentsService;

    @PostMapping("payments")
    public PaymentsDTO createPayments(@RequestBody PaymentsDTO paymentsDTO) {
        return paymentsService.addPayments(paymentsDTO);
    }

    @GetMapping("payments")
    public List<PaymentsDTO> getAllPayments() {
        return paymentsService.readPayments();
    }

    @GetMapping("payments/{id}")
    public Optional<PaymentsDTO> getOnePayments(@PathVariable("id") UUID id) {
        return paymentsService.readOnePayments(id);
    }

    @PutMapping("payments/{id}")
    public boolean UpdatePayments(@RequestBody PaymentsDTO paymentsDTO) {
        boolean resultat = false;
        try{
            resultat = paymentsService.updatePayments(paymentsDTO);
            resultat = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("payments/{id}")
    public boolean DeletePayments(@PathVariable("id") UUID id) {
        return paymentsService.deletePayments(id);
    }
}
