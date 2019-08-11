package com.fyle.bankservice.datastore;

public class DbConnection {


    protected String host;
    protected int port;
    protected String database;
    protected String user;
    protected String password;

    protected DbConnection(String host, int port, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }


    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbConnection that = (DbConnection) o;
        if (port != that.port) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (database != null ? !database.equals(that.database) : that.database != null) return false;
        return !(user != null ? !user.equals(that.user) : that.user != null);
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + port;
        result = 31 * result + (database != null ? database.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
