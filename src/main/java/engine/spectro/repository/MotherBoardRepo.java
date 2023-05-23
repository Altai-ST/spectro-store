package engine.spectro.repository;

import engine.spectro.entity.MotherBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MotherBoardRepo extends JpaRepository<MotherBoardEntity, Long>, JpaSpecificationExecutor<MotherBoardEntity> {
    MotherBoardEntity findByModel(String model);
    Optional<MotherBoardEntity> findById(Long id);
}
