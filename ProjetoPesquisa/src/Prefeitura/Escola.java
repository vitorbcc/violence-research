
package Prefeitura;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
        
@Entity
//@Table(name = "escola")
public class Escola {
    @Column(name="nome")
    private String nome;
    @Column(name="distrito")
    private String distrito;
    @Id
    @Column(name="inep")
    private int inep; 
    
    @Column(name="conselho",nullable=true)
    private boolean conselho;
    
    @Column(name="cciclo")
    private ConselhoCicloEnum cciclo;
    
    @Column(name="regraconvivencia")
    private RegrasConvivenciaEnum regraconvivencia;
    
    
    //@OneToMany(cascade = CascadeType.ALL,mappedBy = "inep")
    private ArrayList<Aluno> arrayalunos;
    
    public void  setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
    public void setDistrito(String distrito){
        this.distrito =distrito;    
    }
    public String getDistrito(){
        return this.distrito;
    }
    
    public void setInep(int inep){
        this.inep = inep;
    }
    public int getInep(){
        return this.inep;
    }
    public void setAluno(ArrayList alunos){
        this.arrayalunos =alunos;
    }
    public ArrayList getAlunos(){
        return this.arrayalunos;
    }
    
    public void setConselho(boolean conselho){
        this.conselho =conselho;
    }
    public boolean getConselho(){
        return this.conselho;
    }
    
    public void setCCiclo(ConselhoCicloEnum conselhoCicloEnum){
        this.cciclo = conselhoCicloEnum;
    }
    public ConselhoCicloEnum getCCiclo(){
        return this.cciclo;
    }
    
    public void setRegraConvivencia(RegrasConvivenciaEnum regraconvivencia){
        this.regraconvivencia = regraconvivencia;
    }
    public RegrasConvivenciaEnum getRegraConvivencia(){
        return this.regraconvivencia;
    }
    
     public String toString() {
        return "Prefeitura.Escola " + getNome() + "               -----                " + getDistrito() +"";
    }
}
