package com.maxiflexy.securedocumentapplication.service;

public interface EmailService {

    void sendNewAccountEmail(String name, String email, String token);
    void sendPasswordEmail(String name, String email, String token);
}
