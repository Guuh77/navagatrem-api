package br.com.navegatrem.controller;

import br.com.navegatrem.entity.FuncionarioEntity;
import br.com.navegatrem.service.FuncionarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    private final FuncionarioService funcionarioService;

    @Inject
    public FuncionarioResource(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GET
    public Response listarTodos() {
        List<FuncionarioEntity> funcionarios = funcionarioService.listarTodosFuncionarios();
        return Response.ok(funcionarios).build();
    }

    @GET
    @Path("/{cpf}")
    public Response buscarPorCpf(@PathParam("cpf") String cpf) {
        FuncionarioEntity funcionario = funcionarioService.buscarFuncionarioPorCpf(cpf);
        return Response.ok(funcionario).build();
    }

    @POST
    @Transactional
    public Response criarFuncionario(@Valid FuncionarioEntity funcionario) {
        FuncionarioEntity novoFuncionario = funcionarioService.criarFuncionario(funcionario);
        return Response.status(Response.Status.CREATED).entity(novoFuncionario).build();
    }

    @PUT
    @Path("/{cpf}")
    @Transactional
    public Response atualizarFuncionario(@PathParam("cpf") String cpf, @Valid FuncionarioEntity funcionario) {
        FuncionarioEntity funcionarioAtualizado = funcionarioService.atualizarFuncionario(cpf, funcionario);
        return Response.ok(funcionarioAtualizado).build();
    }

    @DELETE
    @Path("/{cpf}")
    @Transactional
    public Response deletarFuncionario(@PathParam("cpf") String cpf) {
        funcionarioService.deletarFuncionario(cpf);
        return Response.noContent().build();
    }
}