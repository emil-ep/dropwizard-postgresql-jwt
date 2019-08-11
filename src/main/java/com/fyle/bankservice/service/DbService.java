package com.fyle.bankservice.service;

import com.fyle.bankservice.datastore.PostgresConnection;
import com.fyle.bankservice.datastore.filter.DBExpression;
import com.fyle.bankservice.models.BankModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.fyle.bankservice.models.BankModel.*;

public class DbService implements DbServiceInterface {

    private PostgresConnection connection;
    private Connection driverConnection;
    private Logger logger = LoggerFactory.getLogger(DbService.class);

    public DbService(PostgresConnection connection) {
        this.connection = connection;
        open();
    }

    public void open() {
        try {
            Class.forName("org.postgresql.Driver");
            driverConnection = DriverManager.getConnection(this.connection.getHost() + ":"
                            + this.connection.getPort() + "/" + this.connection.getDatabase(),
                    this.connection.getUser(), this.connection.getPassword());
            logger.info("Postgres Connection opened");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Error opening Postgres connection, Exception : ", e);
        }
    }

    public void close() {
        try {
            driverConnection.close();
        } catch (SQLException e) {
            logger.error("Error closing Postgres connection : ", e);
        }
    }


    @Override
    public List<BankModel> fetch(String entity) {
        List<BankModel> banks = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", entity);
        try {
            ResultSet rs = this.driverConnection.createStatement().executeQuery(query);
            while (rs.next()) {
                BankModel bank = new BankModel(
                        rs.getString(IFSC).trim(),
                        rs.getString(BANK_ID).trim(),
                        rs.getString(BRANCH).trim(),
                        rs.getString(ADDRESS).trim(),
                        rs.getString(CITY).trim(),
                        rs.getString(DISTRICT).trim(),
                        rs.getString(STATE).trim(),
                        rs.getString(BANK_NAME).trim());
                banks.add(bank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banks;
    }

    @Override
    public List<BankModel> fetchWithPagination(String entity, List<DBExpression> args, int offset, int limit) {
        List<BankModel> banks = new ArrayList<>();
        String query = generateQuery(args, entity, offset, limit);
        try {
            ResultSet rs = this.driverConnection.createStatement().executeQuery(query);
            while (rs.next()) {
                BankModel bank = new BankModel(
                        rs.getString(IFSC).trim(),
                        rs.getString(BANK_ID).trim(),
                        rs.getString(BRANCH).trim(),
                        rs.getString(ADDRESS).trim(),
                        rs.getString(CITY).trim(),
                        rs.getString(DISTRICT).trim(),
                        rs.getString(STATE).trim(),
                        rs.getString(BANK_NAME).trim());
                banks.add(bank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banks;
    }

    private static String generateQuery(List<DBExpression> args, String entity, int currentPage, int size) {
        StringBuilder sql = new StringBuilder().append("SELECT * FROM ").append(entity.toUpperCase());
        if (args != null && args.size() != 0) {
            sql.append(" WHERE ");
            args.forEach(arg -> {
                sql.append(arg.getField()).append(" = ").append("'").append(arg.getValue()).append("'");
                if (args.indexOf(arg) != args.size() - 1) {
                    sql.append(" AND ");
                }
            });
        }
        sql.append(" LIMIT ").append(size).append(" OFFSET ").append(currentPage * size);
        return sql.toString();
    }
}
