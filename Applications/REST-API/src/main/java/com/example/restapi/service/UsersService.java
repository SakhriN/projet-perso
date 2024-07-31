package com.example.restapi.service;

import com.example.restapi.entity.Users;
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

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private final UsersRepository usersRepository;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public Users addUsers(Users users) {
        users.setUsersPassword(passwordEncoder.encode(users.getUsersPassword()));
        return usersRepository.save(users);
    }

    public Optional<Users> readOneUser(UUID id) {
        return usersRepository.findById(id);
    }

    public List<Users> readUsers() {
        return usersRepository.findAll();
    }

    public boolean updateUsers(Users users) {
        boolean test = false;
        try {
            Users user_originel = usersRepository.findById(users.getUsersId()).get();
            if (!passwordEncoder.matches(users.getUsersPassword(), user_originel.getUsersPassword())) {
                // Encoder le nouveau mot de passe
                user_originel.setUsersPassword(passwordEncoder.encode(users.getUsersPassword()));
            }
            user_originel.setUsersEmail(users.getUsersEmail());
            user_originel.setUsersAddress(users.getUsersAddress());
            user_originel.setUsersLastname(users.getUsersLastname());
            user_originel.setUsersFirstname(users.getUsersFirstname());
            user_originel.setUsersUsername(users.getUsersUsername());
            user_originel.setUsersPhonenumber(users.getUsersPhonenumber());

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

    public Users searchUsersCriterias(String firstname, String lastname, String email, String username) {
        return usersRepository.findUsersByUsersFirstnameAndUsersLastnameAndUsersEmailAndUsersUsername
                (firstname, lastname, email, username);
    }


    public boolean verifyUser(String email, String password){
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
}
