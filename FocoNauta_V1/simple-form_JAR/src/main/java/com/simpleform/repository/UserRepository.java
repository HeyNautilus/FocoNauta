// Importações necessárias
package com.simpleform.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simpleform.model.UserModel;

@Repository
// Interface que estende JpaRepository, permitindo operações de CRUD no banco de dados para UserModel
public interface UserRepository extends JpaRepository<UserModel, Integer>{

	// Método personalizado que busca um usuário pelo login e senha
    Optional<UserModel> findByEmailAndPassword(String email, String password);

    // Método personalizado que busca um usuário pelo email
    Optional<UserModel> findByEmail(String email);
}


