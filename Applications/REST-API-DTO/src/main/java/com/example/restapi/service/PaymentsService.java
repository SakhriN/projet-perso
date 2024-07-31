package com.example.restapi.service;

import com.example.restapi.entity.Payments;
import com.example.restapi.dto.PaymentsDTO;
import com.example.restapi.mapper.PaymentsMapper;
import com.example.restapi.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;
    private final PaymentsMapper paymentsMapper;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository, PaymentsMapper paymentsMapper) {
        this.paymentsRepository = paymentsRepository;
        this.paymentsMapper = paymentsMapper;
    }

    public PaymentsDTO addPayments(PaymentsDTO paymentsDTO) {
        Payments payments = paymentsMapper.toPayments(paymentsDTO);
        return paymentsMapper.toPaymentsDto(paymentsRepository.save(payments));
    }

    public Optional<PaymentsDTO> readOnePayments(UUID id) {
        return paymentsRepository.findById(id).map(paymentsMapper::toPaymentsDto);
    }

    public List<PaymentsDTO> readPayments() {
        return paymentsRepository.findAll().stream()
                .map(paymentsMapper::toPaymentsDto)
                .collect(Collectors.toList());
    }

    public boolean updatePayments(PaymentsDTO paymentsDTO) {
        boolean test = false;
        try {
            Payments payments = paymentsMapper.toPayments(paymentsDTO);
            paymentsRepository.save(payments);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public boolean deletePayments(UUID id) {
        boolean test = false;
        try {
            paymentsRepository.deleteById(id);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

}
