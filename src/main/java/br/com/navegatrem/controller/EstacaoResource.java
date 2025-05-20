package br.com.navegatrem.controller;

import br.com.navegatrem.entity.EstacaoEntity;
import br.com.navegatrem.service.EstacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/estacoes")
@Produces(MediaType.APPLICATION_JSON)
public class EstacaoResource {

    private final EstacaoService estacaoService;

    @Inject
    public EstacaoResource(EstacaoService estacaoService) {
        this.estacaoService = estacaoService;
    }

    @GET
    public Response listarTodas() {
        List<EstacaoEntity> estacoes = estacaoService.listarTodasEstacoes();
        if (estacoes.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(estacoes).build();
    }

    @GET
    @Path("/{nome}") // O valor entre {} será capturado pelo @PathParam
    public Response buscarPorNome(@PathParam("nome") String nome) {
        EstacaoEntity estacao = estacaoService.buscarEstacaoPorNome(nome);
        return Response.ok(estacao).build();
    }

}