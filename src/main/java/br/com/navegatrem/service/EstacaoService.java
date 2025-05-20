package br.com.navegatrem.service;

import br.com.navegatrem.entity.EstacaoEntity;
import br.com.navegatrem.repository.EstacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class EstacaoService {

    private final EstacaoRepository estacaoRepository;

    @Inject
    public EstacaoService(EstacaoRepository estacaoRepository) {
        this.estacaoRepository = estacaoRepository;
    }

    public List<EstacaoEntity> listarTodasEstacoes() {
        return estacaoRepository.listAll();
    }

    public EstacaoEntity buscarEstacaoPorNome(String nome) {
        return estacaoRepository.findByIdOptional(nome)
                .orElseThrow(() -> new NotFoundException("Estação não encontrada com o nome: " + nome));
    }

}