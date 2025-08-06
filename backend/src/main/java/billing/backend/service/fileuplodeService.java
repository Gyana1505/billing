package billing.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface fileuplodeService {
    String UplodeFile(MultipartFile file);
    boolean delete(String imgUrl);
}
