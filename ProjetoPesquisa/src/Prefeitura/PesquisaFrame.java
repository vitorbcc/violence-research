/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prefeitura;


import java.awt.Dimension;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class PesquisaFrame extends javax.swing.JFrame {

    private Connection con;
    private EntityManager em;
    private ArrayList<JPanel> Lstpainel;
     
    private void stablishConnection(){
        try{
            con=CreateDB.getcon();
        }catch(SQLException e){
            e.printStackTrace();
            
        }catch(ClassNotFoundException ee){    
            ee.printStackTrace();
        }
    }
    
    public void createEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoPesquisaPU");
        em = emf.createEntityManager();        
    }   
    public void loadComboEscola(JComboBox combo){
         try{
            em.getTransaction().begin();
            List<Escola> lst = em.createQuery("select t from Escola as t").getResultList();
            combo.removeAllItems();
            combo.addItem(null);
            for(Escola e: lst){
                combo.addItem(e);
                
//                System.out.println("nome "+e.getNome());
//                System.out.println("distrito "+e.getDistrito());
//                System.out.println("Inep "+e.getInep());
            }
            em.getTransaction().commit();
           }catch(Exception e){
               em.getTransaction().rollback();
               e.printStackTrace();
           }    
    }  
    
    
    
    public void cadastraEscola(){
        
        Escola es = new Escola();
        es.setDistrito("algodoal");
        es.setNome("escola da vida");
        es.setInep(1111);
        es.setConselho(false);
        es.setCCiclo(ConselhoCicloEnum.UM);
        es.setRegraConvivencia(RegrasConvivenciaEnum.PRECISA_ATUALIZAR);
         
        Escola es2 = new Escola();
        es2.setDistrito("pratigi");
        es2.setNome("escola maluca");
        es2.setInep(33);
        es2.setConselho(true);
        es2.setCCiclo(ConselhoCicloEnum.QUATRO);
        es2.setRegraConvivencia(RegrasConvivenciaEnum.SIM);
         
        try{
            em.getTransaction().begin();
            em.persist(es);
            em.persist(es2);
            em.getTransaction().commit();
            
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    public void loadCombos(){
        loadComboEscola(jComboBox1);
        loadComboEscola(jComboBox2);
    }
    public PesquisaFrame() {
        createEntityManager();
        //cadastraEscola();
        initComponents();
        loadCombos();
        initgroupButtons();
        //Lstpainel.add(CadastroPanel,)
        configJtable();
    }
    
    public void configJtable(){
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Dimension d = new Dimension();
        d.setSize(5000, 500);
        jTable1.setPreferredScrollableViewportSize(d);
        
        jTable1.setSize(1000, 400);
        
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(12).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(13).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(14).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(15).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(16).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(17).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(18).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(19).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(20).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(21).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(22).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(23).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(24).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(25).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(26).setPreferredWidth(200);
        
        
    }
    
    public void setJFormattedFieldNascMask(){
        
    }
    
    public MaskFormatter getMask(String mascarade){
        MaskFormatter mask = new MaskFormatter();
        try
            {
                mask.setMask(mascarade);
                mask.setPlaceholderCharacter(' ');
               
            }catch(Exception e){
                e.printStackTrace();
                
            }
        return mask;
    }
    
    
    //metodo de inicilização conjunta do agrupamento dos radiobuttons
    public void initgroupButtons(){
        initgroupButtonSexo();
        initgroupButtonTurno();
        initgroupButtonAnoAnterior();
        initgroupButtonEscolaAnterior();
        initgrouButtonReside();
        initgroupButtonComportamento();
        initgroupButtonConselheCiclo();
        initgroupButtonResponsavel();
        initgroupButtonProjetoSocial();
        initgroupButtonEscolaConstRegra();
        initgroupbuttonConsultaProjetoSoc();
    }
    //inicio dos agrupamentos dos radiobuttos
    public void initgroupButtonEscolaConstRegra(){
        buttonGroupEscoConsRegra.add(jRadioButtonEscolaConsRegraSim);
        buttonGroupEscoConsRegra.add(jRadioButtonEscolaConsRegraNao);
        buttonGroupEscoConsRegra.add(jRadioButtonEscolaConsRegraPatua);
    }
    public void initgroupButtonProjetoSocial(){
        buttonGroupProjetoSoc.add(jRadioButtonProjetoSocSim);
        buttonGroupProjetoSoc.add(jRadioButtonProjetoSocNao);
        buttonGroupProjetoSoc.add(jRadioButtonProjetoSocNaoInd);
    }
    public void initgroupButtonResponsavel(){
        buttonGroupResponsavel.add(jRadioButtonRespMae);
        buttonGroupResponsavel.add(jRadioButtonRespPai);
        buttonGroupResponsavel.add(jRadioButtonRespOutros);
    }
    public void initgroupButtonConselheCiclo(){
         buttonGroupConseCiclo.add(jRadioButtonConseCicl1);
         buttonGroupConseCiclo.add(jRadioButtonConseCicl2);
         buttonGroupConseCiclo.add(jRadioButtonConseCicl3);
         buttonGroupConseCiclo.add(jRadioButtonConseCicl4);
    }
    
    public void initgroupButtonComportamento(){
         buttonGroupComportamento.add(jRadioButtonComportOtimo);
         buttonGroupComportamento.add(jRadioButtonComportbom);
         buttonGroupComportamento.add(jRadioButtonComportRegular);
         buttonGroupComportamento.add(jRadioButtonComportRuim);
    }
    public void initgrouButtonReside(){
         buttonGroupReside.add(jRadioButtonResidePai);
         buttonGroupReside.add(jRadioButtonResidePaiMae);
         buttonGroupReside.add(jRadioButtonResidemae);
         buttonGroupReside.add(jRadioButtonResideoutros);
    }
    public void initgroupbuttonConsultaProjetoSoc(){
        
        buttonGroupConsultaProjetoSoc.add(jRadioButtonConsultaProjSocSim);
        buttonGroupConsultaProjetoSoc.add(jRadioButtonConsultaProjSocNao);
        buttonGroupConsultaProjetoSoc.add(jRadioButtonConsultaProjSocNaoInd);

    }
    
    public void initgroupButtonSexo(){
         buttonGroupSexo.add(jRadioButtonSexoM);
         buttonGroupSexo.add(jRadioButtonSexoF);
    }
      public void initgroupButtonEscolaAnterior(){
         buttonGroupEstudouAntes.add(jRadioButtonEscAntSim);
         buttonGroupEstudouAntes.add(jRadioButtonEscAntNao);
    }
      public void initgroupButtonAnoAnterior(){
         buttonGroupAnoAnte.add(jRadioButtonAntRet);
         buttonGroupAnoAnte.add(jRadioButtonAntAvan);
    }
    
    public void initgroupButtonTurno(){
         buttonGroupTurno.add(jRadioButtonTurnoManha);
         buttonGroupTurno.add(jRadioButtonTurnoIntermed);
         buttonGroupTurno.add(jRadioButtonTurnoTarde);
         buttonGroupTurno.add(jRadioButtonTurnoNoite);
         buttonGroupTurno.add(jRadioButtonTurnoInt);
    }
    public void setSelectedCcicloRadioSelect(Escola escola){
         escola = (Escola) jComboBox1.getSelectedItem();
        //ConselhoCicloEnum CC = escola.getCCiclo(); 

        try{
                switch ( escola.getCCiclo() ) {
                    case UM :
                            jRadioButtonConseCicl1.setSelected(true);
                            break;
                    case DOIS: 
                            jRadioButtonConseCicl2 .setSelected(true);
                            break;
                    case TRES: 
                            jRadioButtonConseCicl3.setSelected(true);
                            break;
                    case QUATRO:
                            jRadioButtonConseCicl4.setSelected(true);
                            break;
                }
        }catch(NullPointerException e){
            buttonGroupConseCiclo.clearSelection();
            
        }catch(Exception ee){
            buttonGroupConseCiclo.clearSelection();
        }
//        if(buttonGroupConseCiclo.isSelected(jRadioButtonConseCicl1.getModel())){
//            System.out.println("siiiiiiiimmm");
//        }
    }
    
    public void setSelectedConvivencia(Escola escola){
         escola = (Escola) jComboBox1.getSelectedItem();
        
         try{
                    switch(escola.getRegraConvivencia()){
                        case SIM:
                                jRadioButtonEscolaConsRegraSim.setSelected(true);
                                break;
                        case NAO: 
                                jRadioButtonEscolaConsRegraNao.setSelected(true);
                                break;
                        case PRECISA_ATUALIZAR: 
                                jRadioButtonEscolaConsRegraPatua.setSelected(true);
                                break;

                    }
         }catch(NullPointerException e){
             buttonGroupEscoConsRegra.clearSelection();
         }catch(Exception ee){
             buttonGroupEscoConsRegra.clearSelection();
         }
    }
    
    public ConselhoCicloEnum getSelectionConselhoCiclo(){
            ConselhoCicloEnum CC;
        if(jRadioButtonConseCicl1.getModel().equals(buttonGroupConseCiclo.getSelection())){
                CC = ConselhoCicloEnum.UM;
        }else if(jRadioButtonConseCicl2.getModel().equals(buttonGroupConseCiclo.getSelection())){
                CC = ConselhoCicloEnum.DOIS;
        }else if(jRadioButtonConseCicl3.getModel().equals(buttonGroupConseCiclo.getSelection())){
                CC = ConselhoCicloEnum.TRES;
        }else if(jRadioButtonConseCicl4.getModel().equals(buttonGroupConseCiclo.getSelection())){
                CC = ConselhoCicloEnum.QUATRO;
        }else
            CC=ConselhoCicloEnum.UM;
          return CC;  
    }
    
    public RegrasConvivenciaEnum getSelectionRegrasConv(){
        RegrasConvivenciaEnum RC;
        
        if(buttonGroupEscoConsRegra.getSelection().equals(jRadioButtonEscolaConsRegraSim.getModel())){
            RC = RegrasConvivenciaEnum.SIM;
        }else if(buttonGroupEscoConsRegra.getSelection().equals(jRadioButtonEscolaConsRegraNao.getModel())){
            RC = RegrasConvivenciaEnum.NAO;
        }else if(buttonGroupEscoConsRegra.getSelection().equals(jRadioButtonEscolaConsRegraPatua.getModel())){
            RC = RegrasConvivenciaEnum.PRECISA_ATUALIZAR;
        }else RC = RegrasConvivenciaEnum.NAO;
        
        return RC;
    }
    
    
    public void persistEscola(ConselhoCicloEnum conseCiclo){
         Escola escola = (Escola)jComboBox1.getSelectedItem();
         escola.setCCiclo(conseCiclo);
       try{
           em.getTransaction().begin();
           em.persist(escola);
           em.getTransaction().commit();
       }catch(Exception e){
           em.getTransaction().rollback();
            e.printStackTrace();
       }
    }
    
    public void persistEscola(RegrasConvivenciaEnum convivencia){
         Escola escola = (Escola)jComboBox1.getSelectedItem();
         escola.setRegraConvivencia(convivencia);
       try{
           em.getTransaction().begin();
           em.persist(escola);
           em.getTransaction().commit();
       }catch(Exception e){
           em.getTransaction().rollback();
            e.printStackTrace();
       }
    }
    
    public void persitAluno (Aluno aluno) throws Exception {
        
           em.getTransaction().begin();
           em.persist(aluno);
           em.getTransaction().commit();
       
    }
    
    
    public boolean validName(String name){
        if(name.trim().isEmpty())
            return false;
        else
            return true;
    } 
    
    public boolean validTurnma(String name){
        if(name.trim().isEmpty())
            return false;
        else
            return true;
    } 
    public boolean validaButtonGroup(ButtonGroup buttonGroup){
        
        if(buttonGroup.getSelection()==null){
           return false; 
        }else return true;
    }
    
    public boolean checkValidDate(){
        
        try{
            LocalDate.of(Integer.parseInt(jFormattedTextFieldNascimento.getText().substring(6, 10)),
                                         Integer.parseInt(jFormattedTextFieldNascimento.getText().substring(3, 5)),
                                          Integer.parseInt(jFormattedTextFieldNascimento.getText().substring(0, 2)));
            return true;
        }catch(Exception e){
            return false;
            
        }    
    } 
    
    public String filledCheck(){
        String msg="";
        
        if(jComboBox1.getSelectedItem() instanceof Escola){
           // System.out.println("-escola selcionada");
        }else{
            msg+="-Selcione a escola na qual o aluno esta matriculado \n";
        }
        if(!validName(jTextFieldNomeAluno.getText())){
            msg+="-Nome inconsistente\n";
            //System.out.println("-Nome inconsistente");
        }
        if(!jFormattedTextFieldNascimento.isEditValid()){
            msg+="-Preencha a data de Nascimento corretamente\n";
            //System.out.println("-Preencha a data de Nascimento corretamente");
        }
        if(!checkValidDate()){
            msg+="-Preencha uma data de Nascimento válida\n";
        }
        if(!validTurnma(jTextFielTurma.getText())){
            msg+="-Informe uma Turma\n";
            //System.out.println("-Informe uma Turma");
        }if(!validaButtonGroup(buttonGroupSexo)){
            msg+="-Selecione o Sexo do aluno\n";
            //System.out.println("-Selecione o Sexo do aluno");
        }if(!validaButtonGroup(buttonGroupTurno)){
            msg+="-Selecione o turno do aluno\n";
            //System.out.println("-Selcione o Turno do Aluno");
        }if(!validaButtonGroup(buttonGroupEstudouAntes)){
            msg+="-Marque sim ou nao se o aluno estudou na escola no ano anterior\n";
            //System.out.println("-Marque sim ou nao se o aluno estudou na escola no ano anterior");
        }if(!validaButtonGroup(buttonGroupAnoAnte)){
            msg+="-Marque se o aluno ficou retido ou avançou no ano anterior\n";
            //System.out.println("-Marque se o aluno ficou retido ou avançou no ano anterior");
        }
        if(!validaButtonGroup(buttonGroupReside)){ 
            msg+="-Selcione com quem o aluno Reside\n";
            //System.out.println("-Selcione com quem o aluno Reside\n");
        }if(!validaButtonGroup(buttonGroupComportamento)){
            msg+="-Selcione o comportamento do aluno\n";
            //System.out.println("-Selcione o comportamento do aluno\n");            
        }if(!validaButtonGroup(buttonGroupResponsavel)){
            msg+="-Selcione o Responsável pelo aluno\n";
            //System.out.println("-Selcione o Responsável pelo aluno\n");            
        }if(!validaButtonGroup(buttonGroupProjetoSoc)){
            msg+="-Selcione se o aluno esta matriculado em algum projeto social\n";
            //System.out.println("-Selcione se o aluno esta matriculado em algum projeto social");            
        }
        
         return msg;  
    }

    public SexoEnum getSexoSelection(){
        //SexoEnum sexo;
        if(buttonGroupSexo.getSelection().equals(jRadioButtonSexoM.getModel())){
            return SexoEnum.M;
        } else
            return SexoEnum.F;
    }
    public TurnoEnum getTurnoSelect(){
        //TurnoEnum turno;
        
        if(buttonGroupTurno.getSelection().equals(jRadioButtonTurnoManha.getModel())){
            return TurnoEnum.MANHA;
        }if(buttonGroupTurno.getSelection().equals(jRadioButtonTurnoIntermed.getModel())){
            return  TurnoEnum.INTERMEDIARIO;
        }
        if(buttonGroupTurno.getSelection().equals(jRadioButtonTurnoTarde.getModel())){
            return  TurnoEnum.TARDE;
        }
        if(buttonGroupTurno.getSelection().equals(jRadioButtonTurnoNoite.getModel())){
            return  TurnoEnum.NOITE;
        }else
       
            return  TurnoEnum.INTEGRAL;
        
    }
    
    public EstudouEscolaEnum getEstudouEscolaAntesSelection(){
        
        if(buttonGroupEstudouAntes.getSelection().equals(jRadioButtonEscAntSim.getModel())){
          return  EstudouEscolaEnum.SM;
        } else
          return  EstudouEscolaEnum.NAO;
    }
    
    public AnoAnteriorEnum getAnoAnteriorSelection(){
        
        if(buttonGroupAnoAnte.getSelection().equals(jRadioButtonAntAvan.getModel())){
            return  AnoAnteriorEnum.AVANCOU;
        }else
            return AnoAnteriorEnum.RETIDO;
    }
    public ResideEnum getResideSelection(){
        
        if(buttonGroupReside.getSelection().equals(jRadioButtonResidePaiMae.getModel())){
            return ResideEnum.PAI_MAE;
        }
        if(buttonGroupReside.getSelection().equals(jRadioButtonResidemae.getModel())){
            return ResideEnum.MAE;
        }
        if(buttonGroupReside.getSelection().equals(jRadioButtonResidePai.getModel())){
            return ResideEnum.PAI;
        }else 
            return ResideEnum.OUTROS;
        
    }
    
    public ComporamentoEnum getComporamentoSelection(){
        if(buttonGroupComportamento.getSelection().equals(jRadioButtonComportOtimo.getModel())){
            return ComporamentoEnum.OTIMO;
        }if(buttonGroupComportamento.getSelection().equals(jRadioButtonComportbom.getModel())){
            return ComporamentoEnum.BOM;
        }if(buttonGroupComportamento.getSelection().equals(jRadioButtonComportRegular.getModel())){
            return ComporamentoEnum.REGULAR;
        }else
            return ComporamentoEnum.RUIM;
        
    }
    public ProjetoSocialEnum getProjetoSocialSelect(){
        if(buttonGroupProjetoSoc.getSelection().equals(jRadioButtonProjetoSocSim.getModel())){
            return ProjetoSocialEnum.SIM;
        }if(buttonGroupProjetoSoc.getSelection().equals(jRadioButtonProjetoSocNao.getModel())){
            return ProjetoSocialEnum.NAO;
        }else
            return ProjetoSocialEnum.NAO_INDCACAO;
            
    }
    
    public ResponsavelEnum getResponsavelSelect(){
        if(buttonGroupResponsavel.getSelection().equals(jRadioButtonRespMae.getModel())){
            return ResponsavelEnum.MAE;
        }
        if(buttonGroupResponsavel.getSelection().equals(jRadioButtonRespPai.getModel())){
            return ResponsavelEnum.PAI;
        }else
            return ResponsavelEnum.OUTROS;
    }
    
    public ProjetoSocialEnum getConsultaProjetoSocialSelect(){
        if(buttonGroupConsultaProjetoSoc.getSelection().equals(jRadioButtonConsultaProjSocSim.getModel())){
            return ProjetoSocialEnum.SIM;
        }
        if(buttonGroupConsultaProjetoSoc.getSelection().equals(jRadioButtonConsultaProjSocNao.getModel())){
            return ProjetoSocialEnum.NAO;
        }
        else return ProjetoSocialEnum.NAO_INDCACAO;

    }
    
    public void clearTable(JTable table){
        DefaultTableModel defaultTableModel = (DefaultTableModel)table.getModel();
        int i =defaultTableModel.getRowCount();
        while(--i>=0){
            defaultTableModel.removeRow(i);
        }
        
    }
    
    public String trataDadosBoleanos(Boolean b){
            
            if(b){
                return "Sim";
            }else
                return  "Não";
    }
    public String trataDadosResideCom(ResideEnum reside){
        if(reside == ResideEnum.PAI_MAE){
            return "Pai e Mãe";
        }else if(reside == ResideEnum.PAI){
            return "Pai";
        }else if(reside == ResideEnum.MAE){
            return "Mãe";
        }else
            return "Outros";
    }
    
    public String trataDadosResponsavel(ResponsavelEnum responsavel){
        if(responsavel == ResponsavelEnum.MAE){
            return "Mãe";
        }else if(responsavel == ResponsavelEnum.PAI){
            return "Pai";
        }else
             return "Outros";
    }
    
    public String TrataDadosProjetoSocial(ProjetoSocialEnum projeto){
        if(projeto==ProjetoSocialEnum.SIM){
            return "Sim";
        }else if(projeto == ProjetoSocialEnum.NAO){
            return "Não";
        }else 
            return "Não mas tem indicação";
    }

    
    public void fillJTable(JTable table,List<Aluno> alunos){
        
        DefaultTableModel defaultTableModel = (DefaultTableModel)table.getModel();
        clearTable(jTable1);
        Iterator i = alunos.iterator();
        int count=0;
        while(i.hasNext()){ 
           Aluno a = (Aluno)i.next();
           
           defaultTableModel.addRow(new Object[]{a.getNome(),
                                                 a.getSexo(),
                                                 a.getNascimento(),
                                                 a.getTurma(),
                                                 a.getTurno(),
                                                 a.getEstudouEscolaAnoAntes(),
                                                 a.getAnoAnterior(),
                                                 trataDadosResideCom(a.getResideCom()),
                                                 a.getComportamento(),
                                                 trataDadosBoleanos(a.isConheceRegras()),
                                                 trataDadosBoleanos(a.isRespeitaProfissionais()),
                                                 trataDadosBoleanos(a.isRespeitaPatrimonio()),
                                                 trataDadosBoleanos(a.isRespeitaColegas()),
                                                 trataDadosBoleanos(a.isViveuDescPortadorNecEspeciais()),
                                                 trataDadosBoleanos(a.isViveuDescRacismoGenBully()),
                                                 trataDadosBoleanos(a.isUsoBebida()),
                                                 trataDadosBoleanos(a.isAgressoes()),
                                                 trataDadosBoleanos(a.isRoubos()),
                                                 trataDadosBoleanos(a.isGravidezPrecoce()),
                                                 trataDadosBoleanos(a.isUsoTraficoDrogas()),
                                                 trataDadosResponsavel(a.getResponsavel()),
                                                 TrataDadosProjetoSocial(a.getProjetoSocial()),
                                                 trataDadosBoleanos(a.isEncaminhaMinisterioPublico()),
                                                 trataDadosBoleanos(a.isVulneravel()),
                                                 trataDadosBoleanos(a.isMaisEducacao()),
                                                 trataDadosBoleanos(a.isRespeitaRegras()),
                                                 trataDadosBoleanos(a.isAusenciaciaCominucada())
                                                });
           count++;
        }
        jTextFieldTotal.setText(Integer.toString(count));
        
    }
    
    public List<Aluno> searchAluno(){
        
        boolean first=false;
        try{
            
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Aluno> criteriaQuery = criteriaBuilder.createQuery(Aluno.class);
            Root<Aluno> aluno = criteriaQuery.from(Aluno.class);
            //Root<Escola> escola = criteriaQuery.from(Escola.class);
            Join<Aluno,Escola> alunoEscola = aluno.join(Aluno_.escola);
            
             Predicate predicateList=criteriaBuilder.and();
             
            if(jComboBox2.getSelectedItem() instanceof Escola){
                Escola e = (Escola)jComboBox2.getSelectedItem();
                System.out.println("Teste JCombo Escola Consulta:" + e.getInep());
                
                //criteriaQuery.where(criteriaBuilder.equal(alunoEscola.get(Escola_.inep),e.getInep()));                
                predicateList=criteriaBuilder.and(predicateList,criteriaBuilder.equal(alunoEscola.get(Escola_.inep),e.getInep()));
                
             }
            if(jRadioButtonConsultaResFamSub.isSelected()){
                
                predicateList=criteriaBuilder.and(predicateList,criteriaBuilder.equal(aluno.get(Aluno_.resideCom), ResideEnum.OUTROS));
                
            }
            if(jRadioButtonConsultaDescRacialGenBull.isSelected()){
                predicateList=criteriaBuilder.and(predicateList,criteriaBuilder.equal(aluno.get(Aluno_.viveuDescRacismoGenBully), true));
            }
            if(jRadioButtonConsultaUsoTrafico.isSelected()){ //falta testar daqui pra baixo
                predicateList=criteriaBuilder.and(predicateList,criteriaBuilder.equal(aluno.get(Aluno_.usoTraficoDrogas),true));
            }
            
            if(jRadioButtonConsultaVulnerabilidade.isSelected()){
                predicateList=criteriaBuilder.and(predicateList,criteriaBuilder.equal(aluno.get(Aluno_.vulneravel),true));
            }
            if(jRadioButtonConsultaRespRegras.isSelected()){
                predicateList=criteriaBuilder.and(predicateList,criteriaBuilder.equal(aluno.get(Aluno_.respeitaRegras),true));
            }
            if(validaButtonGroup(buttonGroupConsultaProjetoSoc)){
                predicateList=criteriaBuilder.and(predicateList,criteriaBuilder.equal(aluno.get(Aluno_.projetoSocial), getConsultaProjetoSocialSelect()));
            }   
            criteriaQuery.where(predicateList);
            
             TypedQuery<Aluno> qAluno = em.createQuery(criteriaQuery);  
             List<Aluno> lstAlunos  = qAluno.getResultList();
            
            
            for(Aluno a: lstAlunos ){
                System.out.println("search aluno nome: "+a.getNome()  + "     -- Escola: " + a.getEscola().getDistrito());
                System.out.println("search aluno sexo: "+a.getSexo());
            }
            return lstAlunos; 
        }catch(Exception e){
            e.printStackTrace();
        }
           return null; 
           
    }
            
            
    public Aluno createAluno(){
        Aluno aluno = new Aluno();
        aluno.setNome(jTextFieldNomeAluno.getText());
        System.out.println("getSexoSelection: "+getSexoSelection().name());
        
        aluno.setSexo(getSexoSelection());
        
        aluno.setNascimento(LocalDate.of(Integer.parseInt(jFormattedTextFieldNascimento.getText().substring(6, 10)),
                                         Integer.parseInt(jFormattedTextFieldNascimento.getText().substring(3, 5)),
                                          Integer.parseInt(jFormattedTextFieldNascimento.getText().substring(0, 2))));
        
        aluno.setTurma(jTextFielTurma.getText());
        aluno.setTurno(getTurnoSelect());
        System.out.println("getEscolaAnoanteriorSelection:"+ getEstudouEscolaAntesSelection().name());
        aluno.setEstudouEscolaAnoAntes(getEstudouEscolaAntesSelection());
        aluno.setAnoAnterior(getAnoAnteriorSelection());
        aluno.setResideCom(getResideSelection());
        aluno.setComportamento(getComporamentoSelection());
        aluno.setConheceRegras(jRadioButtonRegraConv.isSelected());
        aluno.setRespeitaProfissionais(jRadioButtonRespeitaProf.isSelected());
        aluno.setRespeitaPatrimonio(jRadioButtonRespeitaPatri.isSelected());
        aluno.setRespeitaColegas(jRadioButtonRespeitaColega.isSelected());
        aluno.setViveuDescPortadorNecEspeciais(jRadioButtonVivNecEsp.isSelected());
        aluno.setViveuDescRacismoGenBully(jRadioButtonVivRacGenBull.isSelected());
        aluno.setUsoBebida(jRadioButtonVivUsoBebCig.isSelected());
        aluno.setAgressoes(jRadioButtonVivAgreFisBrig.isSelected());
        aluno.setRoubos(jRadioButtonVivRoubFurt.isSelected());
        aluno.setGravidezPrecoce(jRadioButtonVivGravPrec.isSelected());
        aluno.setUsoTraficoDrogas(jRadioButtonVivUsoTrafDrogas.isSelected());
        aluno.setResponsavel(getResponsavelSelect());
        aluno.setProjetoSocial(getProjetoSocialSelect());
        aluno.setEncaminhaMinisterioPublico(jRadioButtonEncaMinCons.isSelected());
        aluno.setVulneravel(jRadioButtonVulneravel.isSelected());
        aluno.setMaisEducacao(jRadioButtonMaisEduc.isSelected());
        aluno.setRespeitaRegras(jRadioButtonRespRegraConv.isSelected());
        aluno.setAusenciaciaCominucada(jRadioButtonAuseEnc.isSelected());
        aluno.setEscola((Escola)jComboBox1.getSelectedItem());
        
        return aluno;
    }
    
    public void clearScrean(){
        jTextFieldNomeAluno.setText("");
        jTextFielTurma.setText("");
        jFormattedTextFieldNascimento.setText("");
        buttonGroupSexo.clearSelection();
        buttonGroupTurno.clearSelection();
        buttonGroupEstudouAntes.clearSelection();
        buttonGroupAnoAnte.clearSelection();
        buttonGroupReside.clearSelection();
        buttonGroupComportamento.clearSelection();
        buttonGroupResponsavel.clearSelection();
        buttonGroupProjetoSoc.clearSelection();
        jRadioButtonRegraConv.setSelected(false);
        jRadioButtonRespeitaProf.setSelected(false);
        jRadioButtonRespeitaPatri.setSelected(false);
        jRadioButtonRespeitaColega.setSelected(false);
        jRadioButtonVivNecEsp.setSelected(false);
        jRadioButtonVivRacGenBull.setSelected(false);
        jRadioButtonVivUsoBebCig.setSelected(false);
        jRadioButtonVivAgreFisBrig.setSelected(false);
        jRadioButtonVivRoubFurt.setSelected(false);
        jRadioButtonVivGravPrec.setSelected(false);
        jRadioButtonVivUsoTrafDrogas.setSelected(false);
        jRadioButtonEncaMinCons.setSelected(false);
        jRadioButtonVulneravel.setSelected(false);
        jRadioButtonMaisEduc.setSelected(false);
        jRadioButtonRespRegraConv.setSelected(false);
        jRadioButtonAuseEnc.setSelected(false);
   }
   public void clearPesquisa(){
            jComboBox2.setSelectedIndex(0);
            jRadioButtonConsultaResFamSub.setSelected(false);
            jRadioButtonConsultaDescRacialGenBull.setSelected(false);
            jRadioButtonConsultaUsoTrafico.setSelected(false);
            jRadioButtonConsultaVulnerabilidade.setSelected(false);
            jRadioButtonConsultaRespRegras.setSelected(false);
            buttonGroupConsultaProjetoSoc.clearSelection();
   }
    
    
    
    
    //fim do argupamento dos radiobuttons
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupReside = new javax.swing.ButtonGroup();
        buttonGroupSexo = new javax.swing.ButtonGroup();
        buttonGroupTurno = new javax.swing.ButtonGroup();
        buttonGroupAnoAnte = new javax.swing.ButtonGroup();
        buttonGroupEstudouAntes = new javax.swing.ButtonGroup();
        buttonGroupComportamento = new javax.swing.ButtonGroup();
        buttonGroupConseCiclo = new javax.swing.ButtonGroup();
        buttonGroupResponsavel = new javax.swing.ButtonGroup();
        buttonGroupProjetoSoc = new javax.swing.ButtonGroup();
        buttonGroupEscoConsRegra = new javax.swing.ButtonGroup();
        buttonGroupConsultaProjetoSoc = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextFieldDistrito = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldInep = new javax.swing.JTextField();
        jRadioButtonConseAtivo = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jRadioButtonConseCicl1 = new javax.swing.JRadioButton();
        jRadioButtonConseCicl2 = new javax.swing.JRadioButton();
        jRadioButtonConseCicl3 = new javax.swing.JRadioButton();
        jRadioButtonConseCicl4 = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jRadioButtonEscolaConsRegraSim = new javax.swing.JRadioButton();
        jRadioButtonEscolaConsRegraNao = new javax.swing.JRadioButton();
        jRadioButtonEscolaConsRegraPatua = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNomeAluno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jRadioButtonSexoM = new javax.swing.JRadioButton();
        jRadioButtonSexoF = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jRadioButtonTurnoManha = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jRadioButtonTurnoTarde = new javax.swing.JRadioButton();
        jRadioButtonTurnoNoite = new javax.swing.JRadioButton();
        jRadioButtonTurnoInt = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jTextFielTurma = new javax.swing.JTextField();
        jRadioButtonEscAntSim = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jRadioButtonEscAntNao = new javax.swing.JRadioButton();
        jRadioButtonAntRet = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jRadioButtonAntAvan = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jRadioButtonResidePaiMae = new javax.swing.JRadioButton();
        jRadioButtonResidePai = new javax.swing.JRadioButton();
        jRadioButtonResidemae = new javax.swing.JRadioButton();
        jRadioButtonResideoutros = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jRadioButtonComportOtimo = new javax.swing.JRadioButton();
        jRadioButtonComportbom = new javax.swing.JRadioButton();
        jRadioButtonComportRegular = new javax.swing.JRadioButton();
        jRadioButtonComportRuim = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jRadioButtonRegraConv = new javax.swing.JRadioButton();
        jRadioButtonRespeitaProf = new javax.swing.JRadioButton();
        jRadioButtonRespeitaPatri = new javax.swing.JRadioButton();
        jRadioButtonRespeitaColega = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jRadioButtonVivNecEsp = new javax.swing.JRadioButton();
        jRadioButtonVivRacGenBull = new javax.swing.JRadioButton();
        jRadioButtonVivUsoBebCig = new javax.swing.JRadioButton();
        jRadioButtonVivAgreFisBrig = new javax.swing.JRadioButton();
        jRadioButtonVivRoubFurt = new javax.swing.JRadioButton();
        jRadioButtonVivUsoTrafDrogas = new javax.swing.JRadioButton();
        jRadioButtonVivGravPrec = new javax.swing.JRadioButton();
        jFormattedTextFieldNascimento = new javax.swing.JFormattedTextField(getMask("##/##/####"));
        jRadioButtonTurnoIntermed = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jRadioButtonRespMae = new javax.swing.JRadioButton();
        jRadioButtonRespPai = new javax.swing.JRadioButton();
        jRadioButtonRespOutros = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jRadioButtonProjetoSocNao = new javax.swing.JRadioButton();
        jRadioButtonProjetoSocSim = new javax.swing.JRadioButton();
        jRadioButtonProjetoSocNaoInd = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jRadioButtonAuseEnc = new javax.swing.JRadioButton();
        jRadioButtonVulneravel = new javax.swing.JRadioButton();
        jRadioButtonMaisEduc = new javax.swing.JRadioButton();
        jRadioButtonEncaMinCons = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jRadioButtonRespRegraConv = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jRadioButtonConsultaResFamSub = new javax.swing.JRadioButton();
        jRadioButtonConsultaDescRacialGenBull = new javax.swing.JRadioButton();
        jRadioButtonConsultaUsoTrafico = new javax.swing.JRadioButton();
        jRadioButtonConsultaVulnerabilidade = new javax.swing.JRadioButton();
        jRadioButtonConsultaRespRegras = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jRadioButtonConsultaProjSocSim = new javax.swing.JRadioButton();
        jRadioButtonConsultaProjSocNao = new javax.swing.JRadioButton();
        jRadioButtonConsultaProjSocNaoInd = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(680, 686));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Escola", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 102, 51))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Nome:");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextFieldDistrito.setEditable(false);
        jTextFieldDistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDistritoActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Distrito:");

        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("INEP:");

        jTextFieldInep.setEditable(false);
        jTextFieldInep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInepActionPerformed(evt);
            }
        });

        jRadioButtonConseAtivo.setText(" Conselho escolar ativo");
        jRadioButtonConseAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonConseAtivoActionPerformed(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(0, 51, 204));
        jLabel17.setText("C. Ciclo realizado:");

        jRadioButtonConseCicl1.setText("1º");
        jRadioButtonConseCicl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonConseCicl1ActionPerformed(evt);
            }
        });

        jRadioButtonConseCicl2.setText("2º");
        jRadioButtonConseCicl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonConseCicl2ActionPerformed(evt);
            }
        });

        jRadioButtonConseCicl3.setText("3º");
        jRadioButtonConseCicl3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonConseCicl3ActionPerformed(evt);
            }
        });

        jRadioButtonConseCicl4.setText("4º");
        jRadioButtonConseCicl4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonConseCicl4ActionPerformed(evt);
            }
        });

        jLabel16.setForeground(new java.awt.Color(0, 51, 204));
        jLabel16.setText("A escola tem regras de convivência construidas com o coletivo:");

        jRadioButtonEscolaConsRegraSim.setText("Sim");
        jRadioButtonEscolaConsRegraSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEscolaConsRegraSimActionPerformed(evt);
            }
        });

        jRadioButtonEscolaConsRegraNao.setText("não");
        jRadioButtonEscolaConsRegraNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEscolaConsRegraNaoActionPerformed(evt);
            }
        });

        jRadioButtonEscolaConsRegraPatua.setText("precisa atualizar");
        jRadioButtonEscolaConsRegraPatua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEscolaConsRegraPatuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonEscolaConsRegraSim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonEscolaConsRegraNao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonEscolaConsRegraPatua))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldInep, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonConseAtivo)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonConseCicl1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonConseCicl2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonConseCicl3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonConseCicl4)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldInep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButtonConseAtivo)
                    .addComponent(jLabel17)
                    .addComponent(jRadioButtonConseCicl1)
                    .addComponent(jRadioButtonConseCicl2)
                    .addComponent(jRadioButtonConseCicl3)
                    .addComponent(jRadioButtonConseCicl4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jRadioButtonEscolaConsRegraSim)
                    .addComponent(jRadioButtonEscolaConsRegraNao)
                    .addComponent(jRadioButtonEscolaConsRegraPatua))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Aluno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 102, 51))); // NOI18N

        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Nome:");

        jTextFieldNomeAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeAlunoActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("Sexo:");

        jRadioButtonSexoM.setText("M");
        jRadioButtonSexoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSexoMActionPerformed(evt);
            }
        });

        jRadioButtonSexoF.setText("F");

        jLabel6.setForeground(new java.awt.Color(0, 51, 204));
        jLabel6.setText("Nascimento:");

        jRadioButtonTurnoManha.setText("manhã");
        jRadioButtonTurnoManha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTurnoManhaActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("Turno:");

        jRadioButtonTurnoTarde.setText("tarde");

        jRadioButtonTurnoNoite.setText("noite");

        jRadioButtonTurnoInt.setText("integral");

        jLabel9.setForeground(new java.awt.Color(0, 51, 204));
        jLabel9.setText("Turma:");

        jRadioButtonEscAntSim.setText("sim");
        jRadioButtonEscAntSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEscAntSimActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(0, 51, 204));
        jLabel10.setText("Estudou na Escola Ano Anterior:");

        jRadioButtonEscAntNao.setText("nao");

        jRadioButtonAntRet.setText("ficou retido");

        jLabel11.setForeground(new java.awt.Color(0, 51, 204));
        jLabel11.setText("Ano Anterior:");

        jRadioButtonAntAvan.setText("avançou");
        jRadioButtonAntAvan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAntAvanActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(0, 51, 204));
        jLabel12.setText("Reside com:");

        jRadioButtonResidePaiMae.setText("pai e mâe");
        jRadioButtonResidePaiMae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonResidePaiMaeActionPerformed(evt);
            }
        });

        jRadioButtonResidePai.setText("pai");

        jRadioButtonResidemae.setText("mâe");

        jRadioButtonResideoutros.setText("outros");

        jLabel13.setForeground(new java.awt.Color(0, 51, 204));
        jLabel13.setText("Comportamento:");

        jRadioButtonComportOtimo.setText("ótimo");
        jRadioButtonComportOtimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonComportOtimoActionPerformed(evt);
            }
        });

        jRadioButtonComportbom.setText("bom");

        jRadioButtonComportRegular.setText("regular");

        jRadioButtonComportRuim.setText("ruim");

        jLabel14.setForeground(new java.awt.Color(0, 51, 204));
        jLabel14.setText("Aluno afirma:");

        jRadioButtonRegraConv.setText("que conhece as regras da escola");
        jRadioButtonRegraConv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRegraConvActionPerformed(evt);
            }
        });

        jRadioButtonRespeitaProf.setText("que respeita os profissionais da escola");
        jRadioButtonRespeitaProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRespeitaProfActionPerformed(evt);
            }
        });

        jRadioButtonRespeitaPatri.setText("que respeita o patrimônio público");

        jRadioButtonRespeitaColega.setText("que respeita os colegas da escola");

        jLabel15.setForeground(new java.awt.Color(0, 51, 204));
        jLabel15.setText("O aluno já vivenciou:");

        jRadioButtonVivNecEsp.setText("descriminação de portadores de necessidades especiais");

        jRadioButtonVivRacGenBull.setText("descriminção racial, de gênero ou Bullying");
        jRadioButtonVivRacGenBull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVivRacGenBullActionPerformed(evt);
            }
        });

        jRadioButtonVivUsoBebCig.setText("uso de bebida alcoolica ou cigarro");
        jRadioButtonVivUsoBebCig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVivUsoBebCigActionPerformed(evt);
            }
        });

        jRadioButtonVivAgreFisBrig.setText("agresões fisicas ou brigas de gangues");
        jRadioButtonVivAgreFisBrig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVivAgreFisBrigActionPerformed(evt);
            }
        });

        jRadioButtonVivRoubFurt.setText("roubos e furtos");

        jRadioButtonVivUsoTrafDrogas.setText("uso ou trafico de drogas");

        jRadioButtonVivGravPrec.setText("gravidez precoce");
        jRadioButtonVivGravPrec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVivGravPrecActionPerformed(evt);
            }
        });

        jFormattedTextFieldNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldNascimentoActionPerformed(evt);
            }
        });

        jRadioButtonTurnoIntermed.setText("intermediário");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonSexoM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonSexoF))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonResidePaiMae)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonResidePai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonResidemae)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonResideoutros)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonComportOtimo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonComportbom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonComportRegular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonComportRuim)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonRegraConv)
                            .addComponent(jLabel14)
                            .addComponent(jRadioButtonRespeitaProf)
                            .addComponent(jRadioButtonRespeitaPatri)
                            .addComponent(jRadioButtonRespeitaColega))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonVivRacGenBull)
                                    .addComponent(jRadioButtonVivUsoBebCig)
                                    .addComponent(jRadioButtonVivNecEsp)
                                    .addComponent(jRadioButtonVivAgreFisBrig))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jRadioButtonVivUsoTrafDrogas))
                                    .addComponent(jRadioButtonVivGravPrec)
                                    .addComponent(jRadioButtonVivRoubFurt))))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonEscAntSim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonEscAntNao)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonAntRet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonAntAvan)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(60, 60, 60))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jTextFielTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74)))
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonTurnoManha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonTurnoIntermed)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonTurnoTarde)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonTurnoNoite)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonTurnoInt)))
                        .addGap(29, 29, 29))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jRadioButtonSexoM)
                    .addComponent(jRadioButtonSexoF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTextFielTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jRadioButtonTurnoManha)
                        .addComponent(jLabel8)
                        .addComponent(jRadioButtonTurnoTarde)
                        .addComponent(jRadioButtonTurnoNoite)
                        .addComponent(jRadioButtonTurnoInt)
                        .addComponent(jFormattedTextFieldNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButtonTurnoIntermed)))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonEscAntSim)
                    .addComponent(jLabel10)
                    .addComponent(jRadioButtonEscAntNao)
                    .addComponent(jLabel11)
                    .addComponent(jRadioButtonAntRet)
                    .addComponent(jRadioButtonAntAvan))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jRadioButtonResidePaiMae)
                    .addComponent(jRadioButtonResidePai)
                    .addComponent(jRadioButtonResidemae)
                    .addComponent(jRadioButtonResideoutros)
                    .addComponent(jLabel13)
                    .addComponent(jRadioButtonComportOtimo)
                    .addComponent(jRadioButtonComportbom)
                    .addComponent(jRadioButtonComportRegular)
                    .addComponent(jRadioButtonComportRuim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonVivNecEsp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonVivRacGenBull)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonVivUsoBebCig)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonVivAgreFisBrig))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonRegraConv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonRespeitaProf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonRespeitaPatri)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonRespeitaColega))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButtonVivRoubFurt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonVivGravPrec)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonVivUsoTrafDrogas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Coordenador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 102, 51))); // NOI18N

        jLabel18.setForeground(new java.awt.Color(0, 51, 204));
        jLabel18.setText("Responsável pelo aluno:");

        jRadioButtonRespMae.setText("mãe");

        jRadioButtonRespPai.setText("pai");

        jRadioButtonRespOutros.setText("outros");

        jLabel19.setForeground(new java.awt.Color(0, 51, 204));
        jLabel19.setText("matriculado em projeto social:");

        jRadioButtonProjetoSocNao.setText("não");

        jRadioButtonProjetoSocSim.setText("sim");

        jRadioButtonProjetoSocNaoInd.setText("não e tem indicação");

        jLabel20.setForeground(new java.awt.Color(0, 51, 204));
        jLabel20.setText("Outros:");

        jRadioButtonAuseEnc.setText("ausência do aluno ou responsável é comunicada ao conselho tutelar");

        jRadioButtonVulneravel.setText("está em vulnerabilidade");

        jRadioButtonMaisEduc.setText("está matriculado no programa mais educação");

        jRadioButtonEncaMinCons.setText("encaminhado pelo Ministério Público ou Conselho tutelar");

        jLabel21.setForeground(new java.awt.Color(0, 51, 204));
        jLabel21.setText("O Aluno:");

        jRadioButtonRespRegraConv.setText("Respeita Regas de convivência");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonRespRegraConv)
                            .addComponent(jRadioButtonMaisEduc)
                            .addComponent(jRadioButtonVulneravel)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonRespMae)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonRespPai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonRespOutros))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonProjetoSocSim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonProjetoSocNao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonProjetoSocNaoInd)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jRadioButtonEncaMinCons, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonAuseEnc)
                        .addGap(59, 59, 59))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(373, 373, 373))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jRadioButtonRespMae)
                    .addComponent(jRadioButtonRespPai)
                    .addComponent(jRadioButtonRespOutros))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jRadioButtonProjetoSocNaoInd)
                    .addComponent(jRadioButtonProjetoSocSim)
                    .addComponent(jRadioButtonProjetoSocNao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonAuseEnc)
                    .addComponent(jRadioButtonEncaMinCons))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonVulneravel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonMaisEduc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonRespRegraConv))
        );

        jButton1.setText("Salva");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(379, 379, 379))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Cadastro Anulos", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aluno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 102, 51))); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setForeground(new java.awt.Color(0, 51, 204));
        jLabel23.setText("Escola:");

        jRadioButtonConsultaResFamSub.setText("reside com familias substitutas");

        jRadioButtonConsultaDescRacialGenBull.setText("descriminção racial, de gênero ou Bullying");

        jRadioButtonConsultaUsoTrafico.setText("uso ou trafico de drogas");

        jRadioButtonConsultaVulnerabilidade.setText(" em vulnerabilidade");
        jRadioButtonConsultaVulnerabilidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonConsultaVulnerabilidadeActionPerformed(evt);
            }
        });

        jRadioButtonConsultaRespRegras.setText("respeita as regras da escola");

        jButton2.setText("pesquisar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel24.setForeground(new java.awt.Color(0, 51, 204));
        jLabel24.setText("matriculado em projeto social:");

        jRadioButtonConsultaProjSocSim.setText("sim");

        jRadioButtonConsultaProjSocNao.setText("não");

        jRadioButtonConsultaProjSocNaoInd.setText("não e tem indicação");

        jButton3.setText("Desmarcar Opções");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Sexo", "Nascimento", "Turma", "Turno", "Estudou na escola ano anterior", "Avançou/retido ano anterior", "Reside com", "Comportamento", "Conhece Regras", "Respeita Profissionais", "Respeita Patrimônio", "Respeita Colegas", "Vivenciou Desc. com port. de necessidades especiais", "Vivenciou Desc. contra Raça, gênero ou bullying", "Vivenciou Uso de Bebida Alcoolica", "Vivenciou Agressões", "Vivenciiou Roubos e Furtos", "Vivenciou Gravidez Precoce", "Vivenciou Trafico de Drogras", "Responsável", "Mat. em Projeto Soc.", "Encaminhado pelo MP", "Vulnerável", "Mais Educação", "Respeita as Regras", "Ausencia Comunicada"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setMinimumSize(new java.awt.Dimension(40, 16));
        jTable1.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        jLabel25.setText("Total de Registros:");

        jTextFieldTotal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextFieldTotal.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jRadioButtonConsultaResFamSub)
                                    .addGap(10, 10, 10)
                                    .addComponent(jRadioButtonConsultaDescRacialGenBull)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jRadioButtonConsultaUsoTrafico)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jRadioButtonConsultaVulnerabilidade))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonConsultaRespRegras)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonConsultaProjSocSim)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButtonConsultaProjSocNao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButtonConsultaProjSocNaoInd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                                        .addComponent(jButton3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2)))))
                        .addGap(41, 41, 41))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonConsultaResFamSub)
                    .addComponent(jRadioButtonConsultaDescRacialGenBull)
                    .addComponent(jRadioButtonConsultaUsoTrafico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonConsultaVulnerabilidade)
                    .addComponent(jRadioButtonConsultaRespRegras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jRadioButtonConsultaProjSocSim)
                    .addComponent(jRadioButtonConsultaProjSocNao)
                    .addComponent(jRadioButtonConsultaProjSocNaoInd)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(281, Short.MAX_VALUE))
        );

        jLabel22.setText("Consulta de alunos ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultas", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 658, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("tab2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(filledCheck().isEmpty()){
             
            try{
                persitAluno(createAluno()); 
                JOptionPane.showMessageDialog(null,"Cadastro Realizado" ,"Concluido",JOptionPane.INFORMATION_MESSAGE);
                clearScrean();
                
            }catch(Exception e){
                em.getTransaction().rollback();
                JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO ao inserir",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }        
        }else
            JOptionPane.showMessageDialog(null,filledCheck(),"Verifique o Preenchimento",JOptionPane.ERROR_MESSAGE);
           
        System.out.println(jFormattedTextFieldNascimento.getText().substring(0, 2));
        System.out.println(jFormattedTextFieldNascimento.getText().substring(3, 5));
        System.out.println(jFormattedTextFieldNascimento.getText().substring(6, 10));
        
//        LocalDate nascimento = LocalDate.of(Integer.parseInt(jFormattedTextFieldNascimento.getText().substring(6, 10)), //Year
//                                        Integer.parseInt(jFormattedTextFieldNascimento.getText().substring(3, 5)), //month
//                                        Integer.parseInt(jFormattedTextFieldNascimento.getText().substring(0, 2)));// Day
//        
//      DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
//        
//        System.out.println(nascimento);
//    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       if( jComboBox1.getSelectedItem() instanceof Escola){
           Escola escola = (Escola) jComboBox1.getSelectedItem();
           jTextFieldInep.setText(Integer.toString(escola.getInep())); 
           jTextFieldDistrito.setText(escola.getDistrito());
           jRadioButtonConseAtivo.setSelected(escola.getConselho());
           setSelectedCcicloRadioSelect(escola);
           setSelectedConvivencia(escola);
           
       }else
       {
           jTextFieldDistrito.setText("");
           jTextFieldInep.setText("");
           jRadioButtonConseAtivo.setSelected(false);
           buttonGroupConseCiclo.clearSelection();
           buttonGroupEscoConsRegra.clearSelection();
           buttonGroupEscoConsRegra.clearSelection();
       }        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextFieldDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDistritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDistritoActionPerformed

    private void jTextFieldInepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInepActionPerformed

    private void jTextFieldNomeAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeAlunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeAlunoActionPerformed

    private void jRadioButtonSexoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSexoMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonSexoMActionPerformed

    private void jRadioButtonTurnoManhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTurnoManhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonTurnoManhaActionPerformed

    private void jRadioButtonEscAntSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEscAntSimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonEscAntSimActionPerformed

    private void jRadioButtonAntAvanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAntAvanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonAntAvanActionPerformed

    private void jRadioButtonResidePaiMaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonResidePaiMaeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonResidePaiMaeActionPerformed

    private void jRadioButtonComportOtimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonComportOtimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonComportOtimoActionPerformed

    private void jRadioButtonRegraConvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRegraConvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonRegraConvActionPerformed

    private void jRadioButtonRespeitaProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRespeitaProfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonRespeitaProfActionPerformed

    private void jRadioButtonVivRacGenBullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVivRacGenBullActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonVivRacGenBullActionPerformed

    private void jRadioButtonVivUsoBebCigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVivUsoBebCigActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonVivUsoBebCigActionPerformed

    private void jRadioButtonVivAgreFisBrigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVivAgreFisBrigActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonVivAgreFisBrigActionPerformed

    private void jRadioButtonConseAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonConseAtivoActionPerformed
       Escola escola = (Escola)jComboBox1.getSelectedItem();
       escola.setConselho(jRadioButtonConseAtivo.isSelected());
       try{
           em.getTransaction().begin();
           em.persist(escola);
           em.getTransaction().commit();
       }catch(Exception e){
           em.getTransaction().rollback();
            e.printStackTrace();
       }
    }//GEN-LAST:event_jRadioButtonConseAtivoActionPerformed

    private void jRadioButtonConseCicl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonConseCicl2ActionPerformed
           persistEscola(getSelectionConselhoCiclo());
    }//GEN-LAST:event_jRadioButtonConseCicl2ActionPerformed

    private void jRadioButtonVivGravPrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVivGravPrecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonVivGravPrecActionPerformed

    private void jFormattedTextFieldNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldNascimentoActionPerformed

    private void jRadioButtonConseCicl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonConseCicl1ActionPerformed
        persistEscola(getSelectionConselhoCiclo());
      
    }//GEN-LAST:event_jRadioButtonConseCicl1ActionPerformed

    private void jRadioButtonConseCicl3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonConseCicl3ActionPerformed
        persistEscola(getSelectionConselhoCiclo());
    }//GEN-LAST:event_jRadioButtonConseCicl3ActionPerformed

    private void jRadioButtonConseCicl4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonConseCicl4ActionPerformed
        persistEscola(getSelectionConselhoCiclo());
    }//GEN-LAST:event_jRadioButtonConseCicl4ActionPerformed

    private void jRadioButtonEscolaConsRegraSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEscolaConsRegraSimActionPerformed
            persistEscola(getSelectionRegrasConv());
    }//GEN-LAST:event_jRadioButtonEscolaConsRegraSimActionPerformed

    private void jRadioButtonEscolaConsRegraNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEscolaConsRegraNaoActionPerformed
        persistEscola(getSelectionRegrasConv());
    }//GEN-LAST:event_jRadioButtonEscolaConsRegraNaoActionPerformed

    private void jRadioButtonEscolaConsRegraPatuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEscolaConsRegraPatuaActionPerformed
        persistEscola(getSelectionRegrasConv());
    }//GEN-LAST:event_jRadioButtonEscolaConsRegraPatuaActionPerformed

    private void jRadioButtonConsultaVulnerabilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonConsultaVulnerabilidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonConsultaVulnerabilidadeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        fillJTable(jTable1, searchAluno());         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    clearPesquisa();
    }//GEN-LAST:event_jButton3ActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PesquisaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PesquisaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupAnoAnte;
    private javax.swing.ButtonGroup buttonGroupComportamento;
    private javax.swing.ButtonGroup buttonGroupConseCiclo;
    private javax.swing.ButtonGroup buttonGroupConsultaProjetoSoc;
    private javax.swing.ButtonGroup buttonGroupEscoConsRegra;
    private javax.swing.ButtonGroup buttonGroupEstudouAntes;
    private javax.swing.ButtonGroup buttonGroupProjetoSoc;
    private javax.swing.ButtonGroup buttonGroupReside;
    private javax.swing.ButtonGroup buttonGroupResponsavel;
    private javax.swing.ButtonGroup buttonGroupSexo;
    private javax.swing.ButtonGroup buttonGroupTurno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<Escola> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextFieldNascimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonAntAvan;
    private javax.swing.JRadioButton jRadioButtonAntRet;
    private javax.swing.JRadioButton jRadioButtonAuseEnc;
    private javax.swing.JRadioButton jRadioButtonComportOtimo;
    private javax.swing.JRadioButton jRadioButtonComportRegular;
    private javax.swing.JRadioButton jRadioButtonComportRuim;
    private javax.swing.JRadioButton jRadioButtonComportbom;
    private javax.swing.JRadioButton jRadioButtonConseAtivo;
    private javax.swing.JRadioButton jRadioButtonConseCicl1;
    private javax.swing.JRadioButton jRadioButtonConseCicl2;
    private javax.swing.JRadioButton jRadioButtonConseCicl3;
    private javax.swing.JRadioButton jRadioButtonConseCicl4;
    private javax.swing.JRadioButton jRadioButtonConsultaDescRacialGenBull;
    private javax.swing.JRadioButton jRadioButtonConsultaProjSocNao;
    private javax.swing.JRadioButton jRadioButtonConsultaProjSocNaoInd;
    private javax.swing.JRadioButton jRadioButtonConsultaProjSocSim;
    private javax.swing.JRadioButton jRadioButtonConsultaResFamSub;
    private javax.swing.JRadioButton jRadioButtonConsultaRespRegras;
    private javax.swing.JRadioButton jRadioButtonConsultaUsoTrafico;
    private javax.swing.JRadioButton jRadioButtonConsultaVulnerabilidade;
    private javax.swing.JRadioButton jRadioButtonEncaMinCons;
    private javax.swing.JRadioButton jRadioButtonEscAntNao;
    private javax.swing.JRadioButton jRadioButtonEscAntSim;
    private javax.swing.JRadioButton jRadioButtonEscolaConsRegraNao;
    private javax.swing.JRadioButton jRadioButtonEscolaConsRegraPatua;
    private javax.swing.JRadioButton jRadioButtonEscolaConsRegraSim;
    private javax.swing.JRadioButton jRadioButtonMaisEduc;
    private javax.swing.JRadioButton jRadioButtonProjetoSocNao;
    private javax.swing.JRadioButton jRadioButtonProjetoSocNaoInd;
    private javax.swing.JRadioButton jRadioButtonProjetoSocSim;
    private javax.swing.JRadioButton jRadioButtonRegraConv;
    private javax.swing.JRadioButton jRadioButtonResidePai;
    private javax.swing.JRadioButton jRadioButtonResidePaiMae;
    private javax.swing.JRadioButton jRadioButtonResidemae;
    private javax.swing.JRadioButton jRadioButtonResideoutros;
    private javax.swing.JRadioButton jRadioButtonRespMae;
    private javax.swing.JRadioButton jRadioButtonRespOutros;
    private javax.swing.JRadioButton jRadioButtonRespPai;
    private javax.swing.JRadioButton jRadioButtonRespRegraConv;
    private javax.swing.JRadioButton jRadioButtonRespeitaColega;
    private javax.swing.JRadioButton jRadioButtonRespeitaPatri;
    private javax.swing.JRadioButton jRadioButtonRespeitaProf;
    private javax.swing.JRadioButton jRadioButtonSexoF;
    private javax.swing.JRadioButton jRadioButtonSexoM;
    private javax.swing.JRadioButton jRadioButtonTurnoInt;
    private javax.swing.JRadioButton jRadioButtonTurnoIntermed;
    private javax.swing.JRadioButton jRadioButtonTurnoManha;
    private javax.swing.JRadioButton jRadioButtonTurnoNoite;
    private javax.swing.JRadioButton jRadioButtonTurnoTarde;
    private javax.swing.JRadioButton jRadioButtonVivAgreFisBrig;
    private javax.swing.JRadioButton jRadioButtonVivGravPrec;
    private javax.swing.JRadioButton jRadioButtonVivNecEsp;
    private javax.swing.JRadioButton jRadioButtonVivRacGenBull;
    private javax.swing.JRadioButton jRadioButtonVivRoubFurt;
    private javax.swing.JRadioButton jRadioButtonVivUsoBebCig;
    private javax.swing.JRadioButton jRadioButtonVivUsoTrafDrogas;
    private javax.swing.JRadioButton jRadioButtonVulneravel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFielTurma;
    private javax.swing.JTextField jTextFieldDistrito;
    private javax.swing.JTextField jTextFieldInep;
    private javax.swing.JTextField jTextFieldNomeAluno;
    private javax.swing.JTextField jTextFieldTotal;
    // End of variables declaration//GEN-END:variables
}
