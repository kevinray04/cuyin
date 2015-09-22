/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.ulima.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.ulima.dto.AdivinanzaDTO;

/**
 *
 * @author Kevin
 */
public class GestionData {
    
    private Connection iniciarConexion() throws ClassNotFoundException, SQLException{
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");
            
        connection = DriverManager.getConnection(
                    "jdbc:sqlite:C:\\Users\\Kevin\\Downloads\\riddles.db");
        
        return connection;
    }
    
    public List<AdivinanzaDTO> listarAdivinanzas(){
        List<AdivinanzaDTO> listaA= new ArrayList<>();
        String sql="Select * from adivinanza";
        Connection con= null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        AdivinanzaDTO refAdiv=null;
        
        try{
            con=this.iniciarConexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                refAdiv= new AdivinanzaDTO();
                refAdiv.setId(rs.getInt("id"));
                refAdiv.setTexto(rs.getString("texto"));
                refAdiv.setRespuesta(rs.getString("respuesta"));
                listaA.add(refAdiv);
            }
            
        }catch(Exception e){
            
            System.out.println(e.toString());
            
        }finally{
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listaA;
    }
    
    public boolean escribirScore(String nombre, int score){
        
        boolean ok=false;
        Connection con=null;
        PreparedStatement ps=null;
        String sql="insert into score values(?,?,?)";
        
        try{
            con=this.iniciarConexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, this.calcularID());
            ps.setString(2,nombre);
            ps.setInt(3, score);
            ok=true;
            
        }catch(Exception e){
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }
    
    private int calcularID(){
        int id=1;
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            con=this.iniciarConexion();
            ps=con.prepareStatement("Select * from score");
            rs=ps.executeQuery();
            while(rs.next()){
                id++;
            }
        }catch (Exception ex) {
            System.out.println(ex.toString());
        }finally{
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return id;
    }
    
}
