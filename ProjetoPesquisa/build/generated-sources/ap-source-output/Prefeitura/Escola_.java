package Prefeitura;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Escola.class)
public abstract class Escola_ {

	public static volatile SingularAttribute<Escola, String> distrito;
	public static volatile SingularAttribute<Escola, Integer> inep;
	public static volatile SingularAttribute<Escola, ConselhoCicloEnum> cciclo;
	public static volatile SingularAttribute<Escola, ArrayList> arrayalunos;
	public static volatile SingularAttribute<Escola, String> nome;
	public static volatile SingularAttribute<Escola, RegrasConvivenciaEnum> regraconvivencia;
	public static volatile SingularAttribute<Escola, Boolean> conselho;

}

