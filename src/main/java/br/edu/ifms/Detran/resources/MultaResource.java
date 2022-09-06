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

import br.edu.ifms.Detran.dto.MultaDto;
import br.edu.ifms.Detran.model.Carro;
import br.edu.ifms.Detran.model.Multa;
import br.edu.ifms.Detran.service.MultaService;

@RestController
@RequestMapping(value = "/multa")
public class MultaResource {	
	
	@Autowired
	private MultaService multa;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Multa> find(@PathVariable Integer id) {		
		Multa obj = multa.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MultaDto objDto) {
		Multa obj = multa.fromDto(objDto);
		obj = multa.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody MultaDto objDto, @PathVariable Integer id) {
		Multa obj = multa.fromDto(objDto);
		obj.setId(id);
		obj = multa.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	//@RequestBody Infracao obj, @PathVariable(value = "id") Long noteId
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id){
		multa.delete(id);
		return ResponseEntity.noContent().build();
	}
	public ResponseEntity<Void> delete(@RequestBody Carro obj,@PathVariable Integer id){
		multa.delete(id);
		return ResponseEntity.noContent().build();
	}
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MultaDto>> findAll() {		
		List<Multa> list = multa.findAll();
		List<MultaDto> listDto = list.stream().map(obj -> new MultaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}