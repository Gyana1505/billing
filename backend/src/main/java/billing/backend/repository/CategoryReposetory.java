package billing.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import billing.backend.entity.CategoryEntity;

@Repository
public interface CategoryReposetory extends JpaRepository<CategoryEntity,Long> {
    
}
