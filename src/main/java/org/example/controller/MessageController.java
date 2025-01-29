package org.example.controller;

import org.example.model.Message;
import org.example.repository.MessageRepository;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Message>> getMessagesForUser(@PathVariable Long userId) {
        List<Message> messages = messageService.getMessagesForUser(userId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long messageId) {
        Message message = messageService.getMessageById(messageId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message sentMessage = messageService.sendMessage(message);
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> sendMessageAsAdmin(@RequestBody Message message) {
        Message sentMessage = messageService.sendMessage(message);
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }
}

