package br.com.btsoftwares.drogaria.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();
	
	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}
	
	private static SessionFactory criarFabricaDeSessoes(){
		try{
			Configuration configuracao = new Configuration().configure();
			
			ServiceRegistry resgistro = new StandardServiceRegistryBuilder()
					.applySettings(configuracao.getProperties())
					.build();
			
			SessionFactory fabrica = configuracao.buildSessionFactory(resgistro);
			
			return fabrica;
		} catch(Throwable ex) {
			System.err.println("Falha ao criar fabrica de sessões. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
