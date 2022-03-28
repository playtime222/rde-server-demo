package nl.rijksoverheid.rdw.rde.serverdemo.repositories;

import nl.rijksoverheid.rdw.rde.serverdemo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    //TODO paging...
    @Query("select new nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageMetadata(m.id, m.whenSent, m.fromUser.username, m.note) from Message m where m.document.owner.email = :emailAddress")
    List<MessageMetadata> findByUser(@Param("emailAddress")  String emailAddress);

    @Query("select m.content from Message m where m.id = :id and m.document.owner.email = :emailAddress")
    byte[] tryGetContent(@Param("id") Long id, @Param("emailAddress")  String emailAddress);
}



