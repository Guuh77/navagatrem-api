package br.com.navegatrem.service;

import br.com.navegatrem.entity.HorarioEntity;
import br.com.navegatrem.repository.HorarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class HorarioService {

    private final HorarioRepository horarioRepository;

    @Inject
    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public HorarioEntity buscarHorarioPorNomeLinha(String nomeLinha) {
        return horarioRepository.findByIdOptional(nomeLinha)
                .orElseThrow(() -> new NotFoundException("Horário não encontrado para a linha: " + nomeLinha));
    }

    public List<HorarioEntity> listarTodosHorarios() {
        return horarioRepository.listAll();
    }
}