package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.model.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{

    Boolean existsByEmail(String email);

    @Modifying
    @Query("update User u SET u.expired =:status where u.email =:email")
    void updateExpiredByEmail(@Param("email") String email,@Param("status") boolean status);

    @Modifying
    @Query("update User u Set u.password= :password where u.email = :email")
    void updatePasswordByEmail(@Param("email") String email,@Param("password") String newPassword);


}
