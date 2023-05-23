package engine.spectro.repositories;


import engine.spectro.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {

    UserEntity findByFirstName(String firstName);
    UserEntity findByLastName(String lastName);
    Optional<UserEntity> findByEmail(String email);
    UserEntity findByPhoneNumber(String number);
}
