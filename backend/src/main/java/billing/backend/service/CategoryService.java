package billing.backend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import billing.backend.io.CategoryRequest;
import billing.backend.io.CategoryResponse;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request,MultipartFile file);
    List<CategoryResponse> read();
    void delete(String categoryId);
}
