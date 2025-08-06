package billing.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public CategoryResponse addCategory(@RequestPart("category") String categoryString,
                                   @RequestPart("file") MultipartFile file){
          ObjectMapper objectmapper=new ObjectMapper();
          CategoryRequest request=null;
          try {
            request=objectmapper.readValue(categoryString, CategoryRequest.class);
            return categoryservice.add(request,file);
          } catch (JsonProcessingException e) {
            // TODO: handle exception
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Exception whele parsing the json");
          }
          
    }
    
    @GetMapping
    public List<CategoryResponse> fetchCategory(){
        return categoryservice.read();
    }
    
    @DeleteMapping("/{categoryId}")
    public void remove(@PathVariable String categoryId){
        try {
            categoryservice.delete(categoryId);
        } catch (Exception e) {
            // TODO: handle exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found "+categoryId);
        }
    }
}
