package dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class CompetenceRequestDto {
	
	@NotNull(message = "L'identifiant de l'utilisateur est obligatoire")
	private Long userId;
	
	@NotBlank(message = "Le nom de la compétence est obligatoire")
	private String nom;
	
	@NotBlank(message = "Le niveau de la compétence est obligatoire")
	private String niveau;
	


}
