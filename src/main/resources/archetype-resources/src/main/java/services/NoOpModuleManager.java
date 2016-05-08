package com.azudio.archetypetest.services;

import java.util.List;

import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.services.javascript.ModuleConfigurationCallback;
import org.apache.tapestry5.services.javascript.ModuleManager;

public class NoOpModuleManager implements ModuleManager {

    @Override
    public void writeInitialization(final Element body, final List<String> libraryURLs, final List<?> inits) {
        // no-op
    }

    @Override
    public void writeConfiguration(final Element body, final List<ModuleConfigurationCallback> moduleConfigurationCallbacks) {
        // no-op
    }

    @Override
    public Resource findResourceForModule(final String moduleName) {
        return null;
    }
}