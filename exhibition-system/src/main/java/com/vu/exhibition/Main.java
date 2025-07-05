package com.vu.exhibition;

import com.vu.exhibition.view.RegistrationForm;

public class Main {
    public static void main(String[] args) {
        // Load UCanAccess driver
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("UCanAccess driver not found");
            e.printStackTrace();
            return;
        }
        
        // Create and show the registration form
        RegistrationForm form = new RegistrationForm();
        form.setVisible(true);
    }
}