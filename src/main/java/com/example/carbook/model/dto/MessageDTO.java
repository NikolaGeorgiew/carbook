package com.example.carbook.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class MessageDTO {
    @NotEmpty(message = "Name must be provided!")
    private String nameOfUser;
    @NotEmpty(message = "Email must be provided!")
    private String emailOfUser;
    @NotEmpty(message = "Subject must be provided!")
    private String subject;
    @NotEmpty(message = "Message must be provided!")
    private String message;

    public static MessageDTO empty() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(null);
        messageDTO.setSubject(null);
        messageDTO.setNameOfUser(null);
        messageDTO.setEmailOfUser(null);

        return messageDTO;
    }


    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public String getEmailOfUser() {
        return emailOfUser;
    }

    public void setEmailOfUser(String emailOfUser) {
        this.emailOfUser = emailOfUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
