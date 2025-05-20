package br.com.navegatrem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "passageiro")
public class PassageiroEntity {

    @Id
    @SequenceGenerator(
            name = "passageiroIdSeq",
            sequenceName = "seq_passageiro",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "passageiroIdSeq"
    )
    private Integer id;

    @Column(length = 11, nullable = false)
    @NotBlank(message = "CPF do passageiro não pode ser em branco")
    @Size(min = 11, max = 11, message = "CPF do passageiro deve ter exatamente 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "CPF do passageiro deve conter apenas números")
    private String cpf;

    @Column(length = 20)
    @NotBlank(message = "RG do passageiro não pode ser em branco")
    private String rg;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Nome do passageiro não pode ser em branco")
    @Size(min = 2, max = 100, message = "Nome do passageiro deve ter entre 2 e 100 caracteres")
    private String nome;

    @Column(length = 255)
    @NotBlank(message = "Endereço do passageiro não pode ser em branco")
    @Size(max = 255, message = "Endereço do passageiro deve ter no máximo 255 caracteres")
    private String endereco;

    @Column(length = 20)
    @NotBlank(message = "Telefone do passageiro não pode ser em branco")
    @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$", message = "Formato de telefone inválido para o passageiro")
    private String telefone;

    public PassageiroEntity() {
    }

    public PassageiroEntity(String cpf, String rg, String nome, String endereco, String telefone) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "PassageiroEntity{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}