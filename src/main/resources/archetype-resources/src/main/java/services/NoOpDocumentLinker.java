package com.azudio.archetypetest.services;

import org.apache.tapestry5.internal.services.DocumentLinker;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.ModuleConfigurationCallback;
import org.apache.tapestry5.services.javascript.StylesheetLink;

public class NoOpDocumentLinker implements DocumentLinker {

    @Override
    public void addStylesheetLink(final StylesheetLink stylesheet) {
        // no-op
    }

    @Override
    public void addScript(final InitializationPriority priority, final String script) {
        // no-op
    }

    @Override
    public void addModuleConfigurationCallback(final ModuleConfigurationCallback callback) {
        // no-op
    }

    @Override
    public void addLibrary(final String libraryURL) {
        // no-op
    }

    @Override
    public void addInitialization(final InitializationPriority priority, final String moduleName, final String functionName, final JSONArray arguments) {
        // no-op
    }

    @Override
    public void addCoreLibrary(final String libraryURL) {
        // no-op
    }
}