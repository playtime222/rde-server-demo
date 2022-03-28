package nl.rijksoverheid.rdw.rde.serverdemo.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime whenSent;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private Document document;
    private String note;
    private byte[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getWhenSent() {
        return whenSent;
    }

    public void setWhenSent(LocalDateTime when) {
        this.whenSent = when;
    }

    public User getFromUser() {
        return fromUser;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void setFromUser(User from) {
        this.fromUser = from;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
