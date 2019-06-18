package db.tracker;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ConnectionRollBack.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/17/2019
 */
public final class ConnectionRollBack {
    /**
     * Constructor.
     */
    private ConnectionRollBack() {
    }

    /**
     * Create connection with autocommit=false mode
     * and rollback call, when connection is closed.
     *
     * @param connection connection.
     * @return Connection object.
     *
     * @throws SQLException possible exception.
     */
    public static Connection create(final Connection connection)
            throws SQLException {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionRollBack.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    Object rsl = null;
                    if ("close".equals(method.getName())) {
                        connection.rollback();
                        connection.close();
                    } else {
                        rsl = method.invoke(connection, args);
                    }
                    return rsl;
                }
        );
    }
}
