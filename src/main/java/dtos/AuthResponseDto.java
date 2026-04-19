package dtos;

import enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder

public class AuthResponseDto {
	
private String token;
private String firstName;
private String lastName;
private String email;
private Role role;
}
