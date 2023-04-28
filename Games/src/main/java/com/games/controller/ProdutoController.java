package com.games.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.games.model.Produto;
import com.games.repository.CategoriaRepository;
import com.games.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController // define que é uma classe controladora e que será composta por URL -> endereço de requisição (endpoints),
//verbos -> quais métodos HTTP serão usados e corpo da requisição @RequestBody -> objetos que contem dados que serão
// persistidos no banco de dados 
@RequestMapping("/produtos") // mapeia as requisições - define a URL padrão do Recurso. EX: (http://localhost:8080/postagens)

@CrossOrigin (origins ="*", allowedHeaders = "*")// essa anotação permite o recebimento de requisições fora do dominio 8080.
public class ProdutoController {

	@Autowired // INJEÇÃO DE DEPENDÊNCIA -> define quais Classes serão instanciadas e em quais lugares serão Injetadas 
	//quando houver necessidade.
	private ProdutoRepository produtoRepository; 
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping // mapeia todas as requisições http get que vai para um endereço especifico chamado endpoint.
	public ResponseEntity<List<Produto>> getAll(){ //getAll retorna todos os objetos que existem no banco de dados
		return ResponseEntity.ok(produtoRepository.findAll()); //Executa o Método findAll() (Método padrão da 
		//Interface JpaRepository), que retornará todos os Objetos da Classe Categoria persistidos no Banco de dados 
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity <Produto> getById (@PathVariable Long id) { // getById respoderá requisições http com uma resposta http | insere o valor do endpoint na variável id
		return produtoRepository.findById(id)
				.map(resposta ->  ResponseEntity.ok(resposta))// se o objeto for encontrado ele mapeia mapeia o objeto resposta
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());// e o Objeto Postagem não for encontrado (Nulo), será retornado o 
		//HTTP Status NOT FOUND 🡪 404 (Não Encontrado!).
	}
	
	@GetMapping ("/titulo{titulo}")
	public ResponseEntity <List <Produto>> getByTitulo (@PathVariable String titulo){
		return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
		if (produtoRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(produtoRepository.save(produto));
			
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
		if (produtoRepository.existsById(produto.getId())){
			
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(produtoRepository.save(produto));
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		produtoRepository.deleteById(id);
}
}
