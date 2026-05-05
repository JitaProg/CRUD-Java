
package act16;

public class Revistes extends Llibre{
    
    public Revistes(String titol, String autor, Integer ano){
        super(titol, autor, ano);
    }
    
    @Override
    public String getTipus(){
        return "Revista";
    }
}
