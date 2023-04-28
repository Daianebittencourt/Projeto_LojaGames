package com.games.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


	@Entity //indica que essa classe será usada para gerar uma tabela no banco de dados

	@Table(name = "tb_produto")//A Anotação @Table indica o nome da Tabela no Banco de dados. 
	//Caso esta anotação não seja declarada, o Banco de dados criará a tabela com o mesmo nome da Classe Model

	public class Produto {

		@Id // indica que o atributo id será a chave primária (Primary Key - PK)
		@GeneratedValue // indica que a chave primária será gerada pelo bando de dados.
		private Long id;
		
		@NotBlank (message = "ATENÇÃO! \nO campo é de preenchimento obrigatório")// não permite que o atributo seja nulo ou com espaços em branco e da para configurar a 
		//mensagem para o usuário
		@Size (min=10, max=100, message= "ATENÇÃO! \nO campo é de preenchimento obrigatório") //define o valor min e máximo de caracteres desse atributo
		private String titulo;
		
		@NotBlank (message = "ATENÇÃO! \nO campo é de preenchimento obrigatório")
		@Size (min=10, max=1000, message= "ATENÇÃO! \nO campo é de preenchimento obrigatório")
		private String texto;
		
		@UpdateTimestamp //o Spring irá obter a data e horário do sistema operacional e inserir no atributo data toda vez que um obejeto for criado
		private LocalDateTime data;

		 @ManyToOne
			@JsonIgnoreProperties("produto")
			private Categoria categoria;
		 @ManyToOne
			@JsonIgnoreProperties("produto")
			private Usuario usuario;
		 
		 
		public Long getId() {
			return id;
			
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

		public LocalDateTime getData() {
			return data;
		}

		public void setData(LocalDateTime data) {
			this.data = data;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		
		
		
		
		
	}
