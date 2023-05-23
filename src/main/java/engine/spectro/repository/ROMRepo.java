package engine.spectro.repository;

import engine.spectro.entity.ROMEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ROMRepo extends JpaRepository<ROMEntity, Long>, JpaSpecificationExecutor<ROMEntity> {
    ROMEntity findByModel(String model);
    Optional<ROMEntity > findById(Long id);
}
