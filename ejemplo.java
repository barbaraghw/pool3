package pool2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ejemplo {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(5); // Crea un pool de 5 threads

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    Connection connection = pool3.getConnection();
                    // Utiliza la conexión
                    // ...

                    // Cerrar y devolver la conexión al pool
                    pool3.closeConnection(connection);
                } catch (SQLException e) {
                    System.out.println("Error al obtener conexión: " + e.getMessage());
                }
            });
        }

        executor.shutdown();
    }
}