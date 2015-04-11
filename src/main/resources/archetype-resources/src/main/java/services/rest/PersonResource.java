#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.tapestry5.jpa.annotations.CommitAfter;

import ${package}.entities.Person;

/**
 * The REST interface service definition. For Tapestry, the Interface must contain the Annotations. The resource is the plural form of the resource, in this case it is 'people' rather than the singular 'person' or 'persons' 
 */
@Path("/people")
public interface PersonResource {

	/**
	 * Returns an array of all the Person entities currently stored
	 */
	@GET
	@Produces({ "application/json" })
	public abstract Response getAll(@QueryParam("sort") String sort);

	/**
	 * Adds a new Person instance
	 */
	@POST
	@Produces({ "application/json" })
	@CommitAfter
	public abstract Response addNew(Person person);

	/**
	 * Updates details about a Person
	 */
	@PUT
	@Produces({ "application/json" })
	@Path("{id}")
	@CommitAfter
	public abstract Response update(Person person);

	/**
	 * Attempts to retrieve the Person represented by an Id
	 */
	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public abstract Response find(@PathParam("id") Long id);

}