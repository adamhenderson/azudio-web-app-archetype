#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.atmosphere.cpr.AtmosphereFramework;
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

	private AtmosphereFramework framework;

	private Logger log;

	public PersonResourceJPA(AtmosphereFramework framework, EntityManager persistenceService, Logger log) {
		this.framework = framework;
		this.persistenceService = persistenceService;
		this.log = log;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ${package}.services.rest.PersonResource${symbol_pound}getAllPeople(java.lang.String)
	 */
	public List<Person> getAllPeople(String sort) {
		log.debug(sort);

		EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(persistenceService, Person.class);

		if (sort != null) {
			if (sort.startsWith("-")) {
				easyCriteria.orderByDesc(sort.substring(1));
			} else {
				easyCriteria.orderByAsc(sort.substring(1));
			}

		}

		return easyCriteria.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ${package}.services.rest.PersonResource${symbol_pound}post(${package}.entities.Person)
	 */
	public Person post(Person page) {
		log.debug(page.toString());
		persistenceService.persist(page);
		framework.getBroadcasterFactory().lookup("/tapestryatmospherehandlerexample1").broadcast("message=" + page.getName());
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ${package}.services.rest.PersonResource${symbol_pound}getPerson(java.lang.Long)
	 */
	public Person getPerson(Long id) {
		Person page = (Person) persistenceService.find(Person.class, id);
		if (page == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return page;
	}

	public Person action1(Person page, String action) {
		// TODO Auto-generated method stub
		return null;
	}

}