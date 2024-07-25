package br.com.java.emailservice.core;

// Interface que define o comportamento da aplicação (Casos de uso, o que a aplicação faz)
public interface EmailSenderUseCase {
    void senEmail(String to, String subject, String body);
}
