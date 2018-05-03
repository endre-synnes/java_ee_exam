package com.endre.java.java_ee_exam.frontend.controller;

import com.endre.java.java_ee_exam.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserInfoController userInfoController;

    public String sendMessage(String receiver){
        String sender = userInfoController.getUserName();

        return "";
    }

}
