package billing.backend.service;

import billing.backend.io.ItemReq;
import billing.backend.io.ItemRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    ItemRes add(ItemReq req, MultipartFile file);
    List<ItemRes> fetchItems();
    void deletItem(String itemId);
}
