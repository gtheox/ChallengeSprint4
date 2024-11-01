package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.beans.Diagnostico;
import br.com.fiap.bo.DiagnosticoBO;

@Path("/diagnostico")
public class DiagnosticoResource {

    private DiagnosticoBO diagnosticoBO;

    public DiagnosticoResource() {
        try {
            diagnosticoBO = new DiagnosticoBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os diagnósticos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDiagnosticos() {
        try {
            List<Diagnostico> diagnosticos = diagnosticoBO.listarDiagnosticos();
            return Response.ok(diagnosticos).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar diagnósticos").build();
        }
    }

    // Buscar diagnóstico específico pelo ID
    @GET
    @Path("/{id_problema}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarDiagnostico(@PathParam("id_problema") int idProblema) {
        try {
            Diagnostico diagnostico = diagnosticoBO.buscarDiagnostico(idProblema);
            if (diagnostico == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Diagnóstico não encontrado").build();
            }
            return Response.ok(diagnostico).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar diagnóstico").build();
        }
    }

    // Inserir diagnóstico
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirDiagnostico(Diagnostico diagnostico, @Context UriInfo uriInfo) {
        try {
            String resultado = diagnosticoBO.inserirDiagnostico(diagnostico);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(diagnostico.getIdProblema()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir diagnóstico").build();
        }
    }

    // Atualizar diagnóstico
    @PUT
    @Path("/{id_problema}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDiagnostico(Diagnostico diagnostico, @PathParam("id_problema") int idProblema) {
        try {
            diagnostico.setIdProblema(idProblema);
            String resultado = diagnosticoBO.atualizarDiagnostico(diagnostico);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar diagnóstico").build();
        }
    }

    // Deletar diagnóstico pelo ID
    @DELETE
    @Path("/{id_problema}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarDiagnostico(@PathParam("id_problema") int idProblema) {
        try {
            String resultado = diagnosticoBO.deletarDiagnostico(idProblema);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar diagnóstico").build();
        }
    }
}
