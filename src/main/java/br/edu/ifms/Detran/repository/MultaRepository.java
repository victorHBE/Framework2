package br.edu.ifms.Detran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.Detran.model.Multa;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Integer> {

}
