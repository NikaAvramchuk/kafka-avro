package com.example.kafkaavro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/kafka")
public class RestController {
    @Autowired
    private AvroProducer avroProducer;

    @PostMapping(value = "/book")
    String sendBookDetails(@RequestBody Book book) {
        avroProducer.sendMessage(book);
        return "Super!";
    }

}
