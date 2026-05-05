
package act16;

public class Articles extends Llibre{
    
    public Articles(String titol, String autor, Integer ano){
        super(titol, autor, ano);
    }
    
    @Override
    public String getTipus(){
        return "Article";
    }
    
}
