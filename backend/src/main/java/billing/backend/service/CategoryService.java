package billing.backend.service;

import billing.backend.io.CategoryRequest;
import billing.backend.io.CategoryResponse;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request);
}
