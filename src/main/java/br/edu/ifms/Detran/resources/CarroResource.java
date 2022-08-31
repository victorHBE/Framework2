package br.edu.ifms.Detran.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifms.Detran.dto.CarroDto;
import br.edu.ifms.Detran.model.Carro;
import br.edu.ifms.Detran.service.CarroService;

@RestController
@RequestMapping(value = "/carro")
public class CarroResource {
	
	@Autowired
	private CarroService carro;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Carro> find(@PathVariable Integer id) {		
		Carro obj = carro.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CarroDto objDto) {
		Carro obj = carro.fromDto(objDto);
		obj = carro.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CarroDto objDto, @PathVariable Integer id) {
		Carro obj = carro.fromDto(objDto);
		obj.setId(id);
		obj = carro.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	//@RequestBody Carro obj, @PathVariable(value = "id") Long noteId
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id){
		carro.delete(id);
		return ResponseEntity.noContent().build();
	}
	public ResponseEntity<Void> delete(@RequestBody Carro obj,@PathVariable Integer id){
		carro.delete(id);
		return ResponseEntity.noContent().build();
	}
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CarroDto>> findAll() {		
		List<Carro> list = carro.findAll();
		List<CarroDto> listDto = list.stream().map(obj -> new CarroDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
