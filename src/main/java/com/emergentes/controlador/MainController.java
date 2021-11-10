package com.emergentes.controlador;

import com.emergentes.modelo.Evento;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        
         try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op"):"list";
            ArrayList<Evento> evento = new ArrayList<Evento>();
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (op.equals("list")){
                // Para listar los datos
                String sql = "SELECT * FROM bd_eventos.seminarios;";
                // Consulta de selección y almacenarlo en una colección
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Evento eve = new Evento();
                    eve.setId(rs.getInt("id"));
                    eve.setTitulo(rs.getString("titulo"));
                    eve.setExpositor(rs.getString("expositor"));
                    eve.setFecha(rs.getString("fecha"));
                    eve.setHora(rs.getString("hora"));
                    eve.setCupo(rs.getInt("cupo"));
                    evento.add(eve);
                }
                request.setAttribute("evento", evento);
                // Enviar al index.jsp para mostrar la informacion
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (op.equals("nuevo")){
                // Instanciar un objeto de la clase 
                Evento ev = new Evento();
                                
                System.out.println(ev.toString());
                
                // El objeto se pone como atributo de request
                request.setAttribute("eve", ev);
                // Redireccionar a editar.jsp
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
            
            if (op.equals("editar")){
                id = Integer.parseInt(request.getParameter("id"));
                Evento eve1 = new Evento();
                ps = conn.prepareStatement("select * from bd_eventos.seminarios where id = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()){
                    eve1.setId(rs.getInt("id"));
                    eve1.setTitulo(rs.getString("titulo"));
                    eve1.setExpositor(rs.getString("expositor"));
                    eve1.setFecha(rs.getString("fecha"));
                    eve1.setHora(rs.getString("hora"));
                    eve1.setCupo(rs.getInt("cupo"));
                }
                request.setAttribute("eve", eve1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
            if (op.equals("eliminar")){
                // Obtener el id
                id = Integer.parseInt(request.getParameter("id"));
                // Realizar la eliminación en la base de datos
                String sql = "delete from libros where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                // Redireccionar a MainController
                response.sendRedirect("MainController");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR AL CONEXTAR "+ex.getMessage());
        }
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Valor de ID " + id);
        String titulo = request.getParameter("titulo");
        String expositor = request.getParameter("expositor");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String cupo = request.getParameter("cupo");
        Evento eve =  new Evento();
        eve.setId(id);
        eve.setTitulo(titulo);
        eve.setExpositor(expositor);
        eve.setFecha(fecha);
        eve.setHora(hora);
        eve.setCupo(cupo);
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (id == 0){
            // Nuevo registro
            String sql = "insert into seminarios (titulo, expositor, fecha, hora, cupo) values (?,?,?,?,?)";
            try {
                ps = conn.prepareStatement(sql);
                
                ps.setString(1, eve.getTitulo());
                ps.setString(2, eve.getExpositor());
                ps.setString(3, eve.getFecha());
                ps.setString(4, eve.getHora());
                ps.setInt(5, eve.getCupo());
                ps.executeUpdate();
            } catch (SQLException ex){
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            String sql1 = "update evento set titulo=?, expositor=?, fecha=?, hora=?, cupo=? where id=?";
            try {
                ps = conn.prepareStatement(sql1);
                ps = setString(1, eve.getTitulo());
                ps = setString(2, eve.getExpositor());
                ps = setString(3, eve.getFecha());
                ps = setString(4, eve.getHora());
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
         
        }
        response.sendRedirect("MainController");
        
    }

    private PreparedStatement setString(int i, String titulo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
