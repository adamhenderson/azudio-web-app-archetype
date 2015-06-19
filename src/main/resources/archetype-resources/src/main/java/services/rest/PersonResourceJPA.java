#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.rest;

import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.tapestry5.annotations.Log;
//import org.atmosphere.cpr.AtmosphereFramework;
import org.slf4j.Logger;

import ${package}.entities.Person;
import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

/**
 * This is the implementation of the REST Service. This particular implementation has the following features:
 * <ul>
 * <li>Is able to save objects to the database via the injected JPA EntityManager</li>
 * <li>Can also perform broadcasts to any connected clients for real-time updates using the Atmosphere Framework by calling methods on the injected AtmosphereFramework</li>
 * <li>Uses the EasyCriteria library to make JPA queries a little simpler</li>
 * </ul>
 */
public class PersonResourceJPA implements PersonResource {

    private EntityManager persistenceService;

    //private AtmosphereFramework framework;

    private Logger log;

    public PersonResourceJPA(EntityManager persistenceService, Logger log) {
        this.persistenceService = persistenceService;
        this.log = log;
    }
    
//    public PersonResourceJPA(AtmosphereFramework framework, EntityManager persistenceService, Logger log) {
//        this.framework = framework;
//        this.persistenceService = persistenceService;
//        this.log = log;
//    }

    /*
     * (non-Javadoc)
     * 
     * @see ${package}.rest.PersonResource${symbol_pound}getAll(java.lang.String)
     */
    @Log
    public Response getAll(String sort) {
        log.debug(sort);

        EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(persistenceService, Person.class);

        if (sort != null) {
            if (sort.startsWith("-")) {
                easyCriteria.orderByDesc(sort.substring(1));
            } else {
                easyCriteria.orderByAsc(sort.substring(1));
            }

        }
        return Response.ok(easyCriteria.getResultList()).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ${package}.rest.PersonResource${symbol_pound}addNew(${package}.entities.Person)
     */
    public Response addNew(Person person) {

        log.debug("New Person id:" + person.getId());
        persistenceService.persist(person);
        log.debug("New Person id:" + person.getId());

        //framework.getBroadcasterFactory().lookup("/tapestryatmospherehandlerexample1").broadcast("message=" + page.getName());

        return Response.ok(person).link("/rest/people/" + person.getId(), "self").status(201).build();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see ${package}.rest.PersonResource${symbol_pound}update(${package}.entities.Person)
     */
    public Response update(Person person) {
        log.debug("Update Person id:" + person.getId());
        persistenceService.merge(person);
        log.debug("Update Person id:" + person.getId());

        //framework.getBroadcasterFactory().lookup("/tapestryatmospherehandlerexample1").broadcast("message=" + page.getName());

        return Response.ok(person).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ${package}.rest.PersonResource${symbol_pound}find(java.lang.Long)
     */
    @Log
    public Response find(Long id) {
        Person person = (Person) persistenceService.find(Person.class, id);
        if (person == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(person).build();
    }



}