package billing.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import billing.backend.entity.CategoryEntity;
// import billing.backend.io.CategoryResponse;

@Repository
public interface CategoryReposetory extends JpaRepository<CategoryEntity,Long> {

    Optional<CategoryEntity> findByCategoryId(String categoryId);
    
}
