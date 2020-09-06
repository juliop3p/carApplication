# Spring Boot Annotations

- @SpringBootApplication = Para startar a aplicação
- @RestController = Para identificar como uma aplicação REST
- @RequestMapping("/") = Para identificar a rota do controller

## CRUD routes

- @GetMapping("/login") = routes.get('/login');
- @PostMapping = routes.post('');
- @PutMapping("/login) = routes.put('/login');
- @DeleteMapping = routes.delete('/login');

## Query Params
- show(@RequestParam("id") Long id) = Controller que recebe um id como param

## Path Params
```
@GetMapping("/show/{id}")
public String show(@PathVariable("id") Long id) {}
```

## Database Connection
```
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/spring?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=julio
spring.datasource.password=root

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

#SQL.
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update

# logging
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n
logging.level.org.hibernate.SQL=debug
```

- @Service = Serviço do banco de dados (CRUD).
- @Autowired = Injeção de dependências.
- @Entity = Entidade do banco de dados (Tabela);

**Gerar um id auto incremento**

```
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
```
<<<<<<< HEAD

## Métodos de retorno de dados

```
// Easiest		
		if(carro.isPresent()) {
			return ResponseEntity.ok(carro.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
		// Ternary	
		return carro.isPresent() ?
				ResponseEntity.ok(carro.get()) :
				ResponseEntity.notFound().build();
		
		// Lambda		
		return carro.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
```
=======
>>>>>>> a9f0c0a29461bfa0fc0f5da5523ce1a87fdbb4f5
