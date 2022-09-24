package com.example.Petsitter_App.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements Runnable{

    private JavaMailSender javaMailSender; // 1

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void run() {
            try {
                //usypiamy wątek na 100 milisekund
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom("Blog Example <from@email.com>");

        msg.setSubject(subject);
        msg.setText(content);


        javaMailSender.send(msg);
    }
}