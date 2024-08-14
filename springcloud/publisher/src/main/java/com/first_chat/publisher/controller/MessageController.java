package com.first_chat.publisher.controller;

import com.first_chat.publisher.model.Notification;
import com.first_chat.publisher.service.NotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;
    //problem w tym, ze spring mysli, ze domyslnie mamy rabbita lokalnie
    //więc musimy mu podać namiary w application.properties
    public MessageController(RabbitTemplate rabbitTemplate, NotificationService notificationService) {
        this.rabbitTemplate = rabbitTemplate;
        this.notificationService = notificationService;
    }
    @GetMapping("/message")
    public String sendMessage(@RequestParam String message){
        rabbitTemplate.convertAndSend("test", message);
        return "Wrzucono wiadomosc na Rabbit.";
    }
    //jak w message chcesz wrzucic kon to wpisujesz
    // localhost::8084/message?message=kon
    @PostMapping("/notification")
    public String sendNotification(@RequestBody Notification notification){
        rabbitTemplate.convertAndSend("test", notification);
        return "Notyfikacja wysłana";
    }
    private final NotificationService notificationService;

    @GetMapping("/notifications")
    public String sendStudentNotification(@RequestParam long studentId){
        notificationService.sendStudentNotification(studentId);
        return "Wiadomość została wysłana do studenta o id: " + studentId;
    }

}
