#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

import javax.persistence.spi.PersistenceUnitTransactionType;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
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
import com.azudio.tapestry.atmosphere.module.AtmosphereModule;
import ${package}.services.rest.PersonResource;
import ${package}.services.rest.PersonResourceJPA;


/**
 * Module to configure your Tapestry Application.
 */
@ImportModule({ AtmosphereModule.class })
public class AppModule {

	private static final Logger log = LoggerFactory.getLogger(AppModule.class);

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
			// unitInfo.addProperty("hibernate.connection.url", "jdbc:mysql://localhost/${artifactId}");
			// unitInfo.addProperty("hibernate.connection.username", "sample18");
			// unitInfo.addProperty("hibernate.connection.password", "sample18");
			// unitInfo.addProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			// unitInfo.addProperty("hbm2ddl.auto", "create");
			// }

			/**
			 * Embedded HSQLDB Implementation
			 * 
			 * @param unitInfo
			 */
			public void configure(TapestryPersistenceUnitInfo unitInfo) {
				unitInfo.persistenceProviderClassName("org.hibernate.ejb.HibernatePersistence");
				unitInfo.transactionType(PersistenceUnitTransactionType.RESOURCE_LOCAL);
				unitInfo.addProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
				unitInfo.addProperty("hibernate.connection.url", "jdbc:hsqldb:file:database/${artifactId}");
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
		configuration.add(SymbolConstants.INCLUDE_CORE_STACK, false);
	}

	/**
	 * Binds Services
	 * 
	 * @param binder
	 */
	public static void bind(ServiceBinder binder) {

		binder.bind(Core.class, CoreImpl.class);

		// REST Services
		binder.bind(PersonResource.class, PersonResourceJPA.class);

	}

	/**
	 * REST: Contribute the proxies services for Live Class Reloading
	 * 
	 * @param singletons
	 * @param pageResource
	 */
	@Contribute(javax.ws.rs.core.Application.class)
	public static void configureRestResources(Configuration<Object> singletons, PersonResource pageResource) {
		singletons.add(pageResource);
	}

	/**
	 * REST: To support the @CommitAfter annotation, the *Resource classes are advised wrapping in a transaction
	 * 
	 * @param advisor
	 * @param receiver
	 */
	@Match({ "*Resource", "*Handler" })
	public static void adviseTransactionally(JpaTransactionAdvisor advisor, MethodAdviceReceiver receiver) {
		advisor.addTransactionCommitAdvice(receiver);
	}

}