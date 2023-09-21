package com.simpleform.service;
import java.security.NoSuchAlgorithmException;

// Importações necessárias
import org.springframework.stereotype.Service;
import com.simpleform.model.UserModel;
import com.simpleform.repository.UserRepository;
import com.simpleform.util.Util;

// Anotação que marca esta classe como um componente de serviço gerenciado pelo Spring
@Service
public class UserService {

    // Injeção de dependência do repositório de usuários (UserRepository)
    private final UserRepository userRepository;

    // Construtor que recebe o UserRepository como parâmetro e o injeta no serviço
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Método para registrar um novo usuário
    public UserModel registerUser(String login, String password, String email) throws Exception{
        if (login == null && password == null) {
            return null; // Se login ou senha forem nulos, retornar null (possivelmente um tratamento de erro)
        } else {
            // Criar um novo objeto UserModel e definir os valores
            if (userRepository.findByEmail(email).isPresent()){
                System.out.println("Email duplicado");
                return null;
            }
            
            
 
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            
            userModel.setPassword(Util.md5(userModel.getPassword())); 
            
            // Salvar o novo usuário no banco de dados usando o UserRepository
            return userRepository.save(userModel);
        }
    }

    // Método para autenticar um usuário com base no login e senha
    public UserModel authenticate(String email, String password){
        // Use o método findByLoginAndPassword do UserRepository para encontrar o usuário
        // Se encontrado, retorne o usuário; caso contrário, retorne null
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}

