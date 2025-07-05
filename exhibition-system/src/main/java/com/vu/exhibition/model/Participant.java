package com.vu.exhibition.model;

public class Participant {
    private String registrationID;
    private String studentName;
    private String faculty;
    private String projectTitle;
    private String contactNumber;
    private String email;
    private String imagePath;

    // Constructor
    public Participant(String registrationID, String studentName, String faculty, 
                      String projectTitle, String contactNumber, String email, String imagePath) {
        this.registrationID = registrationID;
        this.studentName = studentName;
        this.faculty = faculty;
        this.projectTitle = projectTitle;
        this.contactNumber = contactNumber;
        this.email = email;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public String getRegistrationID() { return registrationID; }
    public void setRegistrationID(String registrationID) { this.registrationID = registrationID; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public String getProjectTitle() { return projectTitle; }
    public void setProjectTitle(String projectTitle) { this.projectTitle = projectTitle; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}