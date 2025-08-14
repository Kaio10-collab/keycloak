package com.keycloak.infrastructure.adapter.out;

import com.keycloak.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interface é um componente de repositório do Spring
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    /*
    Veja bem, quando estendemos o JpaRepository, estamos herdando um conjunto completo de métodos para operações CRUD (save, findById, findAll, deleteById, etc.)
    sem escrever uma única linha de código para a implementação.
    Os genéricos UserEntity e Long informam ao Spring o tipo da entidade e o tipo da chave primária, respectivamente.
    */
}
