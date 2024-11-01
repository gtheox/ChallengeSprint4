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

import br.com.fiap.beans.Mecanica;
import br.com.fiap.bo.MecanicaBO;

@Path("/mecanica")
public class MecanicaResource {

    private MecanicaBO mecanicaBO;

    public MecanicaResource() {
        try {
            mecanicaBO = new MecanicaBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todas as oficinas
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarOficinas() {
        try {
            List<Mecanica> oficinas = mecanicaBO.listarOficinas();
            return Response.ok(oficinas).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar oficinas").build();
        }
    }

    // Buscar oficina específica pelo ID
    @GET
    @Path("/{id_oficina}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarOficina(@PathParam("id_oficina") int idOficina) {
        try {
            Mecanica mecanica = mecanicaBO.buscarOficina(idOficina);
            if (mecanica == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Oficina não encontrada").build();
            }
            return Response.ok(mecanica).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar oficina").build();
        }
    }

    // Inserir nova oficina
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirOficina(Mecanica mecanica, @Context UriInfo uriInfo) {
        try {
            String resultado = mecanicaBO.inserirOficina(mecanica);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(mecanica.getIdOficina()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir oficina").build();
        }
    }

    // Atualizar oficina existente
    @PUT
    @Path("/{id_oficina}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarOficina(Mecanica mecanica, @PathParam("id_oficina") int idOficina) {
        try {
            mecanica.setIdOficina(idOficina);
            String resultado = mecanicaBO.atualizarOficina(mecanica);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar oficina").build();
        }
    }

    // Deletar oficina pelo ID
    @DELETE
    @Path("/{id_oficina}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarOficina(@PathParam("id_oficina") int idOficina) {
        try {
            String resultado = mecanicaBO.deletarOficina(idOficina);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar oficina").build();
        }
    }
}
