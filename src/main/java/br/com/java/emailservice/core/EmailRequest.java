package br.com.java.emailservice.core;

public record EmailRequest(String to,
                           String subject,
                           String body) {
}
