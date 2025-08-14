package com.keycloak.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Indica que esta classe é uma entidade JPA e será mapeada para uma tabela
@Table(name = "users") // Define o nome da tabela no banco de dados
@Data // Adiciona getters, setters, toString, equals e hashCode do Lombok
@NoArgsConstructor // Construtor sem argumentos, exigido pelo JPA
@AllArgsConstructor // Construtor com todos os argumentos, útil para testes
public class UserEntity {

    /*
    Vamos criar uma classe que será mapeada para uma tabela no banco de dados. É aqui que usaremos o JPA e o Lombok.
    */

    @Id // Indica que este é o campo de chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de ID
    private Long id;
    private String username;
    private String email;
    private String password;

}
