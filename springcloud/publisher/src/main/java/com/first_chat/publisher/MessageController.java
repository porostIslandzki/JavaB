package com.first_chat.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;
    //problem w tym, ze spring mysli, ze domyslnie mamy rabbita lokalnie
    //więc musimy mu podać namiary w application.properties
    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @GetMapping("/message")
    public String sendMessage(@RequestParam String message){
        rabbitTemplate.convertAndSend("test", message);
        return "Wrzucono wiadomosc na Rabbit.";
    }
    //jak w message chcesz wrzucic kon to wpisujesz
    // localhost::8084/message?message=kon
}
