package billing.backend.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import billing.backend.repository.ItemRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import billing.backend.entity.CategoryEntity;
import billing.backend.io.CategoryRequest;
import billing.backend.io.CategoryResponse;
import billing.backend.repository.CategoryReposetory;
import billing.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CtegoryServiceImpl implements CategoryService{
    public final CategoryReposetory categoryrepo;
    private final ItemRepo itemRepo;

    private final fileUplode fileuplode;
    @Override
    public CategoryResponse add(CategoryRequest request,MultipartFile file) {
        // TODO Auto-generated method stub
       String imgUrl=fileuplode.UplodeFile(file);
       CategoryEntity newCategory =convertToEntity(request);
       newCategory.setImgUrl(imgUrl);
       newCategory=categoryrepo.save(newCategory);
       return convertToResponse(newCategory);
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
        // TODO Auto-generated method stub
        Integer count=itemRepo.countByCategoryId(newCategory.getId());
       return CategoryResponse.builder()
                 . categoryId(newCategory.getCategoryId())
                 .name(newCategory.getName())
                 .description(newCategory.getDescription())
                 .bgColor(newCategory.getBgColor())
                 .imgUrl(newCategory.getImgUrl())
                 .createdAt(newCategory.getCreatedAt())
                 .updatedAt(newCategory.getUpdatedAt())
                 .items(count)
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

    @Override
    public List<CategoryResponse> read() {
        // TODO Auto-generated method stub
        return categoryrepo.findAll()
        .stream()
        .map(categoryEntity -> convertToResponse(categoryEntity))
        .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
        // TODO Auto-generated method stub
        CategoryEntity existingCategort =categoryrepo.findByCategoryId(categoryId)
              .orElseThrow(() -> new RuntimeException("Category not fount"));
        fileuplode.delete(existingCategort.getImgUrl());
        categoryrepo.delete(existingCategort);
    }
    
    
}
