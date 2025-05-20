package br.com.navegatrem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "horario")
public class HorarioEntity {

    @Id
    @Column(name = "nome_linha", length = 50, nullable = false, unique = true)
    private String nomeLinha; // ex: "Linha 8", "Linha 9"

    @Column(name = "tempo_espera", length = 50)
    private String tempoEspera;

    @Column(name = "horario_funcionamento", length = 50)
    private String horarioFuncionamento;

    public HorarioEntity() {
    }

    public HorarioEntity(String nomeLinha, String tempoEspera, String horarioFuncionamento) {
        this.nomeLinha = nomeLinha;
        this.tempoEspera = tempoEspera;
        this.horarioFuncionamento = horarioFuncionamento;
    }

    // Getters e Setters para todos os campos

    public String getNomeLinha() {
        return nomeLinha;
    }

    public void setNomeLinha(String nomeLinha) {
        this.nomeLinha = nomeLinha;
    }

    public String getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(String tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    @Override
    public String toString() {
        return "HorarioEntity{" +
                "nomeLinha='" + nomeLinha + '\'' +
                ", tempoEspera='" + tempoEspera + '\'' +
                ", horarioFuncionamento='" + horarioFuncionamento + '\'' +
                '}';
    }
}