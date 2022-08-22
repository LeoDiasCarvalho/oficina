package com.leodias.oficina.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.leodias.oficina.entity.Telefone;



public class TelefoneDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo não pode ser vazio")
	@Length(min = 5, max = 11, message = "o tamanho deve ser entre 8 e 9 caracteres")
	private String numero;
	
	public TelefoneDTO() {
	}
	
	public TelefoneDTO(Telefone obj) {
		this.id = obj.getId();
		this.numero = obj.getNumero();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNome(String numero) {
		this.numero = numero;
	}

}
