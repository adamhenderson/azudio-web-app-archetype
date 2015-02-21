#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.tapestry5.jpa.annotations.CommitAfter;

import ${package}.entities.Person;

/**
 * The REST interface service definition. For Tapestry, the Interface must contain the Annotations 
 */
@Path("/person")
public interface PersonResource {

	@GET
	@Produces({ "application/json" })
	public abstract List<Person> getAllPeople(@QueryParam("sort") String sort);

	@POST
	@Produces({ "application/json" })
	@CommitAfter
	public abstract Person post(Person person);

	@PUT
	@Produces({ "application/json" })
	@Path("{id}")
	@CommitAfter
	public abstract Person action1(Person person, @HeaderParam("Action") String action);

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public abstract Person getPerson(@PathParam("id") Long id);

}