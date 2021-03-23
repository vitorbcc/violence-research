/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prefeitura;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory ;
    
    static{
        try{
            Configuration cfg = new Configuration().configure("ProjetoPesquisaPU");
            StandardServiceRegistryBuilder serviceBuilder = new StandardServiceRegistryBuilder();
            serviceBuilder.applySettings(cfg.getProperties());
            StandardServiceRegistry serviceRegistry = serviceBuilder.build();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            System.out.println("block static hibernate iniciado: ");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
}
