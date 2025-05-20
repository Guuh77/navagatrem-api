package br.com.navegatrem.controller;

import br.com.navegatrem.entity.EstacaoEntity;
import br.com.navegatrem.service.RotaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/rotas")
@Produces(MediaType.APPLICATION_JSON)
public class RotaResource {

    private final RotaService rotaService;

    @Inject
    public RotaResource(RotaService rotaService) {
        this.rotaService = rotaService;
    }

    @GET
    @Path("/consultar")
    public Response consultarRota(
            @QueryParam("linha") String nomeLinha,
            @QueryParam("origem") String nomeEstacaoOrigem,
            @QueryParam("destino") String nomeEstacaoDestino) {

        if (nomeLinha == null || nomeEstacaoOrigem == null || nomeEstacaoDestino == null ||
                nomeLinha.trim().isEmpty() || nomeEstacaoOrigem.trim().isEmpty() || nomeEstacaoDestino.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Os parâmetros 'linha', 'origem' e 'destino' são obrigatórios.")
                    .build();
        }

        try {
            List<EstacaoEntity> rota = rotaService.consultarRota(nomeLinha, nomeEstacaoOrigem, nomeEstacaoDestino);
            if (rota.isEmpty()) {
                return Response.noContent().build();
            }
            return Response.ok(rota).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (BadRequestException e) { // Se o serviço lançar por algum motivo
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            System.err.println("Erro inesperado ao consultar rota: " + e.getMessage());
            e.printStackTrace(); // Para debug no console do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Ocorreu um erro inesperado ao processar sua solicitação.")
                    .build();
        }
    }
}