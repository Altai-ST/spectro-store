package engine.spectro.repository;

import engine.spectro.entity.MemoryEntity;
import engine.spectro.entity.MotherBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MotherBoardRepo extends JpaRepository<MotherBoard, Long>, JpaSpecificationExecutor<MotherBoard> {
    MotherBoard findByModel(String model);
    Optional<MotherBoard> findById(Long id);
}
