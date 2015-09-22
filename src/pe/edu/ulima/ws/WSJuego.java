/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.ulima.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import pe.edu.ulima.dao.GestionData;
import pe.edu.ulima.dto.AdivinanzaDTO;

/**
 *
 * @author Kevin
 */

@WebService
public class WSJuego {
    
    private GestionData refGes=new GestionData();
    
    
    @WebMethod(operationName = "getAdivinanzas")
    public List<AdivinanzaDTO> obtenerAdivinanzas(){
        List<AdivinanzaDTO> listaA=refGes.listarAdivinanzas();
        
        return listaA;
    }
    
    @WebMethod(operationName = "verificarRespuesta")
    public int verificarRespuesta(List<AdivinanzaDTO> listaRPTA){
        List<AdivinanzaDTO> listaA=refGes.listarAdivinanzas();
        int correctas=0;
        
        for(int i=0; i<listaA.size(); i++){
            if(listaA.get(i).getRespuesta().equals(listaRPTA.get(i).getRespuesta())){
                correctas++;
            }
        }
        
        return correctas;
        
    }
    
    @WebMethod(operationName = "guardarScore")
    public boolean guardarScore(String nombre, int score){
        
        boolean ok=refGes.escribirScore(nombre, score);
        
        return ok;
    }
    
}
