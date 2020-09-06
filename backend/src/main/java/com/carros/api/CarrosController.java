package com.carros.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	@Autowired
	private CarroService service;
	
	@GetMapping()
	public ResponseEntity<Iterable<Carro>> get() {
		return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carro> get(@PathVariable("id") Long id) {
		Optional<Carro> carro = service.getCarroById(id);
				
		return carro.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("tipo/{tipo}")
	public ResponseEntity<List<Carro>> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<Carro> carros = service.getCarrosByTipo(tipo);
		
		return carros.isEmpty() ?
					ResponseEntity.noContent().build() :
					ResponseEntity.ok(carros);
		
	}
	
	@PostMapping
	public ResponseEntity<String> post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);
		
		
		return new ResponseEntity<String>("Carro salvo com sucesso: " + c.getId(), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> put(@PathVariable("id") Long id, @RequestBody Carro carro) {		
		Carro c = service.update(carro, id);
		
		return ResponseEntity.ok("Carro atualizado com sucesso: " + c.getId());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
