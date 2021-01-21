package com.jonasoliveira.lojaweb.services;

import org.springframework.mail.SimpleMailMessage;

import com.jonasoliveira.lojaweb.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
