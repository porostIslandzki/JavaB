package com.first_chat.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
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
            return "naw";
        }
    }
@GetMapping("/notification")
    public ResponseEntity<Notification> receiveNotification(){
        Notification notification = rabbitTemplate.receiveAndConvert("test", ParameterizedTypeReference.forType(Notification.class));
        if(notification != null){
            return ResponseEntity.ok(notification);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    //skonfigurujemy funkcjonalność, która sama będzie nasłuchiwała
    //na kolejkę rabbita. jeśli pojawi się wiadomość, to automatycznie zostanie
    //pobrana
  /* zakomentujemy żeby przetestować metodę receiveNotification  @RabbitListener(queues = "test")
    public void listenerMessage(String message){
        System.out.println(message);
    } */
    @RabbitListener(queues = "test")
    public void listenerMessage(Notification notification){
        System.out.println(notification.getEmail() + " " + notification.getTitle());
    }
}
