package billing.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import billing.backend.io.CategoryRequest;
import billing.backend.io.CategoryResponse;
import billing.backend.service.impl.CtegoryServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CtegoryServiceImpl categoryservice;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestBody CategoryRequest request){
          return categoryservice.add(request);
    }
    
    @GetMapping
    public List<CategoryResponse> fetchCategory(){
        return categoryservice.read();
    }
}
