package br.com.navegatrem.service;

import br.com.navegatrem.entity.PassageiroEntity;
import br.com.navegatrem.repository.PassageiroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class PassageiroService {

    private final PassageiroRepository passageiroRepository;

    @Inject
    public PassageiroService(PassageiroRepository passageiroRepository) {
        this.passageiroRepository = passageiroRepository;
    }

    // Metodo para criar um novo passageiro
    public PassageiroEntity criarPassageiro(PassageiroEntity passageiro) {
        passageiroRepository.persist(passageiro); // O ID sera gerado pela sequence
        return passageiro;
    }

    // Metodo para buscar todos os passageiros
    public List<PassageiroEntity> listarTodosPassageiros() {
        return passageiroRepository.listAll();
    }

    // Metodo para buscar um passageiro pelo ID
    public PassageiroEntity buscarPassageiroPorId(Integer id) {
        return passageiroRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Passageiro não encontrado com ID: " + id));
    }

    // Metodo para atualizar um passageiro existente
    public PassageiroEntity atualizarPassageiro(Integer id, PassageiroEntity passageiroAtualizado) {
        PassageiroEntity passageiroExistente = buscarPassageiroPorId(id);

        passageiroExistente.setCpf(passageiroAtualizado.getCpf());
        passageiroExistente.setRg(passageiroAtualizado.getRg());
        passageiroExistente.setNome(passageiroAtualizado.getNome());
        passageiroExistente.setEndereco(passageiroAtualizado.getEndereco());
        passageiroExistente.setTelefone(passageiroAtualizado.getTelefone());

        passageiroRepository.persist(passageiroExistente);
        return passageiroExistente;
    }

    // Metodo para deletar um passageiro pelo ID
    public void deletarPassageiro(Integer id) {
        buscarPassageiroPorId(id);
        passageiroRepository.deleteById(id);
    }
}