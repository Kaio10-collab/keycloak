package com.keycloak.infrastructure.adapter.in;

import com.keycloak.application.port.in.UserServicePort;
import com.keycloak.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Define que esta classe é um controlador REST
@RequestMapping("/users") // Mapeia todas as requisições para /users
public class UserController {

    /*
    Aqui vamos expor essa funcionalidade através de uma API REST. Este adaptador será o nosso Controlador REST. Ele irá:
    * Receber as requisições HTTP (GET, POST, PUT, DELETE).
    * Converter os dados da requisição (JSON) para objetos de domínio.
    * Chamar os métodos do seu caso de uso (UserServicePort).
    * Converter os resultados de volta para JSON para enviar como resposta.
    */

    private final UserServicePort userServicePort;

    // Injeção de dependência do caso de uso
    public UserController(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userServicePort.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userServicePort.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServicePort.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        // Garantir que o ID do corpo da requisição é o mesmo do path
        user.setId(id);
        User updatedUser = userServicePort.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServicePort.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
