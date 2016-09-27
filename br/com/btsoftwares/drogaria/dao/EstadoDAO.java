package br.com.btsoftwares.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.btsoftwares.drogaria.domain.Estado;
import br.com.btsoftwares.drogaria.util.HibernateUtil;

public class EstadoDAO extends GenericDAO<Estado> {
	
	@SuppressWarnings("unchecked")
	public List<Estado> buscarEstadoPorCidade(Estado estado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.add(Restrictions.eq("estado_codigo",estado));
			consulta.addOrder(Order.asc("nome"));
			List<Estado> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
}
