package br.com.isidrocorp.projetofinal.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.projetofinal.dao.EventoDAO;
import br.com.isidrocorp.projetofinal.dto.PeriodoConsulta;
import br.com.isidrocorp.projetofinal.dto.VolumeAlarmes;
import br.com.isidrocorp.projetofinal.model.Evento;

@RestController
@CrossOrigin("*")
public class EventoController {
	
	@Autowired
	EventoDAO dao;
	VolumeAlarmes dto;
	
	@GetMapping("/eventos")
	public ArrayList<Evento> recuperarTodos(){
		ArrayList<Evento> lista;
		lista = (ArrayList<Evento>)dao.findByOrderByData();
		
		return lista;
	}
	@GetMapping("/eventos/alarmes/resumo")
	public ArrayList<VolumeAlarmes> recuperarResumoPorAlarme(){
		return dao.getAllWithName();
	}
	
	@PostMapping("/eventos/periodo")
	public ArrayList<Evento> recuperarPorPeriodo(@RequestBody PeriodoConsulta periodo){
		try {
			Date inicio = new SimpleDateFormat("yyyy-MM-dd").parse(periodo.getInicio());
			Date fim    = new SimpleDateFormat("yyyy-MM-dd").parse(periodo.getFim());
			ArrayList<Evento> lista = dao.findByDataBetweenOrderByData(inicio, fim);
			return lista;
		}
		catch(Exception ex) {
			return null;
		}
	} 
	@GetMapping("/eventos/alarmes/janeiro")
	public ArrayList<VolumeAlarmes> recuperarDeJaneiro() {
		try {
			Date inicio = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
			Date fim    = new SimpleDateFormat("dd/MM/yyyy").parse("31/01/2020");

			return dao.getAllWithNameByPeriod(inicio, fim);
		} catch (Exception ex) {
			return null;
		}
	}	
	 @GetMapping("/eventos/alarmes/manual")
	 public ArrayList<VolumeAlarmes> recuperarManual(){
	        ArrayList<Evento> lista;
	        ArrayList<VolumeAlarmes> ret;
	        lista = (ArrayList<Evento>)dao.findByOrderByData();
	        ret = new ArrayList<VolumeAlarmes>();
	        long count_1=0,count_2=0,count_3=0,count_4=0,count_5=0;
	        String nome_1="", nome_2="", nome_3="", nome_4="", nome_5="";
	        for (Evento e: lista) {
	            switch(e.getAlarme().getId()) {
	            case 1:
					count_1++;
					nome_1 = e.getAlarme().getNome();
					break;
				case 2:
					count_2++;
					nome_2 = e.getAlarme().getNome();
					break;
				case 3:
					count_3++;
					nome_3 = e.getAlarme().getNome();
					break;
				case 4:
					count_4++;
					nome_4 = e.getAlarme().getNome();
					break;
				case 5:
					count_5++;
					nome_5 = e.getAlarme().getNome();
					break;
				}
			}

			ret.add(new VolumeAlarmes(1, nome_1, count_1));
			ret.add(new VolumeAlarmes(2, nome_2, count_2));
			ret.add(new VolumeAlarmes(3, nome_3, count_3));
			ret.add(new VolumeAlarmes(4, nome_4, count_4));
			ret.add(new VolumeAlarmes(5, nome_5, count_5));
			
			return ret;
		}
}