package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.Bill;
import com.apartment.vmoproject.api.model.Service_Fee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailSenderService {
    public void sendEmail(String toEmail, List<Service_Fee> fees, Bill bill);
}
