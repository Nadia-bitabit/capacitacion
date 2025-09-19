package Models;

import database.SQLDatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonaDao {

    private Connection conexion;

    public PersonaDao() {
        try {
            this.conexion = SQLDatabaseManager.connect();
            crearTablaSiNoExiste();
        } catch (SQLException  e) {
            System.err.println("Error de conexion");
        }
    }

    private boolean closeDBConnection(){
        try {
            SQLDatabaseManager.disconnect(conexion);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // 🔹 Crear la tabla persona si no existe
    private void crearTablaSiNoExiste() {
        String query = "CREATE TABLE IF NOT EXISTS persona (" +
                       "id SERIAL PRIMARY KEY, " +
                       "nombre VARCHAR(50) NOT NULL, " +
                       "apellido VARCHAR(50) NOT NULL" +
                       ")";
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(query);
            System.out.println("✅ Tabla 'persona' creada/verificada");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear tabla: " + e.getMessage());
        }
    }

    public boolean crearPersona(PersonaModel persona){
        try{
            String query = "INSERT INTO persona (nombre, apellido) VALUES (?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(query);
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar persona " + e.getMessage());
            return false;
        } finally {
            closeDBConnection();
        }
    }
}
