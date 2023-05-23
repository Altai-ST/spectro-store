package engine.spectro.repository;

import engine.spectro.entity.ROMEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ROMRepo extends JpaRepository<ROMEntity, Long>{
    ROMEntity findByModel(String model);
    Optional<ROMEntity > findById(Long id);
    List<ROMEntity> findByFilters(Map<String, Object> filter);
}
