package billing.backend.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import billing.backend.service.fileuplodeService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class fileUplode implements fileuplodeService{
     private Cloudinary cloudinary;
    @Override
    public String UplodeFile(MultipartFile file) {
        // TODO Auto-generated method stub
       try {
        Map uplodeResult= cloudinary.uploader().upload(file.getBytes(),
       ObjectUtils.asMap("resource_type","auto"));
       return uplodeResult.get("secure_url").toString();
       } catch (IOException e) {
        throw new RuntimeException("Failed to upload file to Cloudinary", e);
       }
    }

    @Override
    public boolean delete(String imgUrl) {
           try {
            Map result = cloudinary.uploader().destroy(imgUrl, ObjectUtils.emptyMap());
            String status=(String)result.get("result"); // returns "ok" if successful
            return "ok".equalsIgnoreCase(status);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file from Cloudinary", e);
        }
    }
    
    
}
