package billing.backend.service.impl;

import billing.backend.entity.CategoryEntity;
import billing.backend.entity.ItemEntity;
import billing.backend.io.ItemReq;
import billing.backend.io.ItemRes;
import billing.backend.repository.CategoryReposetory;
import billing.backend.repository.ItemRepo;
import billing.backend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImp implements ItemService {
    private final fileUplode uplode;
    private CategoryReposetory categoryReposetory;
    private final ItemRepo itemRepo;
    @Override
    public ItemRes add(ItemReq req, MultipartFile file) {
        String imgUrl=uplode.UplodeFile(file);
        ItemEntity newItem=convertToEntity(req);
        CategoryEntity category=categoryReposetory.findByCategoryId(req.getCategoryId()).
                orElseThrow(()->new RuntimeException("Category Not Found"+req.getCategoryId()));
        newItem.setCategory(category);
        newItem.setImgUrl(imgUrl);
        newItem=itemRepo.save(newItem);
        return convertToResponse(newItem);


    }

    private ItemRes convertToResponse(ItemEntity newItem) {
        return ItemRes.builder()
                .itemId(newItem.getItemId())
                .name(newItem.getName())
                .price(newItem.getPrice())
                .description(newItem.getDescription())
                .imgUrl(newItem.getImgUrl())
                .categoryName(newItem.getCategory().getName())
                .categoryId(newItem.getCategory().getCategoryId())
                .createdAt((newItem.getCreatedAt()))
                .updatedAt(newItem.getUpdatedAt())
                .build();

    }

    private ItemEntity convertToEntity(ItemReq req) {
        return ItemEntity.builder()
                .itemId(UUID.randomUUID().toString())
                .name(req.getName())
                .price(req.getPrice())
                .description(req.getDescription())
                .build();
    }

    @Override
    public List<ItemRes> fetchItems() {
        return itemRepo.findAll()
                .stream()
                .map(item -> convertToResponse(item))
                .collect(Collectors.toList());
    }

    @Override
    public void deletItem(String itemId) {
     ItemEntity existing=itemRepo.findByItemId(itemId)
             .orElseThrow(()-> new RuntimeException("Item not found"+itemId));
     boolean isDelite=uplode.delete(existing.getImgUrl());
        if (isDelite) {
            itemRepo.delete(existing);

        }else {
            throw new  ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to delete the image");
        }

    }
}
