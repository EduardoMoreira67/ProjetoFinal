package br.com.isidrocorp.projetofinal.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.isidrocorp.projetofinal.dto.VolumeAlarmes;
import br.com.isidrocorp.projetofinal.model.Evento;

public interface EventoDAO extends CrudRepository<Evento, Integer> {
	public ArrayList<Evento> findByOrderByData();
	public ArrayList<Evento> findByOrderByNumSeq();
	
	public ArrayList<Evento> findByDataBetweenOrderByData(Date inicio, Date fim);
	
	@Query("SELECT new br.com.isidrocorp.projetofinal.dto.VolumeAlarmes(ev.alarme.id, count(ev.alarme.id)) "
			+ " FROM Evento ev GROUP BY ev.alarme.id")
	public ArrayList<VolumeAlarmes> getAllAlarmes();
	
	@Query("SELECT new br.com.isidrocorp.projetofinal.dto.VolumeAlarmes(ev.alarme.id, ev.alarme.nome, count(ev.alarme.id))"
			+ "FROM Evento ev GROUP BY ev.alarme.id")
	public ArrayList<VolumeAlarmes> getAllWithName();
	
	@Query("SELECT new br.com.isidrocorp.projetofinal.dto.VolumeAlarmes(ev.alarme.id, ev.alarme.nome, count(ev.alarme.id))" 
			    + "FROM Evento ev "
			    + "WHERE ev.data >= :inicio AND ev.data <= :fim "
			    + "GROUP BY ev.alarme.id")
	public ArrayList<VolumeAlarmes> getAllWithNameByPeriod(@Param("inicio") Date inicio, @Param("fim") Date fim);
}
