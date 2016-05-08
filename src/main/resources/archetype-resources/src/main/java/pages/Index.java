#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.pages;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;
import org.slf4j.Logger;

import ${package}.entities.Person;
import ${package}.services.AppCore;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

/**
 * Index page of your application.
 */
public class Index {

    @Inject
    private Logger log;

    @Property
    @Inject
    private AppCore appCore;

    @Inject
    @PersistenceContext(unitName = "${artifactId}")
    private EntityManager persistence;

    @Property
    private Person p;

    @Inject
    @Property(read = true)
    private ComponentResources cr;

    public String getApplicationName() {
        return appCore.getApplicationName();
    }

    @CommitAfter
    public void onNewEntity() {

        p = new Person();
        p.setName("Test: " + new Date().getTime());

        // Save the new Person instance
        persistence.persist(p);

    }

    public List<Person> getPeople() {
        return UaiCriteriaFactory.createQueryCriteria(persistence, Person.class).getResultList();
    }

    @CommitAfter
    public Object onClearAll() {
        persistence.createQuery("delete from Person").executeUpdate();
        return null;
    }

}