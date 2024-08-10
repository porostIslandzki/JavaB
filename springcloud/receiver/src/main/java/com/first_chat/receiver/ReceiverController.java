package com.first_chat.receiver;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {
    //musimy wstrzyknąć instancję klasy konfiguracyjnej
    //dla naszego rabbita

    private final RabbitTemplate rabbitTemplate;

    public ReceiverController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
@GetMapping("/message")
    public String receiveMessage(){
        Object message = rabbitTemplate.receiveAndConvert("test");
        if(message!=null) {
            return "Udało się pobrać wiadomość: " + message.toString();
        } else {
            return "klucze bez e";
        }
    }
}
