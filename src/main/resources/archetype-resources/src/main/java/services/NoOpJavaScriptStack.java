package com.azudio.archetypetest.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.services.javascript.JavaScriptAggregationStrategy;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

public class NoOpJavaScriptStack implements JavaScriptStack {

    @Override
    public List<StylesheetLink> getStylesheets() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getStacks() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getModules() {
        return new ArrayList<>();
    }

    @Override
    public List<Asset> getJavaScriptLibraries() {
        return new ArrayList<>();
    }

    @Override
    public JavaScriptAggregationStrategy getJavaScriptAggregationStrategy() {
        return JavaScriptAggregationStrategy.DO_NOTHING;
    }

    @Override
    public String getInitialization() {
        return "";
    }
}