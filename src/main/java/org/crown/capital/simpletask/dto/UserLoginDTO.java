package org.crown.capital.simpletask.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private String userName;
    private String password;
}
