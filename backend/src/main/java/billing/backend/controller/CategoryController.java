package billing.backend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import billing.backend.io.CategoryRequest;
import billing.backend.io.CategoryResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {
    public CategoryResponse addCategory(@RequestBody CategoryRequest request){

    }
    
}
