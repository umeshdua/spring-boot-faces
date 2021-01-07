package com.auth0.samples.bootfaces.config;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

public class RewriteConfigurationProvider extends HttpConfigurationProvider {
	@Override
	public int priority() {
		return 10;
	}

	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder.begin()
				.addRule(Join.path("/").to("/product/list.jsf"))
				.addRule(Join.path("/list/{entity}").to("/{entity}/list.jsf"))
				.addRule(Join.path("/crud/{entity}").to("/{entity}/crud.jsf"));
	}
}