#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.modules;

import javax.persistence.spi.PersistenceUnitTransactionType;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.jpa.EntityManagerSource;
import org.apache.tapestry5.jpa.PersistenceUnitConfigurer;
import org.apache.tapestry5.jpa.TapestryPersistenceUnitInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Module to configure your Tapestry Application.
 */
public final class ProductionModeModule {

    private static final Logger log = LoggerFactory.getLogger(ProductionModeModule.class);

    private ProductionModeModule() {
    }

    @Contribute(EntityManagerSource.class)
    public static void configurePersistenceUnitInfos(final MappedConfiguration<String, PersistenceUnitConfigurer> cfg) {

        log.debug("Config Persistence");

        final PersistenceUnitConfigurer configurer = new PersistenceUnitConfigurer() {

            @Override
            public void configure(final TapestryPersistenceUnitInfo unitInfo) {
                unitInfo.persistenceProviderClassName("org.hibernate.jpa.HibernatePersistenceProvider");
                unitInfo.transactionType(PersistenceUnitTransactionType.RESOURCE_LOCAL);
                unitInfo.addProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
                unitInfo.addProperty("hibernate.connection.url", "jdbc:hsqldb:file:database/${artifactId}");
                unitInfo.addProperty("hibernate.connection.username", "SA");
                unitInfo.addProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                unitInfo.addProperty("hibernate.hbm2ddl.auto", "create");
            }
        };

        cfg.add("${artifactid}", configurer);
    }

    /**
     * Contribute to the Application Defaults
     *
     * @param configuration
     */
    public static void contributeApplicationDefaults(final MappedConfiguration<String, Object> configuration) {
        configuration.add(SymbolConstants.PRODUCTION_MODE, true);
    }

}