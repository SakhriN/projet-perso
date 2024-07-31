package com.example.restapi.service;

import com.example.restapi.entity.Payments;
import com.example.restapi.repository.PaymentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;


    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }


    public Payments addPayments(Payments payments) {
        return paymentsRepository.save(payments);
    }

    public Optional<Payments> readOnePayment(UUID id) {
        return paymentsRepository.findById(id);
    }

    public List<Payments> readPayments() {
        return paymentsRepository.findAll();
    }

    public boolean updatePayments(Payments payments) {
        boolean test = false;
        try {
            paymentsRepository.save(payments);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }


    public boolean deletePayments(UUID id) {
        boolean test = false;
        try {
            paymentsRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

}
