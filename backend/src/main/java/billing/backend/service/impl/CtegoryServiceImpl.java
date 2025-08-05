package billing.backend.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import billing.backend.entity.CategoryEntity;
import billing.backend.io.CategoryRequest;
import billing.backend.io.CategoryResponse;
import billing.backend.repository.CategoryReposetory;
import billing.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CtegoryServiceImpl implements CategoryService{
    private final CategoryReposetory categoryrepo;
    @Override
    public CategoryResponse add(CategoryRequest request) {
        // TODO Auto-generated method stub
       CategoryEntity newCategory =convertToEntity(request);
       newCategory=categoryrepo.save(newCategory);
       return convertToResponse(newCategory);
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
        // TODO Auto-generated method stub
       return CategoryResponse.builder()
                 . categoryId(newCategory.getCategoryId())
                 .name(newCategory.getName())
                 .description(newCategory.getDescription())
                 .bgColor(newCategory.getBgColor())
                 .imgUrl(newCategory.getImgUrl())
                 .createdAt(newCategory.getCreatedAt())
                 .updatedAt(newCategory.getUpdatedAt())
                 .build();
                 
    }

    private CategoryEntity convertToEntity(CategoryRequest request) {
        // TODO Auto-generated method stub
       return CategoryEntity.builder()
            .categoryId(UUID.randomUUID().toString())
            .name(request.getName())
            .description(request.getDescription())
            .bgColor(request.getBgColor())
            .build();
    }
    
    
}
