package com.example.services;

import java.util.List;


public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
    List<NotificationMessage> getNotificationMessages();
}

