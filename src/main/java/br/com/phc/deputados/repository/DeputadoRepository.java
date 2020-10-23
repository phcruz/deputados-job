package br.com.phc.deputados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.deputados.model.Deputado;

@Repository
public interface DeputadoRepository extends JpaRepository<Deputado, Long>{

}
