package com.ufersa.tcc.emprestimoapi.service;

import com.ufersa.tcc.emprestimoapi.model.User;
import com.ufersa.tcc.emprestimoapi.repository.UserRepository;
import com.ufersa.tcc.emprestimoapi.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserPrincipal) {
            return ((UserPrincipal) principal).getUser();
        } else {
            String email = principal.toString();
            return findByEmail(email);
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
    }

    public User findByMatricula(String matricula) {
        return userRepository.findById(matricula)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com matrícula: " + matricula));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByMatricula(String matricula) {
        return userRepository.existsByMatricula(matricula);
    }

    public boolean existsByCpf(String cpf) {
        return userRepository.existsByCpf(cpf);
    }
}
