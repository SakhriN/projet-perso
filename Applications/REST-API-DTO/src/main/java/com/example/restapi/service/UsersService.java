package com.example.restapi.service;

import com.example.restapi.dto.UsersDTO;
import com.example.restapi.entity.Users;
import com.example.restapi.mapper.UsersMapper;
import com.example.restapi.repository.UsersRepository;
import com.example.restapi.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    private final UsersMapper usersMapper;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    public UsersDTO addUsers(UsersDTO usersDTO) {
        Users users = usersMapper.toUsers(usersDTO);
        users.setUsersPassword(passwordEncoder.encode(users.getUsersPassword()));
        return usersMapper.toUsersDto(usersRepository.save(users));
    }

    public Optional<UsersDTO> readOneUser(UUID id) {
        return usersRepository.findById(id).map(usersMapper::toUsersDto);
    }

    public List<UsersDTO> readUsers() {
        return usersRepository.findAll().stream().map(usersMapper::toUsersDto).collect(Collectors.toList());
    }

    public boolean updateUsers(UsersDTO usersDTO) {
        boolean test = false;
        try {
            Users user_originel = usersRepository.findById(usersDTO.getUsersId()).get();
            if (!passwordEncoder.matches(usersDTO.getUsersPassword(), user_originel.getUsersPassword())) {
                // Encoder le nouveau mot de passe
                user_originel.setUsersPassword(passwordEncoder.encode(usersDTO.getUsersPassword()));
            }
            user_originel.setUsersEmail(usersDTO.getUsersEmail());
            user_originel.setUsersAddress(usersDTO.getUsersAddress());
            user_originel.setUsersLastname(usersDTO.getUsersLastname());
            user_originel.setUsersFirstname(usersDTO.getUsersFirstname());
            user_originel.setUsersUsername(usersDTO.getUsersUsername());
            user_originel.setUsersPhonenumber(usersDTO.getUsersPhonenumber());

            usersRepository.save(user_originel);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

    public boolean deleteUsers(UUID id) {
        boolean test = false;
        try {
            usersRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

    public UsersDTO searchUsersCriterias(String firstname, String lastname, String email, String username) {
        return usersMapper.toUsersDto(usersRepository.findUsersByUsersFirstnameAndUsersLastnameAndUsersEmailAndUsersUsername
                (firstname, lastname, email, username));
    }

    public boolean verifyUser(String email, String password) {
        return usersRepository.findByUsersEmail(email)
                .map(user -> passwordEncoder.matches(password, user.getUsersPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public String generateToken(String email, String password) {
        Users user = usersRepository.findByUsersEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        if (!passwordEncoder.matches(password, user.getUsersPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsersEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new User(user.getUsersEmail(), user.getUsersPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        // Convertir les rôles de l'utilisateur en une liste de GrantedAuthority
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getUsersRole().name());
        return List.of(authority);
    }

    public Optional<UsersDTO> findUsersByUsersEmail(String email) {
        Optional<UsersDTO> usersDTO = usersRepository.findByUsersEmail(email).map(usersMapper::toUsersDto);
        return usersDTO;
    }
}
