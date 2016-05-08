#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.services.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

import ${package}.entities.Person;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

/**
 * This is the implementation of the REST Service. This particular implementation has the following features:
 * <ul>
 * <li>Is able to save objects to the database via the injected JPA EntityManager</li>
 * <li>Can also perform broadcasts to any connected clients for real-time updates using the Atmosphere Framework by calling methods on the injected AtmosphereFramework</li>
 * <li>Uses the EasyCriteria library to make JPA queries a little simpler</li>
 * </ul>
 */
public class PersonResourceJPA implements PersonResource {

    private final Logger log;

    @Inject
    @PersistenceContext(unitName = "${artifactId}")
    private EntityManager persistenceService;

    public PersonResourceJPA(final Logger log) {
        this.log = log;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ${package}.rest.PersonResource${symbol_pound}get(java.lang.String)
     */
    @Override
    public Response get(final String sort) {
        log.debug(sort);
        final UaiCriteria<Person> criteria = UaiCriteriaFactory.createQueryCriteria(persistenceService, Person.class);
        if (sort != null) {
            if (sort.startsWith("-")) {
                criteria.orderByDesc(sort.substring(1));
            } else {
                criteria.orderByAsc(sort.substring(1));
            }
        }
        return Response.ok(criteria.getResultList()).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ${package}.rest.PersonResource${symbol_pound}addNew(${package}.entities.Person)
     */
    @Override
    public Response addNew(final Person person) {

        log.debug("New Person id:" + person.getId());
        persistenceService.persist(person);
        log.debug("New Person id:" + person.getId());

        return Response.ok(person).link("/rest/people/" + person.getId(), "self").status(201).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ${package}.rest.PersonResource${symbol_pound}update(${package}.entities.Person)
     */
    @Override
    public Response update(final Person person) {
        log.debug("Update Person id:" + person.getId());
        persistenceService.merge(person);
        log.debug("Update Person id:" + person.getId());

        return Response.ok(person).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ${package}.rest.PersonResource${symbol_pound}find(java.lang.Long)
     */
    @Override
    public Response find(final Long id) {
        final Person person = persistenceService.find(Person.class, id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();
    }

}