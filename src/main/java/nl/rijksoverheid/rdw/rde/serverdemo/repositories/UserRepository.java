package nl.rijksoverheid.rdw.rde.serverdemo.repositories;

import nl.rijksoverheid.rdw.rde.serverdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email")String email);

    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username")String username);

    //TODO split out enrollment entity
    //TODO add expiry to enrollment entity
    @Query("select u from User u where u.documentEnrollmentId = :enrollmentId")
    User findByDocumentEnrollmentId(@Param("enrollmentId")String enrollmentId);
}


