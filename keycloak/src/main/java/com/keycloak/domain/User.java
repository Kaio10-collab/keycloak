package com.keycloak.domain;

import java.util.Objects;

public class User {

    /*
    Essa classe é a representação central do negócio, é totalmente independente de qualquer tecnologia ou framework de infraestrutura.
    Isso é fundamental para aplicamos a arquitetura limpa, pois garantimos que o coração da aplicação (o domínio) permaneça intocado, mesmo que a gente decida mudar o banco de dados,
    o framework web ou até mesmo a forma como os dados são serializados/deserializados.
    Essa classe será pura e não terá o uso de lombok.
    */

    private Long id;
    private String username;
    private String email;
    private String password; // Em um sistema real, a senha seria hash e nunca exposta.

    // Construtor completo
    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Construtor para criação (sem ID, pois o banco de dados irá gerá-lo)
    public User(String username, String email, String password) {
        this(null, username, email, password);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters (pois será necessário para modificações, mas para imutabilidade, preferimos métodos com retorno de nova instância)
    // Por simplicidade, vamos usar setters aqui para o CRUD
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}