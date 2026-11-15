package dao;

import config.ConexionDB;
import estructuras.Adoptante;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdoptanteDAO implements CRUD<Adoptante> {

    private static final String SQL_INSERTAR = "INSERT INTO adoptantes( id_adoptante, nombre, dni, telefono, email, domicilio) VALUES ( ?,?,?,?,?,?)";
    private static final String SQL_LISTAR = "SELECT * FROM adoptantes ORDER BY id_adoptante";
    private static final String SQL_BUSCAR = "SELECT * FROM adoptantes WHERE id_adoptante = ?";
    private static final String SQL_ACTUALIZAR =  "UPDATE adoptantes SET nombre = ?, dni = ?, telefono = ?, email = ?, domicilio = ? WHERE id_adoptante = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM adoptantes WHERE id_adoptante = ?";

    @Override
    public void crear(Adoptante objeto) throws AccesoDatosException {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERTAR)){
            ps.setInt(1, objeto.getId());
            ps.setString(2, objeto.getNombre());
            ps.setInt(3, objeto.getDni());
            ps.setString(4, objeto.getTelefono());
            ps.setString(5, objeto.getEmail());
            ps.setString(6, objeto.getDomicilio());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new AccesoDatosException("Error a cargar Adoptante " + objeto.getId(), e);
        }
    }

    @Override
    public List<Adoptante> mostrarTodos() throws AccesoDatosException {
        List<Adoptante> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(crearAdoptante(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al listar los Adoptantes", e);
        }
        return lista;
    }

    @Override
    public Adoptante buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_BUSCAR)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearAdoptante(rs);
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar a Adoptante " + id , e);
        }
        throw new ElementoNoEncontradoExcepcion("Adoptante", id);
    }

    @Override
    public void modificar(int id, Adoptante objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        int filas;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ACTUALIZAR)) {
            ps.setString(1, objeto.getNombre());
            ps.setInt(2, objeto.getDni());
            ps.setString(3, objeto.getTelefono());
            ps.setString(4, objeto.getEmail());
            ps.setString(5, objeto.getDomicilio());
            ps.setInt(6, id);
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al Actualizar de Adoptantes " + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Adoptante", id);
        }
    }

    @Override
    public void eliminar(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        int filas;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ELIMINAR)){
            ps.setInt(1, id);
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al Eliminar de Adoptantes " + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Adoptante", id);
        }
    }


    private Adoptante crearAdoptante(ResultSet rs) throws SQLException{
        int id = rs.getInt("id_adoptante");
        String nombre = rs.getString("nombre");
        int dni = rs.getInt("dni");
        String telefono = rs.getString("telefono");
        String email = rs.getString("email");
        String domicilio = rs.getString("domicilio");


        Adoptante adoptante = new Adoptante (id, nombre, dni, telefono, email, domicilio);
        return adoptante;
    }
}
