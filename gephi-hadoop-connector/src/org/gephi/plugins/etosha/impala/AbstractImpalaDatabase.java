package org.gephi.plugins.etosha.impala;

import org.gephi.io.database.drivers.SQLDriver;
import org.gephi.io.importer.api.PropertiesAssociations;
import org.gephi.io.importer.plugin.database.EdgeListDatabaseImpl;

/**
 *
 * @author kamir
 * 
 * based on original work from: Mathieu Bastian
 */
public class AbstractImpalaDatabase extends EdgeListDatabaseImpl {

    //Database attributes
    protected String name;
    protected SQLDriver driver;
    protected String host;
    protected int port;
    protected String username;
    protected String passwd;
    protected String DBName;
    //PropertiesAssociations
    protected PropertiesAssociations properties = new PropertiesAssociations();

    public String getDBName() {
        return DBName;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public SQLDriver getSQLDriver() {
        return driver;
    }

    public void setSQLDriver(SQLDriver driver) {
        this.driver = driver;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PropertiesAssociations getPropertiesAssociations() {
        return properties;
    }
}
