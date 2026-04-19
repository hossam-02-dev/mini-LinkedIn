package dtos;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder

public class ProjetResponseDto {
	
	private Long id;
	
	private String titre;
	
	private Long auteurId;
	
	private String nomAuteur;
	
	private String description;
	
	private String technologies;
	
	private String lienGithub;
	
	private String lienDemo;
	
	private String imageUrl;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDate dateCreation;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDate dateMaj;

}
