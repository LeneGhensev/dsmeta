//Esse service é reponsável por implementar operações de negócio.

package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repository;
	
//	public List<Sale> findSales() {
//		return repository.findAll();		
//	}

	public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {
	
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()); //cria uma data com os dados de hoje.
		
		//se o minDate não for informado, ele pega de 1 ano atrás.
		//se o maxDate não for informado, ele pega data atual.
		
		//LocalDate min = LocalDate.parse(minDate); //converte a data em formato de texto para o formato LocalDate
		LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate); //verifica se o maxDate está vazio e, se sim, coloca a data de 1 ano atrás
		//Senão, utiliza a data informada.
		
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate); //verifica se o maxDate está vazio e, se sim, coloca a data atual.
		//Senão, utiliza a data informada.
		
		return repository.findSales(min, max, pageable);		
	}	
	
}
