package Models;

import database.SQLDatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<PersonaModel> obtenerPersonas() {
        List<PersonaModel> personas = new ArrayList<>();
        String query = "SELECT id, nombre, apellido FROM persona";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id"); // columna id
                String nombre = rs.getString("nombre"); // columna nombre
                String apellido = rs.getString("apellido"); // columna apellido

                PersonaModel persona = new PersonaModel(nombre, apellido);
                personas.add(persona);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener personas: " + e.getMessage());
        }
        return personas;
    }

}
