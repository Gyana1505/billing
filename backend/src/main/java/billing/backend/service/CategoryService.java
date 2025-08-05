package billing.backend.service;

import java.util.List;

import billing.backend.io.CategoryRequest;
import billing.backend.io.CategoryResponse;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request);
    List<CategoryResponse> read();
    void delete(String categoryId);
}
