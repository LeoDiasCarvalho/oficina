package com.leodias.oficina.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leodias.oficina.entity.Telefone;
import com.leodias.oficina.entity.dto.TelefoneDTO;
import com.leodias.oficina.services.TelefoneService;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneResource {
	
	@Autowired
	private TelefoneService service;
	
	//Recuperando Telefone por id
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
			
		Optional<Telefone> obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Inserindo Telefone
		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<Void> insert(@Valid @RequestBody TelefoneDTO objDTO){
			Telefone obj = service.fromDTO(objDTO);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		//Atualizando Telefones
		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody TelefoneDTO objDTO, @PathVariable Integer id){
			Telefone obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
		//Deletando Telefone por id
		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		//Recuperando todas as Categorias
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<TelefoneDTO>> findAll() {
			
			List<Telefone> lista = service.findAll();
			List<TelefoneDTO> listaDTO = lista.stream().map(obj -> new TelefoneDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listaDTO);
		}
		
		//Recuperando todos os Telefones por páginas
		@RequestMapping(value="/page", method = RequestMethod.GET)
		public ResponseEntity<Page<TelefoneDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linhasPorPagina", defaultValue="2") Integer linhasPorPagina, 
				@RequestParam(value="ordem", defaultValue="id") String ordem, 
				@RequestParam(value="direcao", defaultValue="ASC") String direcao) {
				
			Page<Telefone> lista = service.findPage(page, linhasPorPagina, ordem, direcao);
			Page<TelefoneDTO> listaDTO = lista.map(obj -> new TelefoneDTO(obj));
			return ResponseEntity.ok().body(listaDTO);
		}

}
