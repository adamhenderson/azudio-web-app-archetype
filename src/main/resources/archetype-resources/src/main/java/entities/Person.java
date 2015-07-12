#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Example Person Entity
 * 
 * The entity is annotated with a XMLRootElement so it can be serialised for REST interaction.
 * 
 */
@Entity
@XmlRootElement(name = "Person")
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private static final long serialVersionUID = 1L;

    public Person() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
