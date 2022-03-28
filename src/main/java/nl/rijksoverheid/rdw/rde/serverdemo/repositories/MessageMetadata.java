package nl.rijksoverheid.rdw.rde.serverdemo.repositories;

import java.time.LocalDateTime;

//Flattened representation
public class MessageMetadata {
        private long id;
        private LocalDateTime whenSent;
        private String fromUser;
        private String toUser;
        private String note;

    public MessageMetadata(long id, LocalDateTime whenSent, String fromUser, String note) {
        this.id = id;
        this.whenSent = whenSent;
        this.fromUser = fromUser;
        this.note = note;
    }

    public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public LocalDateTime getWhenSent() {
            return whenSent;
        }

        public void setWhenSent(LocalDateTime whenSent) {
            this.whenSent = whenSent;
        }

        public String getFromUser() {
            return fromUser;
        }

        public void setFromUser(String fromUser) {
            this.fromUser = fromUser;
        }

        public String getToUser() {
            return toUser;
        }

        public void setToUser(String toUser) {
            this.toUser = toUser;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
