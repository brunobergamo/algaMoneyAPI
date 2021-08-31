package com.example.algamoney.api.resource;

import com.example.algamoney.api.event.RecursoCreatedEvent;
import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<?> listar(){
		List<Categoria> findAll = categoryRepository.findAll();
		return ResponseEntity.ok(findAll);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria caegoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoryRepository.save(caegoria);
		publisher.publishEvent(new RecursoCreatedEvent(this,response,categoriaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> getById(@PathVariable Long codigo) {
		Categoria findOne = categoryRepository.findOne(codigo);
		if(findOne == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findOne);
	}
}
