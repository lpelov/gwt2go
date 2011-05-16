package com.gwt2go.dev.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * This gin module binds an implementation for the
 * {@link com.google.gwt.inject.example.simple.client.SimpleService} used in
 * this example application. Note that we don't have to bind implementations
 * for {@link com.google.gwt.inject.example.simple.client.SimpleConstants} and
 * {@link com.google.gwt.inject.example.simple.client.SimpleMessages} - they
 * are constructed by Gin through GWT.create.
 */
public class SimpleGinModule extends AbstractGinModule {

  protected void configure() {
    bind(SimpleService.class).to(SimpleServiceImpl.class);
  }
}
