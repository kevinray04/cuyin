/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.ulima.ws;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Kevin
 */
public class IniciarServicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String url = "http://localhost:9555/Juego";
        Endpoint.publish(url, new WSJuego());
        System.out.println("Servicio de Juego ejecutandose correctamente...");
    }
    
}
