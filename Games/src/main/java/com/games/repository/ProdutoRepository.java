package com.games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.games.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long > { // foi adicionado a HERANÇA com a
	// interface JpaRepository que recebe dois parametros -> Classe categoria e a chave primária Long (id)
	
	public List <Produto> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo); // criando Query Method 
	// vai fazer uma consulta personalizada | método assinado com uma Collection List 
}