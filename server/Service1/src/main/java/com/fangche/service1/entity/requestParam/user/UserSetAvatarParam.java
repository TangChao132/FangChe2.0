package com.fangche.service1.entity.requestParam.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserSetAvatarParam {
    @NotBlank
    private MultipartFile file;
}
