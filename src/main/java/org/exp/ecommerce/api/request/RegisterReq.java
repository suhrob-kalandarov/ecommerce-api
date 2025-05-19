package org.exp.ecommerce.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReq {
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private MultipartFile profileImage;
}