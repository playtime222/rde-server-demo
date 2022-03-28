package nl.rijksoverheid.rdw.rde.serverdemo.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "UserAccounts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Collection<Document> documents;

    private String documentEnrollmentId;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String lastName) {
        this.username = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Collection < Document > getDocuments() {
//        return documents;
//    }
//
//    public void setDocuments (Collection<Document> documents) {
//        this.documents = documents;
//    }

    public void setDocumentEnrollmentId(String documentEnrollmentId) {
        this.documentEnrollmentId = documentEnrollmentId;
    }

    public String getDocumentEnrollmentId() {
        return documentEnrollmentId;
    }
}


