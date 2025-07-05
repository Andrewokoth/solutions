package com.vu.exhibition.view;

import com.vu.exhibition.controller.DatabaseController;
import com.vu.exhibition.model.Participant;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RegistrationForm extends JFrame {
    private DatabaseController dbController;
    
    // Form components
    private JTextField txtRegistrationID, txtStudentName, txtFaculty, txtProjectTitle, txtContactNumber, txtEmail;
    private JLabel lblImage;
    private JButton btnRegister, btnSearch, btnUpdate, btnDelete, btnClear, btnExit, btnUploadImage,btnexist ;
    private String imagePath;
    
    public RegistrationForm() {
        dbController = new DatabaseController();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Victoria University Exhibition Registration System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Form panel (Center)
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Row 0: Registration ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Registration ID:"), gbc);
        gbc.gridx = 1;
        txtRegistrationID = new JTextField(20);
        formPanel.add(txtRegistrationID, gbc);
        
        // Row 1: Student Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        txtStudentName = new JTextField(20);
        formPanel.add(txtStudentName, gbc);
        
        // Row 2: Faculty
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Faculty:"), gbc);
        gbc.gridx = 1;
        txtFaculty = new JTextField(20);
        formPanel.add(txtFaculty, gbc);
        
        // Row 3: Project Title
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Project Title:"), gbc);
        gbc.gridx = 1;
        txtProjectTitle = new JTextField(20);
        formPanel.add(txtProjectTitle, gbc);
        
        // Row 4: Contact Number
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Contact Number:"), gbc);
        gbc.gridx = 1;
        txtContactNumber = new JTextField(20);
        formPanel.add(txtContactNumber, gbc);
        
        // Row 5: Email
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);
        
        // Row 6: Image Upload
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Project Image:"), gbc);
        gbc.gridx = 1;
        btnUploadImage = new JButton("Upload Image");
        btnUploadImage.addActionListener(e -> uploadImage());
        formPanel.add(btnUploadImage, gbc);
        
        // Image display area (East)
        JPanel imagePanel = new JPanel(new BorderLayout());
        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(300, 300));
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        lblImage.setBorder(BorderFactory.createEtchedBorder());
        imagePanel.add(lblImage, BorderLayout.CENTER);
        
        // Button panel (South)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnRegister = new JButton("Register");
        btnSearch = new JButton("Search");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");
        
        // Add action listeners
        btnRegister.addActionListener(e -> registerParticipant());
        btnSearch.addActionListener(e -> searchParticipant());
        btnUpdate.addActionListener(e -> updateParticipant());
        btnDelete.addActionListener(e -> deleteParticipant());
        btnClear.addActionListener(e -> clearForm());
        btnExit.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);
        
        // Add panels to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(imagePanel, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);
        
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath();
            
            // Display the image
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(img));
        }
    }
    
    private boolean validateInput() {
        if (txtRegistrationID.getText().isEmpty() ||
            txtStudentName.getText().isEmpty() ||
            txtFaculty.getText().isEmpty() ||
            txtProjectTitle.getText().isEmpty() ||
            txtContactNumber.getText().isEmpty() ||
            txtEmail.getText().isEmpty() ||
            imagePath == null) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Email validation
        if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Contact number validation (simple check for digits)
        if (!txtContactNumber.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Contact number should contain only digits!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void registerParticipant() {
        if (!validateInput()) return;
        
        Participant participant = new Participant(
            txtRegistrationID.getText(),
            txtStudentName.getText(),
            txtFaculty.getText(),
            txtProjectTitle.getText(),
            txtContactNumber.getText(),
            txtEmail.getText(),
            imagePath
        );
        
        if (dbController.insertParticipant(participant)) {
            JOptionPane.showMessageDialog(this, "Participant registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        }
    }
    
    private void searchParticipant() {
        String registrationID = txtRegistrationID.getText();
        if (registrationID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Registration ID to search!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Participant participant = dbController.searchParticipant(registrationID);
        if (participant != null) {
            txtStudentName.setText(participant.getStudentName());
            txtFaculty.setText(participant.getFaculty());
            txtProjectTitle.setText(participant.getProjectTitle());
            txtContactNumber.setText(participant.getContactNumber());
            txtEmail.setText(participant.getEmail());
            
            // Display image if path exists
            if (participant.getImagePath() != null && !participant.getImagePath().isEmpty()) {
                imagePath = participant.getImagePath();
                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
                lblImage.setIcon(new ImageIcon(img));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Participant not found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void updateParticipant() {
        if (!validateInput()) return;
        
        Participant participant = new Participant(
            txtRegistrationID.getText(),
            txtStudentName.getText(),
            txtFaculty.getText(),
            txtProjectTitle.getText(),
            txtContactNumber.getText(),
            txtEmail.getText(),
            imagePath
        );
        
        if (dbController.updateParticipant(participant)) {
            JOptionPane.showMessageDialog(this, "Participant updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update participant!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteParticipant() {
        String registrationID = txtRegistrationID.getText();
        if (registrationID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Registration ID to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this participant?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (dbController.deleteParticipant(registrationID)) {
                JOptionPane.showMessageDialog(this, "Participant deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete participant!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void clearForm() {
        txtRegistrationID.setText("");
        txtStudentName.setText("");
        txtFaculty.setText("");
        txtProjectTitle.setText("");
        txtContactNumber.setText("");
        txtEmail.setText("");
        lblImage.setIcon(null);
        imagePath = null;
    }
}