// Importações necessárias para utilizar as anotações JPA (Java Persistence API)
package com.simpleform.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Anotação que indica que esta classe é uma entidade JPA
@Entity
// Especifica o nome da tabela no banco de dados
@Table(name = "users_table")
public class UserModel {
    
    // Anotação para definir a chave primária
    @Id
    // Anotação para especificar a estratégia de geração de valor da chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    // Atributos para representar os dados do usuário
    String login;
    String password;
    String email;

    // Métodos getters e setters para os atributos
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Sobrescrita do método hashCode para garantir a correta comparação de objetos
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    // Sobrescrita do método equals para comparar objetos UserModel
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserModel other = (UserModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    // Sobrescrita do método toString para representar o objeto em forma de string
    @Override
    public String toString() {
        return "UserModel [id=" + id + ", login=" + login + ", email=" + email + ", password=" + password + "]";
    }

    public UserModel orElse(Object object) {
        return null;
    }

	
}
