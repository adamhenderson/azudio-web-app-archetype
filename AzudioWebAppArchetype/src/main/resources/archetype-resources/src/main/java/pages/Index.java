#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.pages;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;
import org.apache.tapestry5.services.RequestGlobals;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.Broadcaster;
import org.slf4j.Logger;

import ${package}.entities.Person;
import ${package}.services.Core;
import com.uaihebert.factory.EasyCriteriaFactory;

/**
 * Index page of your application.
 */
public class Index {

	@Inject
	private Logger log;

	@Property
	@Inject
	private Core core;

	@Inject
	private EntityManager persistence;

	public String getApplicationName() {
		return core.getApplicationName();
	}

	public void onSaveComponent() {
		core.saveComponent();
	}

	@Inject
	private RequestGlobals requestGlobals;

	@Inject
	private AtmosphereFramework framework;

	@CommitAfter
	public void onNewEntity() {

		Person p = new Person();
		p.setName("Test: " + new Date().getTime());

		// Save the new Person instance
		persistence.persist(p);

		// Now broadcast that new Person to all subscribers
		Broadcaster broadcaster = framework.getAtmosphereConfig().getBroadcasterFactory().lookup("/tapestryatmospherehandlerexample1");
		broadcaster.broadcast(p);
	}

	public List<Person> getPeople() {
		return EasyCriteriaFactory.createQueryCriteria(persistence, Person.class).getResultList();
	}

	@Property
	private Person p;

	@CommitAfter
	public void onClearAll() {
		persistence.createQuery("delete from Person").executeUpdate();

	}

}