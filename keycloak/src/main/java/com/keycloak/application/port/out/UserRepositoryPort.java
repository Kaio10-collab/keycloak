package com.keycloak.application.port.out;

import com.keycloak.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    /*
    esta é uma interface de "porta de saída" para a camada de aplicação que comentei na classe UserServicePort
    */
    User save(User user); // Salva ou atualiza um usuário no "repositório".
    Optional<User> findById(Long id); // Encontra um usuário pelo ID.
    void deleteById(Long id); // Deleta um usuário pelo ID.
    List<User> findAll(); // Retorna todos os usuários.

}
