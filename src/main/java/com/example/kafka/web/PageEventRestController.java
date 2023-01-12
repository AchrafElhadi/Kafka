package com.example.kafka.web;

import com.example.kafka.entities.PageEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController @AllArgsConstructor
public class PageEventRestController {

    private StreamBridge streamBridge;
    @GetMapping("/publish/{topic}/{namePage}")
    public PageEvent publish(@PathVariable String topic,@PathVariable String namePage)
    {
        PageEvent p=new PageEvent(namePage,Math.random()>0.5?"u1":"u2",new Date(),new Random().nextInt(9000));
        streamBridge.send(topic,p);
        return p;
    }
}
