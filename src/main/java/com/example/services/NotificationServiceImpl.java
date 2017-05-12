package com.example.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.services.NotificationMessage.NotificationMessageType;
@Service
public class NotificationServiceImpl implements NotificationService{


    public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

    @Autowired
    private HttpSession httpSession;

    @Override
    public void addInfoMessage(String msg) {
        addNotificationMessage(NotificationMessageType.INFO, msg);
    }

    @Override
    public void addErrorMessage(String msg) {
        addNotificationMessage(NotificationMessageType.ERROR, msg);
    }

    @Override
    public List<NotificationMessage> getNotificationMessages() {
        List<NotificationMessage> notifyMessages = (List<NotificationMessage>) httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, new ArrayList<NotificationMessage>());
        return notifyMessages;
    }
    
    
    
    private void addNotificationMessage(NotificationMessageType type, String msg) {
        List<NotificationMessage> notifyMessages = (List<NotificationMessage>) httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
        notifyMessages = new ArrayList<NotificationMessage>();
        if (notifyMessages == null) {
        }
        notifyMessages.add(new NotificationMessage(type, msg));
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notifyMessages);
    }
    
    

}
