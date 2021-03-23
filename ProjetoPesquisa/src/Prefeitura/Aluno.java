/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prefeitura;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 *
 * @author User
 */
@Entity
public class Aluno implements Serializable {

     enum dado{};
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome")
    private String nome;
//    @Column(name = "idade")
//    private int idade;
    @Column(name = "sexo")
    private SexoEnum sexo;
//    @Column(name = "endereco")
//    private String endereco;
    @Column(name = "nascimento")
    private LocalDate nascimento;
    
    @Column(name = "turma")
    private String turma;
    
    @Column(name = "turno")
    private TurnoEnum turno;
    
    @Column(name = "estudouEscolaAnoAntes")    
    private EstudouEscolaEnum estudouEscolaAnoAntes; //se estudou na escola ano anterior
    
    @Column(name = "anoAnterior")    
    private AnoAnteriorEnum anoAnterior; // se no ano anterior ficou retido ou avançou
    
    @Column(name = "resideCom")    
    private ResideEnum resideCom;
    
    @Column(name = "comportamento")    
    private ComporamentoEnum comportamento;
    
    @Column(name = "conheceRegras")    
    private boolean conheceRegras;
    
    @Column(name = "respeitaProfissionais")    
    private boolean respeitaProfissionais;
    
    @Column(name = "respeitaPatrimonio")    
    private boolean respeitaPatrimonio;
    
    @Column(name = "respeitaColegas")    
    private boolean respeitaColegas;
    
      
    @Column(name = "viveuDescPortadorNecEspeciais")    
    private boolean viveuDescPortadorNecEspeciais;
    
    @Column(name = "viveuDescRacismoGenBully")    
    private boolean viveuDescRacismoGenBully;
    
    @Column(name = "usoBebida")    
    private boolean usoBebida;
    
    @Column(name = "agressoes")    
    private boolean agressoes;
    
    @Column(name = "roubos")    
    private boolean  roubos;
    
    @Column(name = "gravidezPrecoce")  
    private boolean gravidezPrecoce;
    
    @Column(name = "usoTraficoDrogas")  
    private boolean usoTraficoDrogas;
    
    @Column(name = "responsavel")  
    private ResponsavelEnum responsavel;
    
    @Column(name = "projetoSocial")  
    private ProjetoSocialEnum projetoSocial;
    
    @Column(name = "encaminhaMinisterioPublico")  
    private boolean encaminhaMinisterioPublico;
    
    @Column(name = "vulneravel")  
    private boolean vulneravel;
    
    @Column(name = "maisEducacao")  
    private boolean maisEducacao;
    
    @Column(name = "respeitaRegras")  
    private boolean respeitaRegras;
    
    @Column(name = "ausenciaciaCominucada")  
    private boolean ausenciaciaCominucada;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Escola escola;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        
        this.id = id;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
//    public void setIdade(int idade){
//        this.idade=idade;
//    }
//    public int getIdade(){
//        return this.idade;
//    }
    public void setSexo(SexoEnum sexo){
        this.sexo=sexo;
    }
    public SexoEnum getSexo(){
        return this.sexo;
    }
//    public void setEndereco(String endereco){
//        this.endereco=endereco;
//    }
//    public String getEndereco(){
//        return this.endereco;
//    }
    
    public void setNascimento(LocalDate nascimento){
        this.nascimento=nascimento;
        
    }
    public LocalDate getNascimento(){
        return this.nascimento;
    }
    
    public void setTurma(String turnma){
        this.turma=turnma;
        
    }
    public String getTurma(){
        return this.turma;
    }
    
    public void setTurno(TurnoEnum turno){
        this.turno=turno;
        
    }
    public TurnoEnum getTurno(){
        return this.turno;
    }
    
    public void setEstudouEscolaAnoAntes(EstudouEscolaEnum estudouEscolaAnoAntes){
        this.estudouEscolaAnoAntes=estudouEscolaAnoAntes;
    }
    public EstudouEscolaEnum getEstudouEscolaAnoAntes(){
        return this.estudouEscolaAnoAntes;
    }
    
    public void setAnoAnterior(AnoAnteriorEnum anoAnterior){
        this.anoAnterior=anoAnterior;
    }
    public AnoAnteriorEnum getAnoAnterior(){
        return this.anoAnterior;
    }
    
    public void setResideCom(ResideEnum resideCom){
        this.resideCom =resideCom;
    }
    public ResideEnum getResideCom(){
        return this.resideCom;
    }
    
