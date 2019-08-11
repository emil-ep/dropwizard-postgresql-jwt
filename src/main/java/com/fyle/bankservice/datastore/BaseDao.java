package com.fyle.bankservice.datastore;

import com.fyle.bankservice.service.DbService;

public class BaseDao {

    protected static DbService dbService;

    public static void initialize(PostgresConnection connection) {

        dbService = new DbService(connection);
    }

    public static void close() {
        dbService.close();
    }
}
