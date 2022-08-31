package br.edu.ifms.Detran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.Detran.dto.MultaDto;
import br.edu.ifms.Detran.model.Multa;
import br.edu.ifms.Detran.repository.MultaRepository;

@Service
public class MultaService {
	
	@Autowired
	private MultaRepository repo;
	
	public Multa inserir(Multa multa) {
		multa.setId(null);
		return repo.save(multa);
	}
	
	public Multa fromDto(MultaDto objDto) {
		return new Multa(objDto.getId(), objDto.getCidade(), objDto.getAno(), null, null);
	}
	
}
