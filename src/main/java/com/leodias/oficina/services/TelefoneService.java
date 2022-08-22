package com.leodias.oficina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.leodias.oficina.entity.Telefone;
import com.leodias.oficina.entity.dto.TelefoneDTO;
import com.leodias.oficina.repositories.TelefoneRepository;
import com.leodias.oficina.services.exceptions.ObjectNotFoundException;

@Service
public class TelefoneService {
	
	@Autowired
	private TelefoneRepository repo;
	
	public Optional<Telefone> findById(Integer id) {
		Optional<Telefone> obj = repo.findById(id);
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Telefone.class.getName())));
	}
	
	public Telefone insert(Telefone obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Telefone update(Telefone obj) {
		findById(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new com.leodias.oficina.services.exceptions
			.DataIntegrityException("Não é possível excluir um Telefone que contenha Cliente");
			
		}
	}
	
	public List<Telefone> findAll() {
		List<Telefone> lista = repo.findAll();
		return lista;
	}
	
	public Page<Telefone> findPage(Integer page, Integer linhasPorPagina, String ordem, String direcao){
		
		PageRequest pr = PageRequest.of(page, linhasPorPagina, Direction.valueOf(direcao), ordem);
		return repo.findAll(pr);

	}
	
	public Telefone fromDTO(TelefoneDTO objDTO) {
		return new Telefone(objDTO.getId(), objDTO.getNumero());
	}

}
