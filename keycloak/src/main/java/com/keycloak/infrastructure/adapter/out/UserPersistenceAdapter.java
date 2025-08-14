package com.keycloak.infrastructure.adapter.out;

import com.keycloak.application.port.out.UserRepositoryPort;
import com.keycloak.domain.User;
import com.keycloak.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component // Anotação do Spring para indicar que esta classe é um componente gerido
public class UserPersistenceAdapter implements UserRepositoryPort {

    /*
    Aqui vamos criar o elo de ligação entre a nossa camada de Aplicação (que só conhece a interface UserRepositoryPort)
    e a camada de Infraestrutura (que agora tem o JpaUserRepository).
    Vamos "adaptar" as chamadas da camada de Aplicação para os métodos do Spring Data JPA.
    */

    private final JpaUserRepository jpaUserRepository;

    public UserPersistenceAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        // Converte a entidade de domínio (User) para a entidade de persistência (UserEntity)
        UserEntity userEntity = new UserEntity(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        UserEntity savedEntity = jpaUserRepository.save(userEntity);
        // Converte de volta para a entidade de domínio e retorna
        return new User(savedEntity.getId(), savedEntity.getUsername(), savedEntity.getEmail(), savedEntity.getPassword());
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                // Converte a entidade de persistência (UserEntity) para a entidade de domínio (User)
                .map(userEntity -> new User(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword()));
    }

    @Override
    public void deleteById(Long id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
                // Converte a lista de UserEntity para uma lista de User
                .map(userEntity -> new User(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword()))
                .collect(Collectors.toList());
    }
}
