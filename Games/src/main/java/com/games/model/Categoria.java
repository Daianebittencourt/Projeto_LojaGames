package com.games.model;

import java.util.List;

import com.games.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull(message = "ATENÇÃO! O campo descrição é de preenchimento obrigatório")
		private String descricao;
		
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties("categoria")
		private List<Produto> produto;


		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescricao() {
			return this.descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public List<Produto> getProduto() {
			return produto;
		}

		public void setProduto(List<Produto> produto) {
			this.produto = produto;
		}
		
		

	}



