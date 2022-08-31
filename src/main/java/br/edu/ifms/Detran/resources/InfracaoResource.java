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

import br.edu.ifms.Detran.dto.InfracaoDto;
import br.edu.ifms.Detran.model.Carro;
import br.edu.ifms.Detran.model.Infracao;
import br.edu.ifms.Detran.service.InfracaoService;

@RestController
@RequestMapping(value = "/infracao")
public class InfracaoResource {	
	
	@Autowired
	private InfracaoService infracao;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Infracao> find(@PathVariable Integer id) {		
		Infracao obj = infracao.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody InfracaoDto objDto) {
		Infracao obj = infracao.fromDto(objDto);
		obj = infracao.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody InfracaoDto objDto, @PathVariable Integer id) {
		Infracao obj = infracao.fromDto(objDto);
		obj.setId(id);
		obj = infracao.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	//@RequestBody Infracao obj, @PathVariable(value = "id") Long noteId
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id){
		infracao.delete(id);
		return ResponseEntity.noContent().build();
	}
	public ResponseEntity<Void> delete(@RequestBody Carro obj,@PathVariable Integer id){
		infracao.delete(id);
		return ResponseEntity.noContent().build();
	}
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<InfracaoDto>> findAll() {		
		List<Infracao> list = infracao.findAll();
		List<InfracaoDto> listDto = list.stream().map(obj -> new InfracaoDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
