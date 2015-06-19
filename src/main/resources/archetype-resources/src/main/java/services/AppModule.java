#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

import javax.persistence.spi.PersistenceUnitTransactionType;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.IOCSymbols;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.ImportModule;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.jpa.EntityManagerSource;
import org.apache.tapestry5.jpa.JpaTransactionAdvisor;
import org.apache.tapestry5.jpa.PersistenceUnitConfigurer;
import org.apache.tapestry5.jpa.TapestryPersistenceUnitInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.azudio.tapestry.atmosphere.module.AtmosphereModule;
import ${package}.services.rest.PersonResource;
import ${package}.services.rest.PersonResourceJPA;

/**
 * Module to configure your Tapestry Application.
 */
@ImportModule({ /* Uncomment to enable Atmosphere: AtmosphereModule.class */})
public class AppModule {

    private static final Logger log = LoggerFactory.getLogger(AppModule.class);

    // Contributions to EntityManagerSource are in the [MODE]ModeModule.java files
    // IntegrationModeModule.java
    // UatModeModule.java
    // ProductionModeModule.java - This module will be loaded if the tapestry.execution-mode JVM property is not set
    
    /**
     * Contribute to the Application Defaults
     * 
     * @param configuration
     */
    public static void contributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
        configuration.add(SymbolConstants.ENABLE_HTML5_SUPPORT, true);
        configuration.add(SymbolConstants.FORM_CLIENT_LOGIC_ENABLED, false);
        configuration.add(SymbolConstants.INCLUDE_CORE_STACK, false);
        configuration.add(SymbolConstants.ENABLE_PAGELOADING_MASK, false);
        configuration.add(SymbolConstants.COMBINE_SCRIPTS, true);
        configuration.add(IOCSymbols.THREAD_POOL_CORE_SIZE, 7);
    }

    /**
     * Binds Services
     * 
     * @param binder
     */
    public static void bind(ServiceBinder binder) {
        binder.bind(AppCore.class, AppCoreImpl.class);
        binder.bind(PersonResource.class, PersonResourceJPA.class);
    }

    /**
     * REST: Contribute the proxies services for Live Class Reloading
     * 
     * @param singletons
     * @param personResource
     */
    @Contribute(javax.ws.rs.core.Application.class)
    public static void configureRestResources(Configuration<Object> singletons, PersonResource personResource) {
        singletons.add(personResource);
    }

    /**
     * REST: To support the @CommitAfter annotation, the *Resource classes are advised wrapping in a transaction
     * 
     * @param advisor
     * @param receiver
     */
    // Uncomment to enable REST Example
    @Match({ "*Resource" })
    public static void adviseTransactionally(JpaTransactionAdvisor advisor, MethodAdviceReceiver receiver) {
        advisor.addTransactionCommitAdvice(receiver);
    }

}