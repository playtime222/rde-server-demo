package nl.rijksoverheid.rdw.rde.serverdemo.repositories;

import nl.rijksoverheid.rdw.rde.serverdemo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    //List for current user
    @Query("select new nl.rijksoverheid.rdw.rde.serverdemo.repositories.DocumentMetadata(d.id, d.displayName) from Document d where d.owner.email = :email")
    List<DocumentMetadata> findByUserEmail(@Param("email")  String email);

//    // List for UI selection when sending
//    @Query("select d from Document d where d.owner.username = :username")
//    List<Document> findByUsername(@Param("username")  String username);

    // List for UI selection when sending
    // Flat list alternative to findByUsername
    @Query("select new nl.rijksoverheid.rdw.rde.serverdemo.repositories.Recipient(d.id, u.email, d.displayName) from Document d join User u on d.owner = u")
    List<Recipient> listRecipients();
}
