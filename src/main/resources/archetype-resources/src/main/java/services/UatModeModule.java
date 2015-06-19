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
public class UatModeModule {

    private static final Logger log = LoggerFactory.getLogger(UatModeModule.class);

    @Contribute(EntityManagerSource.class)
    public static void configurePersistenceUnitInfos(MappedConfiguration<String, PersistenceUnitConfigurer> cfg) {

        log.debug("Config Persistence");

        PersistenceUnitConfigurer configurer = new PersistenceUnitConfigurer() {

            // /**
            // * MYSQL Implementation
            // * @param unitInfo
            // */
            // public void configure(TapestryPersistenceUnitInfo unitInfo) {
            // unitInfo.persistenceProviderClassName("org.hibernate.ejb.HibernatePersistence");
            // unitInfo.transactionType(PersistenceUnitTransactionType.RESOURCE_LOCAL);
            // unitInfo.addProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            // unitInfo.addProperty("hibernate.connection.url", "jdbc:mysql://localhost/uat-${artifactId}");
            // unitInfo.addProperty("hibernate.connection.username", "${artifactId}"); // <-- CHANGE THIS USERNAME 
            // unitInfo.addProperty("hibernate.connection.password", "${artifactId}"); // <-- CHANGE THIS PASSWORD
            // unitInfo.addProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            // unitInfo.addProperty("hbm2ddl.auto", "create");
            // }

            /**
             * Embedded HSQLDB Implementation
             * 
             * @param unitInfo
             */
            @Override
            public void configure(TapestryPersistenceUnitInfo unitInfo) {
                unitInfo.persistenceProviderClassName("org.hibernate.ejb.HibernatePersistence");
                unitInfo.transactionType(PersistenceUnitTransactionType.RESOURCE_LOCAL);
                unitInfo.addProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
                unitInfo.addProperty("hibernate.connection.url", "jdbc:hsqldb:file:database/uat-${artifactId}");
                unitInfo.addProperty("hibernate.connection.username", "SA");
                unitInfo.addProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                unitInfo.addProperty("hibernate.hbm2ddl.auto", "create");
            }

        };

        cfg.add("${artifactId}", configurer);
    }

    /**
     * Contribute to the Application Defaults
     * 
     * @param configuration
     */
    public static void contributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
        configuration.add(SymbolConstants.PRODUCTION_MODE, false);
    }

}