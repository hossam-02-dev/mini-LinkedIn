package com.example.miniLinkedin.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.miniLinkedin.dtos.AuthResponseDto;
import com.example.miniLinkedin.dtos.LoginRequestDto;
import com.example.miniLinkedin.dtos.RegisterRequestDto;
import com.example.miniLinkedin.dtos.UserResponseDto;
import com.example.miniLinkedin.entities.ProfilEntity;
import com.example.miniLinkedin.entities.UserEntity;
import com.example.miniLinkedin.exceptions.AccountNotActivatedException;
import com.example.miniLinkedin.exceptions.ResourceNotFoundException;
import com.example.miniLinkedin.mapping.UserMapper;
import com.example.miniLinkedin.repositories.ProfilRepository;
import com.example.miniLinkedin.repositories.UserRepository;
import com.example.miniLinkedin.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
private final UserRepository userRepository ;
private final PasswordEncoder passwordEncoder;
private final UserMapper userMapper;
private final AuthenticationManager authenticationManager;
private final JwtService jwtService;
private final ProfilRepository profilRepository;

@Transactional
public UserResponseDto inscrire(RegisterRequestDto dto ) {
	
if(userRepository.existsByEmail(dto.getEmail())) {
throw new IllegalStateException("Un compte existe déjà avec cet email : " +dto.getEmail());	
}
String encodedPassword = passwordEncoder.encode(dto.getPassword());

String activationToken = UUID.randomUUID().toString();

UserEntity user = new UserEntity() ;

user.setActivationToken(activationToken);
user.setActive(false);
user.setEmail(dto.getEmail());
user.setFirstName(dto.getFirstName());
user.setLastName(dto.getLastName());
user.setPassword(encodedPassword);
user.setRole(dto.getRole());
user.setCreatedAt(LocalDateTime.now());


UserEntity  savedUser = userRepository.save(user);

ProfilEntity profil = new ProfilEntity();
profil.setUser(savedUser);
profil.setBio("Bienvenue dans mon profil ");
profilRepository.save(profil);

return userMapper.toDto(savedUser);

	
} 
@Transactional
public void activer(String token) {
	UserEntity user = userRepository.findByActivationToken(token)
            .orElseThrow(() -> new ResourceNotFoundException("Le jeton d'activation est invalide ou expiré."));
	
	if (user.isActive()) {
        throw new IllegalStateException("Ce compte est déjà activé.");
    }
	user.setActive(true);
	user.setActivationToken(null);
	userRepository.save(user);
}
@Transactional
public AuthResponseDto connecter (LoginRequestDto dto) {
	authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
	    );
	
	UserEntity user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'email : " + dto.getEmail()));
	
	if (!user.isActive() ) {
        throw new AccountNotActivatedException("Votre compte n'est pas encore activé. Veuillez vérifier vos emails.");
    }
	
	String jwtToken = jwtService.generateToken(user);

  
    return AuthResponseDto.builder()
            .token(jwtToken)
            .role(user.getRole())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();
}



}
