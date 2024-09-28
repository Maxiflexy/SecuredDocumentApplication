package com.maxiflexy.securedocumentapplication.utils;

public class EmailUtils {

    public static String getEmailMessage(String name,String host,String  key){
        return "Hello " + name + ",\n\nYour new account has been created. Please click on the link below to verify your account.\n\n" +
                getVerificationUrl(host, key) + "\n\nThe Support Team";
    }

    public static String getResetPasswordMessage(String name,String host,String  key){
        return "Hello " + name + ",\n\nYour new account has been created. Please click on the link below to verify your account.\n\n" +
                getResetPasswordUrl(host, key) + "\n\nThe Support Team";
    }

    private static String getVerificationUrl(String host, String key) {
        return host + "/verify/account?key=" + key;
    }

    private static String getResetPasswordUrl(String host, String token) {
        return host + "/verify/password?key=" + token;
    }
}
