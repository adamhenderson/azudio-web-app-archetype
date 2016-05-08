#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.modules;

import org.apache.tapestry5.internal.InternalConstants;
import org.apache.tapestry5.internal.services.DocumentLinker;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.services.ServiceOverride;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.ModuleManager;

import ${package}.services.NoOpDocumentLinker;
import ${package}.services.NoOpJavaScriptStack;
import ${package}.services.NoOpModuleManager;

/**
 * Module to remove any of Tapestry's built in JavaScript and CSS handling.
 */
public final class KillJSModule {

    private KillJSModule() {
    }

    @Contribute(JavaScriptStackSource.class)
    public static void killCoreStack(final MappedConfiguration<String, JavaScriptStack> configuration) {

        // Make the JSSTack a no-op
        configuration.override(InternalConstants.CORE_STACK_NAME, new NoOpJavaScriptStack());
    }

    @Contribute(ServiceOverride.class)
    public static void killDocumentLinkerAndModuleManager(final MappedConfiguration<Class<?>, Object> configuration) {

        // Make the Document Linker a no-op
        configuration.add(DocumentLinker.class, new NoOpDocumentLinker());

        // Make the ModuleManager a no-op
        configuration.add(ModuleManager.class, new NoOpModuleManager());
    }
}