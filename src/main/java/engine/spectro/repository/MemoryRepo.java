package engine.spectro.repository;

import engine.spectro.entity.MemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MemoryRepo extends JpaRepository<MemoryEntity, Long>, JpaSpecificationExecutor<MemoryRepo> {
    MemoryEntity findByModel(String model);
    Optional<MemoryEntity> findById(Long id);
}
