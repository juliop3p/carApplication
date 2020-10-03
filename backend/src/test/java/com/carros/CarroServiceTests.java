package com.carros;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;

@SpringBootTest
class CarroServiceTests {
	
	@Autowired
	private CarroService service;

	@Test
	void testSave() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");
	
		CarroDTO c = service.insert(carro);
		assertNotNull(c);
		
		Long id = c.getId();
		assertNotNull(id);
		
		// Buscar o objeto
		c = service.getCarroById(id);
		assertNotNull(c);
		
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());

		// Deletar o objeto
		service.delete(id);
		
		// Verificar se deletou		
		try {
			assertNull(service.getCarroById(id));
			fail("O Carro não foi excluido");
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testLista() {
		List<CarroDTO> carros = service.getCarros();
		
		assertEquals(30, carros.size());
	}
	
	@Test
	public void testGet() {
		CarroDTO op = service.getCarroById(11L);
		
		assertNotNull(op);
		
		assertEquals("Ferrari FF", op.getNome());
	}
	
	@Test
	public void testListaPorTipo() {		
		assertEquals(10, service.getCarrosByTipo("esportivos").size());
		assertEquals(10, service.getCarrosByTipo("classicos").size());
		assertEquals(10, service.getCarrosByTipo("luxo").size());
		assertEquals(0, service.getCarrosByTipo("x").size());
		
	}

}
