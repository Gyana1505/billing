package billing.backend.controller;

import billing.backend.io.ItemReq;
import billing.backend.io.ItemRes;
import billing.backend.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/itemAdd")
    public ItemRes addItem(@RequestPart("item") String req,
                           @RequestPart("file")MultipartFile file){
        ObjectMapper objectMapper=new ObjectMapper();
        ItemReq itemRequest=null;
        try {
            itemRequest=objectMapper.readValue(req,ItemReq.class);
            return itemService.add(itemRequest,file);
        }catch (JsonProcessingException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error occured");
        }
    }

    @GetMapping("/items")
    public List<ItemRes> readItem(){
        return itemService.fetchItems();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/admin/item/{itemId}")
    public void deliteItem(@PathVariable String itemId){
        try {
            itemService.deletItem(itemId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not found");
        }
    }
}
