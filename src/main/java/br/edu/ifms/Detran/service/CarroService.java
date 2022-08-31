package br.edu.ifms.Detran.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifms.Detran.dto.CarroDto;
import br.edu.ifms.Detran.model.Carro;
import br.edu.ifms.Detran.repository.CarroRepository;
import br.edu.ifms.Detran.service.execption.DataIntegrityException;
import br.edu.ifms.Detran.service.execption.ObjectNotFoundException;



@Service
public class CarroService {
	@Autowired
	private CarroRepository repo;
	
	public Carro buscarPorId(Integer id) {
		Optional<Carro> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Carro.class.getName()));		
	}
	
	public Carro insert (Carro obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}

	public Carro update(Carro obj) {
		Carro newObj = buscarPorId(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		buscarPorId(id);
		try {
			repo.deleteById(id);	
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível remover. Verifique a integridade referencial.");
		}
	}

	public List<Carro> findAll() {
		// TODO Auto-generated method stub		
		return repo.findAll();
	}
	
	public Carro fromDto(CarroDto objDto) {
		return new Carro(objDto.getId(), objDto.getNome(), objDto.getMarca(), objDto.getPlaca());
	}
	
	private void updateData(Carro newObj, Carro obj) {
		newObj.setNome(obj.getNome());
		newObj.setMarca(obj.getMarca());
		newObj.setPlaca(obj.getPlaca());
	}
}
