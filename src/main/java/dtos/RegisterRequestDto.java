package dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder

public class RegisterRequestDto {
	
	@NotBlank(message = "Le prenom est obligatoire")
	private String firstName;
	
	@NotBlank(message = "Le nom est obligatoire")
	private String lastName;
	
	@NotBlank(message = "L'email ne peut pas être vide")
	@Email
	private String email;
	
	@NotBlank(message = "Le mot de passe ne peut pas être vide")
	private String password;
	
	@NotBlank(message = "La confirmation du mot de passe ne peut pas être vide")
	private String confirmPassword;

}