    public void setComportamento(ComporamentoEnum comportamento) {
        this.comportamento = comportamento;
    }
    public ComporamentoEnum getComportamento() {
        return this.comportamento;
    }

    public void setConheceRegras(boolean conheceRegras) {
        this.conheceRegras = conheceRegras;
    }
    public boolean isConheceRegras() {
        return this.conheceRegras;
    }
    
    public void setRespeitaProfissionais(boolean respeitaProfissionais) {
        this.respeitaProfissionais = respeitaProfissionais;
    }
    public boolean isRespeitaProfissionais() {
        return this.respeitaProfissionais;
    }
    
    public void setRespeitaPatrimonio(boolean respeitaPatrimonio) {
        this.respeitaPatrimonio = respeitaPatrimonio;
    }
    public boolean isRespeitaPatrimonio() {
        return this.respeitaPatrimonio;
    }

    public void setRespeitaColegas(boolean respeitaColegas) {
        this.respeitaColegas = respeitaColegas;
    }
    public boolean isRespeitaColegas() {
        return this.respeitaColegas;
    }

    
    public void setViveuDescPortadorNecEspeciais(boolean viveuDescPortadorNecEspeciais) {
        this.viveuDescPortadorNecEspeciais = viveuDescPortadorNecEspeciais;
    }
    public boolean isViveuDescPortadorNecEspeciais() {
        return this.viveuDescPortadorNecEspeciais;
    }
    
    public void setViveuDescRacismoGenBully(boolean viveuDescRacismoGenBully) {
        this.viveuDescRacismoGenBully = viveuDescRacismoGenBully;
    }
    public boolean isViveuDescRacismoGenBully() {
        return this.viveuDescRacismoGenBully;
    }

    
    public void setUsoBebida(boolean usoBebida) {
        this.usoBebida = usoBebida;
    }
    public boolean isUsoBebida() {
        return this.usoBebida;
    }

    public void setAgressoes(boolean agressoes) {
        this.agressoes = agressoes;
    }
    public boolean isAgressoes() {
        return this.agressoes;
    }
    
    public void setRoubos(boolean roubos) {
        this.roubos = roubos;
    }
    public boolean isRoubos() {
        return this.roubos;
    }

    
    public void setGravidezPrecoce(boolean gravidezPrecoce) {
        this.gravidezPrecoce = gravidezPrecoce;
    }
    public boolean isGravidezPrecoce() {
        return this.gravidezPrecoce;
    }
    
    public void setUsoTraficoDrogas(boolean usoTraficoDrogas) {
        this.usoTraficoDrogas = usoTraficoDrogas;
    }
    public boolean isUsoTraficoDrogas() {
        return this.usoTraficoDrogas;
    
    }
     public void setResponsavel(ResponsavelEnum responsavel) {
        this.responsavel = responsavel;
    }
    public ResponsavelEnum getResponsavel() {
        return this.responsavel;
    }

    
    public void setProjetoSocial(ProjetoSocialEnum projetoSocial) {
        this.projetoSocial = projetoSocial;
    }
    public ProjetoSocialEnum getProjetoSocial() {
        return this.projetoSocial;
    }
    
    public void setEncaminhaMinisterioPublico(boolean encaminhaMinisterioPublico) {
        this.encaminhaMinisterioPublico = encaminhaMinisterioPublico;
    }
    public boolean isEncaminhaMinisterioPublico() {
        return this.encaminhaMinisterioPublico;
    }
    
    public void setVulneravel(boolean vulneravel) {
        this.vulneravel = vulneravel;
    }
    public boolean isVulneravel() {
        return this.vulneravel;
    }

    public void setMaisEducacao(boolean maisEducacao) {
        this.maisEducacao = maisEducacao;
    }
    public boolean isMaisEducacao() {
        return this.maisEducacao;
    }

    public void setRespeitaRegras(boolean respeitaRegras) {
        this.respeitaRegras = respeitaRegras;
    }
    public boolean isRespeitaRegras() {
        return this.respeitaRegras;
    }

    public void setAusenciaciaCominucada(boolean ausenciaciaCominucada) {
        this.ausenciaciaCominucada = ausenciaciaCominucada;
    }
    
    public boolean isAusenciaciaCominucada() {
        return this.ausenciaciaCominucada;
    }
    
    public void setEscola(Escola escola){
        this.escola=escola;
    }
    public Escola getEscola(){
        return this.escola;
    }    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prefeitura.aluno[ id=" + id + " ]";
    }
    
}
