package com.fyle.bankservice.datastore;

public class PostgresConnection extends DbConnection {


    private PostgresConnection(String host, int port, String database, String userName, String password) {
        super(host, port, userName, password);
        this.database = database;
    }

    public static PostgresConnection create(String host, int port, String database, String user, String password) {
        return new PostgresConnection(host, port, database, user, password);
    }

}
