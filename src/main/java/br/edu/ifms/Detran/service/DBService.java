package br.edu.ifms.Detran.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.Detran.model.Carro;
import br.edu.ifms.Detran.model.Infracao;
import br.edu.ifms.Detran.model.Multa;
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
		
		Carro c = new Carro(null, "Fusca", "Volks", "ADS 1227");
		Carro c1 = new Carro(null, "Uno", "Fiat", "SDA 7221");
		Carro c2 = new Carro(null, "Compass", "Jeep", "HJK 1245");
		Carro c3 = new Carro(null, "Sandero", "Reno", "MNB 1935");
		
		Infracao i = new Infracao(null, "Ultrapassagem em faixa continua", 10, 199.0f);
		Infracao i2 = new Infracao(null, "Velocidade proibida", 10, 100.0f);
		Infracao i3 = new Infracao(null, "Faixa amarela", 5, 100.0f);
		Infracao i4 = new Infracao(null, "Estacionamento proibido", 3, 70.0f);
		Infracao i5 = new Infracao(null, "Faixa de pedestre", 6, 90.0f);
		Infracao i6 = new Infracao(null, "Dirigir com celular", 8, 120.0f);
		Infracao i7 = new Infracao(null, "Sinal Vermelho", 9, 159.0f);
		Infracao i8 = new Infracao(null, "Dirigir Alcoolizado", 20, 200.0f);
		
		Multa m = new Multa(null, "Corumba", 2022, c2, i5);
		Multa m2 = new Multa(null, "Corumba", 2022, c2, i8);
		Multa m3 = new Multa(null, "Campo Grande", 2022, c3, i7);
		Multa m4 = new Multa(null, "Miranda", 2022, c1, i6);
		Multa m5 = new Multa(null, "Coxim", 2022, c3, i3);
		Multa m6 = new Multa(null, "Ladario", 2022, c1, i2);
		Multa m7 = new Multa(null, "Tres lagoas", 2022, c, i);
		Multa m8 = new Multa(null, "Dourados", 2022, c, i4);
		
		c2.getMultas().addAll(Arrays.asList(m,m2));
		c3.getMultas().addAll(Arrays.asList(m3,m5));
		c1.getMultas().addAll(Arrays.asList(m4,m6));
		c.getMultas().addAll(Arrays.asList(m7,m8));
		
		i5.getMultas().addAll(Arrays.asList(m));
		i8.getMultas().addAll(Arrays.asList(m2));
		i7.getMultas().addAll(Arrays.asList(m3));
		i6.getMultas().addAll(Arrays.asList(m4));
		i3.getMultas().addAll(Arrays.asList(m5));
		i2.getMultas().addAll(Arrays.asList(m6));
		i.getMultas().addAll(Arrays.asList(m7));
		i4.getMultas().addAll(Arrays.asList(m8));
		
		carro.saveAll(Arrays.asList(c,c1,c2,c3));
		infracao.saveAll(Arrays.asList(i,i2,i3,i4,i5,i6,i7,i8));
		multa.saveAll(Arrays.asList(m,m2,m3,m4,m5,m6,m7,m8));
		
		
		
		
	}
}