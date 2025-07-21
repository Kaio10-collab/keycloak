package com.keycloak.application.port.in;

import com.keycloak.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserServicePort { // Esta interface define o contrato para as operações de gerenciamento de usuários.

    /*
    Vamos criar uma interface que define as operações que nosso serviço de usuário irá expor.
    Vamos criar também as "Portas", elas são interfaces que definem contratos de comunicação entre a camada de Aplicação e as camadas externas (Infraestrutura).
    Existem dois tipos principais:
    Portas de Entrada (in): Definem o que a camada de Aplicação oferece. São as interfaces que os adaptadores de entrada (como seus controladores REST) vão chamar.
    No caso, será essa classe aqui.
    Portas de Saída (out): Definem o que a camada de Aplicação precisa do mundo externo. São as interfaces que a camada de Aplicação usa para interagir com adaptadores de saída (como repositórios de banco de dados ou clientes de serviços externos).
    No caso será na classe UserRepositoryPort, onde faremos a persistência.
    */

    User createUser(User user); // Cria um novo usuário
    Optional<User> getUserById(Long id); // Busca um usuário pelo ID, retornando um Optional para indicar que o usuário pode não ser encontrado.
    User updateUser(User user); // Atualiza um usuário existente.
    void deleteUser(Long id); // Exclui um usuário pelo ID.
    List<User> getAllUsers(); // Retorna uma lista de todos os usuários.

}
