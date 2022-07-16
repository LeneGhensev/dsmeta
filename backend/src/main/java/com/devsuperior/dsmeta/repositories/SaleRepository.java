//Essa interface é um componente responsável por acessar o banco de dados.

package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
	
	@Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :min AND :max ORDER BY obj.amount DESC")//busca as 20 melhores vendas no intervalo de vendas selecionado.
	Page<Sale> findSales(LocalDate min, LocalDate max, Pageable pageable);
}
