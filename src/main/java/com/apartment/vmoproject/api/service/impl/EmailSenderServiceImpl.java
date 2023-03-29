package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.Bill;
import com.apartment.vmoproject.api.model.Service_Fee;
import com.apartment.vmoproject.api.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAutoConfiguration
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toEmail, List<Service_Fee> fees, Bill bill) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);

        String content = "";
        content += "Service Expense: \n";
        for (Service_Fee sv : fees) {
            content += sv.getName()+": "+ sv.getUnitPrice()+"\n";

        }

        content += "Total price: "+ bill.getTotalPrice();

        message.setSubject("Payment of apartment and living expenses from "+bill.getFromDate()+" to "+bill.getToDate());
        message.setText(content);

        javaMailSender.send(message);
    }
}
