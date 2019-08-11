package com.fyle.bankservice.configuration;

import io.dropwizard.Configuration;

public class BankServiceConfiguration extends Configuration {

    private static PostgresConfiguration postgres;

    public static PostgresConfiguration getPostgres() {
        return postgres;
    }

    public void setPostgres(PostgresConfiguration postgres) {
        this.postgres = postgres;
    }
}
