package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "messages")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})

public class Message {
    @Id
    @SequenceGenerator(
            name = "messages_id_seq",
            sequenceName = "messages_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_id_seq")
    protected long id;
    protected long senderId;
    protected String senderType;
    protected long receiverId;
    protected String receiverType;
    protected String content;
    protected Date timestamp;

    public Message() {
    }

    public Message(long id, long senderId, String senderType, long receiverId, String receiverType, String content, Date time) {
        this.id = id;
        this.senderId = senderId;
        this.senderType = senderType;
        this.receiverId = receiverId;
        this.receiverType = receiverType;
        this.content = content;
        this.timestamp = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;
        return id == message.id && senderId == message.senderId
                && receiverId == message.receiverId
                && Objects.equals(senderType, message.senderType)
                && Objects.equals(receiverType, message.receiverType)
                && Objects.equals(content, message.content)
                && Objects.equals(timestamp, message.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderId, senderType, receiverId, receiverType, content, timestamp);
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", senderType='" + senderType + '\'' +
                ", receiverId=" + receiverId +
                ", receiverType='" + receiverType + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
