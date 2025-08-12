package billing.backend.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class cloudinary {
    @Value("${cloudinary.api_key}")
    private String ApiKey;
    @Value("${cloudinary.api_secret}")
    private String secret;
    @Value("${cloudinary.cloud_name}")
    private String cloudinaryName;
    
    @Bean
    public Cloudinary cloudinar(){
        Map<String,String> configfile=new HashMap<>();
        configfile.put("api_key", ApiKey);
        configfile.put("api_secret",secret);
        configfile.put("cloud_name", cloudinaryName);
        return new Cloudinary(configfile);
    }
}
