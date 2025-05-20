package br.com.navegatrem.service;

import br.com.navegatrem.entity.FuncionarioEntity;
import br.com.navegatrem.repository.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    @Inject
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    // Metodo para criar um novo funcionario
    public FuncionarioEntity criarFuncionario(FuncionarioEntity funcionario) {
        funcionarioRepository.persist(funcionario);
        return funcionario;
    }

    // Metodo para buscar todos os funcionarios
    public List<FuncionarioEntity> listarTodosFuncionarios() {
        return funcionarioRepository.listAll();
    }

    // Metodo para buscar um funcionario pelo CPF (que é o ID)
    public FuncionarioEntity buscarFuncionarioPorCpf(String cpf) {
        return funcionarioRepository.findByIdOptional(cpf)
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado com CPF: " + cpf));
    }

    public FuncionarioEntity atualizarFuncionario(String cpf, FuncionarioEntity funcionarioAtualizado) {
        FuncionarioEntity funcionarioExistente = buscarFuncionarioPorCpf(cpf);
        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setCargo(funcionarioAtualizado.getCargo());
        funcionarioExistente.setTelefone(funcionarioAtualizado.getTelefone());
        funcionarioRepository.persist(funcionarioExistente);
        return funcionarioExistente;
    }

    // Metodo para deletar um funcionario pelo CPF
    public void deletarFuncionario(String cpf) {
        buscarFuncionarioPorCpf(cpf);
        funcionarioRepository.deleteById(cpf);
    }
}