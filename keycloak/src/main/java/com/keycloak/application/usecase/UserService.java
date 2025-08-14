package com.keycloak.application.usecase;

import com.keycloak.application.port.in.UserServicePort;
import com.keycloak.application.port.out.UserRepositoryPort;
import com.keycloak.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta classe é um componente de serviço do Spring e pode ser injetada
public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort; // Porta de saída para persistência

    // Injeção de dependência via construtor
    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUser(User user) {
        // Aqui poderiam vir regras de negócio antes de salvar, por exemplo:
        // - Validar o formato do email
        // - Criptografar a senha (antes de passar para o repositório)
        // - Verificar se o username já existe (exemplo abaixo)

        // Exemplo de regra de negócio simples: verificar se o username já existe
        if (userRepositoryPort.findAll().stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
            throw new IllegalArgumentException("Username already exists.");
        }

        return userRepositoryPort.save(user); // Delega a persistência para a porta de saída
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepositoryPort.findById(id); // Delega a busca para a porta de saída
    }

    @Override
    public User updateUser(User user) {
        // Em uma atualização, primeiro buscaríamos o usuário existente para garantir que ele existe e depois aplicaríamos as modificações.
        // Por simplicidade, vamos assumir que o ID do usuário no objeto 'user' é válido.

        // Exemplo de uma regra de negócio: garantir que o usuário a ser atualizado existe
        userRepositoryPort.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + user.getId() + " not found."));

        return userRepositoryPort.save(user); // Delega a atualização para a porta de saída
    }

    @Override
    public void deleteUser(Long id) {
        // Exemplo de regra de negócio: verificar se o usuário existe antes de deletar
        userRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found for deletion."));

        userRepositoryPort.deleteById(id); // Delega a exclusão para a porta de saída
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.findAll(); // Delega a busca para a porta de saída
    }
}
