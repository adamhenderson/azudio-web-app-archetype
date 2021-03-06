#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.IOCSymbols;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.ImportModule;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.jpa.JpaTransactionAdvisor;

import ${package}.modules.KillJSModule;
import ${package}.services.rest.PersonResource;
import ${package}.services.rest.PersonResourceJPA;

/**
 * Module to configure your Tapestry Application.
 */
@ImportModule({ KillJSModule.class })
public final class AppModule {

    private AppModule() {
    }

    // Contributions to EntityManagerSource are in the [MODE]ModeModule.java files
    // IntegrationModeModule.java
    // UatModeModule.java
    // ProductionModeModule.java - This module will be loaded if the tapestry.execution-mode JVM property is not set
    
    /**
     * Contribute to the Application Defaults
     * 
     * @param configuration
     */
    public static void contributeApplicationDefaults(final MappedConfiguration<String, Object> configuration) {
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
    public static void bind(final ServiceBinder binder) {
        binder.bind(AppCore.class, AppCoreImpl.class);
        
        // Rest Services
        binder.bind(PersonResource.class, PersonResourceJPA.class);
    }

    /**
     * REST: Contribute the proxies services for Live Class Reloading. (Not using Tynamo Auto-Discovery)
     * 
     * @param singletons
     * @param personResource
     */
    @Contribute(javax.ws.rs.core.Application.class)
    public static void configureRestResources(final Configuration<Object> singletons, final PersonResource personResource) {
        singletons.add(personResource);
    }

    /**
     * REST: To support the @CommitAfter annotation, the *Resource classes are advised wrapping in a transaction.  (Not using Tynamo Auto-Discovery). JPA Implementation.
     * 
     * @param advisor
     * @param receiver
     */
    @Match({ "*Resource" })
    public static void adviseTransactionally(final JpaTransactionAdvisor advisor, final MethodAdviceReceiver receiver) {
        advisor.addTransactionCommitAdvice(receiver);
    }

}