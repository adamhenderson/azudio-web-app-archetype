#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.modules;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.internal.InternalConstants;
import org.apache.tapestry5.internal.services.DocumentLinker;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.services.ServiceOverride;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptAggregationStrategy;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.ModuleConfigurationCallback;
import org.apache.tapestry5.services.javascript.ModuleManager;
import org.apache.tapestry5.services.javascript.StylesheetLink;

/**
 * Module to remove any of Tapestry's built in JavaScript and CSS handling.
 */
public class KillJSModule {

    @Contribute(JavaScriptStackSource.class)
    public static void killCoreStack(final MappedConfiguration<String, JavaScriptStack> configuration) {
        configuration.override(InternalConstants.CORE_STACK_NAME, new JavaScriptStack() {

            @Override
            public List<StylesheetLink> getStylesheets() {
                return new ArrayList<StylesheetLink>();
            }

            @Override
            public List<String> getStacks() {
                return new ArrayList<String>();
            }

            @Override
            public List<String> getModules() {
                return new ArrayList<String>();
            }

            @Override
            public List<Asset> getJavaScriptLibraries() {
                return new ArrayList<Asset>();
            }

            @Override
            public JavaScriptAggregationStrategy getJavaScriptAggregationStrategy() {
                return JavaScriptAggregationStrategy.DO_NOTHING;
            }

            @Override
            public String getInitialization() {
                return "";
            }
        });
    }

    @Contribute(ServiceOverride.class)
    public static void killDocumentLinkerAndModuleManager(final MappedConfiguration<Class<?>, Object> configuration) {
        // Make the Document Linker a no-op
        configuration.add(DocumentLinker.class, new DocumentLinker() {

            @Override
            public void addStylesheetLink(final StylesheetLink stylesheet) {
            }

            @Override
            public void addScript(final InitializationPriority priority, final String script) {
            }

            @Override
            public void addModuleConfigurationCallback(final ModuleConfigurationCallback callback) {
            }

            @Override
            public void addLibrary(final String libraryURL) {
            }

            @Override
            public void addInitialization(final InitializationPriority priority, final String moduleName, final String functionName, final JSONArray arguments) {
            }

            @Override
            public void addCoreLibrary(final String libraryURL) {
            }
        });
        // Make the ModuleManager a no-op
        configuration.add(ModuleManager.class, new ModuleManager() {

            @Override
            public void writeInitialization(final Element body, final List<String> libraryURLs, final List<?> inits) {
            }

            @Override
            public void writeConfiguration(final Element body, final List<ModuleConfigurationCallback> moduleConfigurationCallbacks) {
            }

            @Override
            public Resource findResourceForModule(final String moduleName) {
                return null;
            }
        });
    }
}