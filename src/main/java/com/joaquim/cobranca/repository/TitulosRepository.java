package com.joaquim.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joaquim.cobranca.model.Titulo;

public interface TitulosRepository extends JpaRepository<Titulo, Long> {

	@Query("select t from Titulo t where t.descricao like %?1%")
	public List<Titulo> findByDescricaoContaining(String descricao);

}
