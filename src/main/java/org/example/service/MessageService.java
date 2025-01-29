package org.example.service;

import org.example.exception.ResourceNotFoundException;
import org.example.model.Message;
import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessagesForUser(Long userId) {
        return messageRepository.findBySenderIdOrReceiverIdOrderByTimestampDesc(userId, userId);
    }

    public Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id " + messageId));
    }

    public Message sendMessage(Message message) {
        if (message.getReceiverId() == 0 || message.getContent() == null || message.getContent().isEmpty()) {
            throw new IllegalArgumentException("Invalid message");
        }

        message.setTimestamp(new Date());
        return messageRepository.save(message);
    }
}