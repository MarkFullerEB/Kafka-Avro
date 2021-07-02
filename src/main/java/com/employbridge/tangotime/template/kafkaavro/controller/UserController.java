package com.employbridge.tangotime.template.kafkaavro.controller;

import com.employbridge.tangotime.template.kafkaavro.consumer.Receiver;
import com.employbridge.tangotime.template.kafkaavro.producer.Sender;
import com.employbridge.tangotime.template.kafkaavro.schemas.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    Sender sender;

    @Autowired
    Receiver receiver;

    @PostMapping(path = "/new", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody User u) {
        sender.send(u);

        return new ResponseEntity<String>("Added!", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/view", produces = "application/json")
    public ResponseEntity<List<User>> viewConsumed() {
        ArrayList<User> data = new ArrayList<User>(receiver.consume());

        return new ResponseEntity<List<User>>(data, HttpStatus.ACCEPTED);
    }
}
