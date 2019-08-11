package com.fyle.bankservice.configuration;

public class PostgresConfiguration {

    private static String databaseName;
    private static int port;
    private static String host;
    private static String user;
    private static String password;

    public static String getUser() {
        return user;
    }

    public void setUser(String user) {
        PostgresConfiguration.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PostgresConfiguration.password = password;
    }

    public static String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        PostgresConfiguration.databaseName = databaseName;
    }

    public static int getPort() {
        return port;
    }

    public void setPort(int port) {
        PostgresConfiguration.port = port;
    }
}
