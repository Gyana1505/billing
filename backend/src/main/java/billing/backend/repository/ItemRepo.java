package billing.backend.repository;

import billing.backend.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ItemRepo extends JpaRepository<ItemEntity,Long> {

    Optional<ItemEntity> findByItemId(String id);
   Integer countByCategoryId(Long Id);

}
