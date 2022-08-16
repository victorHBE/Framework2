package br.edu.ifms.Detran.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.Detran.repository.CarroRepository;
import br.edu.ifms.Detran.repository.InfracaoRepository;
import br.edu.ifms.Detran.repository.MultaRepository;

@Service
public class DBService {

	@Autowired
	private CarroRepository carro;
	
	@Autowired
	private InfracaoRepository infracao;
	
	@Autowired
	private MultaRepository multa;

	public void instantiateTestDatabase() throws ParseException {

		
	}
}