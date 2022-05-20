package top.byteinfo.cd.auth.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AuthVO {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;


}
