package Prefeitura;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aluno.class)
public abstract class Aluno_ {

	public static volatile SingularAttribute<Aluno, LocalDate> nascimento;
	public static volatile SingularAttribute<Aluno, AnoAnteriorEnum> anoAnterior;
	public static volatile SingularAttribute<Aluno, TurnoEnum> turno;
	public static volatile SingularAttribute<Aluno, Boolean> maisEducacao;
	public static volatile SingularAttribute<Aluno, ResideEnum> resideCom;
	public static volatile SingularAttribute<Aluno, Boolean> respeitaProfissionais;
	public static volatile SingularAttribute<Aluno, Boolean> vulneravel;
	public static volatile SingularAttribute<Aluno, Escola> escola;
	public static volatile SingularAttribute<Aluno, Long> id;
	public static volatile SingularAttribute<Aluno, Boolean> viveuDescRacismoGenBully;
	public static volatile SingularAttribute<Aluno, Boolean> respeitaRegras;
	public static volatile SingularAttribute<Aluno, ResponsavelEnum> responsavel;
	public static volatile SingularAttribute<Aluno, EstudouEscolaEnum> estudouEscolaAnoAntes;
	public static volatile SingularAttribute<Aluno, Boolean> usoTraficoDrogas;
	public static volatile SingularAttribute<Aluno, Boolean> encaminhaMinisterioPublico;
	public static volatile SingularAttribute<Aluno, Boolean> usoBebida;
	public static volatile SingularAttribute<Aluno, Boolean> viveuDescPortadorNecEspeciais;
	public static volatile SingularAttribute<Aluno, String> nome;
	public static volatile SingularAttribute<Aluno, Boolean> respeitaPatrimonio;
	public static volatile SingularAttribute<Aluno, Boolean> conheceRegras;
	public static volatile SingularAttribute<Aluno, Boolean> agressoes;
	public static volatile SingularAttribute<Aluno, Boolean> roubos;
	public static volatile SingularAttribute<Aluno, Boolean> respeitaColegas;
	public static volatile SingularAttribute<Aluno, Boolean> gravidezPrecoce;
	public static volatile SingularAttribute<Aluno, ProjetoSocialEnum> projetoSocial;
	public static volatile SingularAttribute<Aluno, Boolean> ausenciaciaCominucada;
	public static volatile SingularAttribute<Aluno, SexoEnum> sexo;
	public static volatile SingularAttribute<Aluno, String> turma;
	public static volatile SingularAttribute<Aluno, ComporamentoEnum> comportamento;

}

