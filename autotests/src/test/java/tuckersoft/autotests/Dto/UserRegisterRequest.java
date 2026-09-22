package tuckersoft.autotests.Dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;
    private String password;
    private String displayName;
    private String role;
}

