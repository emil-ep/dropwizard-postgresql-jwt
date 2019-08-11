package com.fyle.bankservice.application;

import com.fyle.bankservice.auth.AuthFilterUtils;
import com.fyle.bankservice.auth.jwt.User;
import com.fyle.bankservice.configuration.BankServiceConfiguration;
import com.fyle.bankservice.configuration.PostgresConfiguration;
import com.fyle.bankservice.datastore.BaseDao;
import com.fyle.bankservice.datastore.PostgresConnection;
import com.fyle.bankservice.resource.BankResource;
import com.fyle.bankservice.resource.LoginResource;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.PolymorphicAuthDynamicFeature;
import io.dropwizard.auth.PolymorphicAuthValueFactoryProvider;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtContext;

public class BankServiceApplication extends Application<BankServiceConfiguration> {


    public static void main(String[] args) {
        try {
            new BankServiceApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run(BankServiceConfiguration bankServiceConfiguration, Environment environment) throws Exception {

        initialisePostgres();
        registerResources(environment);
        registerAuthFilters(environment);
    }

    private void registerResources(Environment environment) {
        environment.jersey().register(BankResource.class);
        environment.jersey().register(LoginResource.class);
    }

    private void registerAuthFilters(Environment environment) {

        AuthFilterUtils authFilterUtils = new AuthFilterUtils();
        final AuthFilter<BasicCredentials, PrincipalImpl> basicFilter = authFilterUtils.buildBasicAuthFilter();
        final AuthFilter<JwtContext, User> jwtFilter = authFilterUtils.buildJwtAuthFilter();

        final PolymorphicAuthDynamicFeature feature = new PolymorphicAuthDynamicFeature<>(
                ImmutableMap.of(PrincipalImpl.class, basicFilter, User.class, jwtFilter));
        final AbstractBinder binder = new PolymorphicAuthValueFactoryProvider.Binder<>(
                ImmutableSet.of(PrincipalImpl.class, User.class));
        environment.jersey().register(feature);
        environment.jersey().register(binder);
        environment.jersey().register(RolesAllowedDynamicFeature.class);
    }

    private void initialisePostgres() {

        PostgresConnection postgresConnection = PostgresConnection.create(PostgresConfiguration.getHost(),
                PostgresConfiguration.getPort(), PostgresConfiguration.getDatabaseName(),
                PostgresConfiguration.getUser(), PostgresConfiguration.getPassword());
        BaseDao.initialize(postgresConnection);
    }
}
