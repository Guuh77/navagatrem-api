package br.com.navegatrem.controller;

import br.com.navegatrem.entity.PassageiroEntity;
import br.com.navegatrem.service.PassageiroService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/passageiros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PassageiroResource {

    private final PassageiroService passageiroService;

    @Inject
    public PassageiroResource(PassageiroService passageiroService) {
        this.passageiroService = passageiroService;
    }

    @GET
    public Response listarTodos() {
        List<PassageiroEntity> passageiros = passageiroService.listarTodosPassageiros();
        return Response.ok(passageiros).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Integer id) {
        PassageiroEntity passageiro = passageiroService.buscarPassageiroPorId(id);
        return Response.ok(passageiro).build();
    }

    @POST
    @Transactional
    public Response criarPassageiro(@Valid PassageiroEntity passageiro) {
        PassageiroEntity novoPassageiro = passageiroService.criarPassageiro(passageiro);
        return Response.status(Response.Status.CREATED).entity(novoPassageiro).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarPassageiro(@PathParam("id") Integer id, @Valid PassageiroEntity passageiro) {
        PassageiroEntity passageiroAtualizado = passageiroService.atualizarPassageiro(id, passageiro);
        return Response.ok(passageiroAtualizado).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarPassageiro(@PathParam("id") Integer id) {
        passageiroService.deletarPassageiro(id);
        return Response.noContent().build();
    }
}