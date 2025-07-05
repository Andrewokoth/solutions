package com.vu.exhibition.controller;

import com.vu.exhibition.model.Participant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DatabaseController {
    private static final String DB_URL = "jdbc:ucanaccess://src/main/resources/VUE_Exhibition.accdb";
    
    // Database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    
    // Insert participant
    public boolean insertParticipant(Participant participant) {
        String sql = "INSERT INTO Participants (RegistrationID, StudentName, Faculty, ProjectTitle,  ContactNumber, Email, ImagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, participant.getRegistrationID());
            pstmt.setString(2, participant.getStudentName());
            pstmt.setString(3, participant.getFaculty());
            pstmt.setString(4, participant.getProjectTitle());
            pstmt.setString(5, participant.getContactNumber());
            pstmt.setString(6, participant.getEmail());
            pstmt.setString(7, participant.getImagePath());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error inserting participant: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Search participant by ID
    public Participant searchParticipant(String registrationID) {
        String sql = "SELECT * FROM Participants WHERE RegistrationID = ?";
        Participant participant = null;
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, registrationID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                participant = new Participant(
                    rs.getString("RegistrationID"),
                    rs.getString("StudentName"),
                    rs.getString("Faculty"),
                    rs.getString("ProjectTitle"),
                    rs.getString("ContactNumber"),
                    rs.getString("Email"),
                    rs.getString("ImagePath")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error searching participant: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return participant;
    }
    
    // Update participant
    public boolean updateParticipant(Participant participant) {
        String sql = "UPDATE Participants SET StudentName = ?, Faculty = ?, ProjectTitle = ?, ContactNumber = ?, Email = ?, ImagePath = ? WHERE RegistrationID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, participant.getStudentName());
            pstmt.setString(2, participant.getFaculty());
            pstmt.setString(3, participant.getProjectTitle());
            pstmt.setString(4, participant.getContactNumber());
            pstmt.setString(5, participant.getEmail());
            pstmt.setString(6, participant.getImagePath());
            pstmt.setString(7, participant.getRegistrationID());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating participant: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Delete participant
    public boolean deleteParticipant(String registrationID) {
        String sql = "DELETE FROM Participants WHERE RegistrationID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, registrationID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting participant: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Get all participants (for future features)
    public List<Participant> getAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        String sql = "SELECT * FROM Participants";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Participant participant = new Participant(
                    rs.getString("RegistrationID"),
                    rs.getString("StudentName"),
                    rs.getString("Faculty"),
                    rs.getString("ProjectTitle"),
                    rs.getString("ContactNumber"),
                    rs.getString("Email"),
                    rs.getString("ImagePath")
                );
                participants.add(participant);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving participants: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return participants;
    }
}