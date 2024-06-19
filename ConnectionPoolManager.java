package pool2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionPoolManager {
    private static final Map<Thread, Connection> connectionMap = new HashMap<>();

    public static Connection getConnection() throws SQLException {
        Thread currentThread = Thread.currentThread();
        Connection connection = connectionMap.get(currentThread);

        if (connection == null || connection.isClosed()) {
            connection = pool3.getConnection();
            connectionMap.put(currentThread, connection);
        }

        return connection;
    }

    public static void closeConnection() throws SQLException {
        Thread currentThread = Thread.currentThread();
        Connection connection = connectionMap.get(currentThread);

        if (connection != null) {
            pool3.closeConnection(connection);
            connectionMap.remove(currentThread);
        }
    }
}