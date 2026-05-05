
package act16;

//Clase abstracta para no crear un objeto libro
public abstract class Llibre {
    private String titol;
    private String autor;
    private Integer ano;
    private String estat;
    
    //Constructor de libro
    public Llibre(String titol, String autor, Integer ano){
        this.titol = titol;
        this.autor = autor;
        this.ano = ano;
        this.estat = "Disponible";
    }
    
    //Getters
    public String getTitol(){
        return titol;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public Integer getAno(){
        return ano;
    }
    
    public String getEstat(){
        return estat;
    }
    
    //Setters
    public void setTitol(String titol){
        this.titol = titol;
    }
    
    public void setAutor(String autor){
        this.autor = autor;
    }
    
    public void setAno(Integer ano){
        this.ano = ano;
    }
    
    public void setEstat(String estat){
        this.estat = estat;
    }
    
    public abstract String getTipus();
}
