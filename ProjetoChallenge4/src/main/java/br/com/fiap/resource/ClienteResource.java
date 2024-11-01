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

import br.com.fiap.beans.Cliente;
import br.com.fiap.bo.ClienteBO;

@Path("/cliente")
public class ClienteResource {
    
    private ClienteBO clienteBO;

    public ClienteResource() {
        try {
            clienteBO = new ClienteBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Selecionar todos os clientes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarClientes() {
        try {
            List<Cliente> clientes = clienteBO.listarClientes();
            return Response.ok(clientes).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar clientes").build();
        }
    }

    // Buscar cliente específico pelo ID
    @GET
    @Path("/{id_cliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCliente(@PathParam("id_cliente") int idCliente) {
        try {
            Cliente cliente = clienteBO.buscarCliente(idCliente);
            if (cliente == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
            }
            return Response.ok(cliente).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar cliente").build();
        }
    }
    
    // Inserir cliente
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCliente(Cliente cliente, @Context UriInfo uriInfo) {
        try {
            String resultado = clienteBO.inserirCliente(cliente);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(cliente.getIdCliente()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir cliente").build();
        }
    }
    
    // Atualizar cliente
    @PUT
    @Path("/{id_cliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarCliente(Cliente cliente, @PathParam("id_cliente") int idCliente) {
        try {
            cliente.setIdCliente(idCliente);
            String resultado = clienteBO.atualizarCliente(cliente);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar cliente").build();
        }
    }
    
    // Deletar cliente pelo ID
    @DELETE
    @Path("/{id_cliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarCliente(@PathParam("id_cliente") int idCliente) {
        try {
            String resultado = clienteBO.deletarCliente(idCliente);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar cliente").build();
        }
    }
}
