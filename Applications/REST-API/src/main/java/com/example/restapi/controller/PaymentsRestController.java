package com.example.restapi.controller;

import com.example.restapi.entity.Payments;
import com.example.restapi.service.PaymentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class PaymentsRestController {

private final PaymentsService paymentsService;

public PaymentsRestController(PaymentsService paymentsService){
        this.paymentsService = paymentsService;
        }
@PostMapping("payments")
public Payments createPayments(@RequestBody Payments payments) {
        payments.setPaymentsId(UUID.randomUUID());
        paymentsService.addPayments(payments);
        return payments;
        }

@GetMapping("payments")
public List<Payments> getAllPayments() {
        return paymentsService.readPayments();
        }

@GetMapping("payment/{id}")
public Optional<Payments> getOnePayments(@PathVariable("id") UUID id) {
        return paymentsService.readOnePayment(id);
        }

@PutMapping("payment/{id}")
public boolean UpdatePayments(@PathVariable("id") UUID id, @RequestBody Payments payments) {
        Optional<Payments> payment_test = paymentsService.readOnePayment(id);
        boolean resultat = false;
        if(payment_test!=null) {
        resultat = paymentsService.updatePayments(payments);
        }
        return resultat;
        }

@DeleteMapping("payment/{id}")
public boolean DeletePayments(@PathVariable("id") UUID id) {
        return paymentsService.deletePayments(id);
        }
        }
