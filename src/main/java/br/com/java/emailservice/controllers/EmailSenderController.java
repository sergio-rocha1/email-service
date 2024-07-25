package br.com.java.emailservice.controllers;

import br.com.java.emailservice.application.EmailSenderService;
import br.com.java.emailservice.core.EmailRequest;
import br.com.java.emailservice.core.exceptions.EmailServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    private final Logger logger = LoggerFactory.getLogger(EmailSenderController.class);

    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
            this.emailSenderService.senEmail(request.to(), request.subject(), request.body());
            return ResponseEntity.ok("Email enviado com sucesso!");
        } catch (EmailServiceException ex) {
            logger.error("Erro enquanto enviava o email", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro enquanto enviava o email");
        }
    }
}
