package br.com.navegatrem.controller;

import br.com.navegatrem.entity.HorarioEntity;
import br.com.navegatrem.service.HorarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/horarios")
@Produces(MediaType.APPLICATION_JSON)
public class HorarioResource {

    private final HorarioService horarioService;

    @Inject
    public HorarioResource(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GET
    public Response listarTodos() {
        List<HorarioEntity> horarios = horarioService.listarTodosHorarios();
        if (horarios.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(horarios).build();
    }
    @GET
    @Path("/{nomeLinha}")
    public Response buscarPorNomeLinha(@PathParam("nomeLinha") String nomeLinha) {
        HorarioEntity horario = horarioService.buscarHorarioPorNomeLinha(nomeLinha);
        return Response.ok(horario).build();
    }

}