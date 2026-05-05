/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package act16;

/**
 *
 * @author borja
 */
public class Llibres extends Llibre{
    
    public Llibres(String titol, String autor, Integer ano){
        super(titol, autor, ano);
    }
    
    @Override
    public String getTipus(){
        return "Llibre";
    }
}
