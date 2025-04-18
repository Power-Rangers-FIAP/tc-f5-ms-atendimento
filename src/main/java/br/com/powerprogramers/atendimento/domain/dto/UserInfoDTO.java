package br.com.powerprogramers.atendimento.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDTO {

    private String userName;
    private String userId;
    private String email;
    private String lastName;
    private String firstName;
    private String realmName;
    private String userType;
}