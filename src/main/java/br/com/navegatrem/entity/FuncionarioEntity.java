package br.com.navegatrem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "funcionario")
public class FuncionarioEntity {

    @Id
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    @NotBlank(message = "CPF não pode ser em branco")
    @Size(min = 11, max = 11, message = "CPF deve ter exatamente 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas números") // garante que tenha 11 digitos numericos
    private String cpf;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank(message = "Nome não pode ser em branco")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Column(name = "cargo", length = 50, nullable = false)
    @NotBlank(message = "Cargo não pode ser em branco")
    @Size(max = 50, message = "Cargo deve ter no máximo 50 caracteres")
    private String cargo;

    @Column(name = "telefone", length = 20) // ex: (XX) XXXXX-XXXX ou XXXXXXXXXXX
    @NotBlank(message = "Telefone não pode ser em branco")
    @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$", message = "Formato de telefone inválido")
    private String telefone;

    public FuncionarioEntity() {
    }

    public FuncionarioEntity(String cpf, String nome, String cargo, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
        this.telefone = telefone;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "FuncionarioEntity{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}