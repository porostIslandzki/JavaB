package com.first_chat.publisher.service;

import com.first_chat.publisher.model.Notification;
import com.first_chat.publisher.model.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;

@Service
public class NotificationServiceImpl implements NotificationService{
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;


    public NotificationServiceImpl(RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendStudentNotification(Long studentId) {
        Student student = restTemplate.exchange("http://localhost:8081/students/" + studentId,
                HttpMethod.GET, HttpEntity.EMPTY, Student.class).getBody();
        Notification notification = new Notification();
        notification.setEmail(student.getEmail());
        notification.setTitle("Witaj! " + student.getFirstName());
        notification.setBody("Miło, że jesteś z nami: " + student.getFirstName() + " " + student.getLastName());
        rabbitTemplate.convertAndSend("test", notification);
    }
}
