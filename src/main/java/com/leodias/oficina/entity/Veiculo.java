package com.leodias.oficina.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Veiculo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String placa;
	private String nome;
	private String modelo;
	private String marca;
	private String anoFabricacao;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	public Veiculo() {
	}

	public Veiculo(Integer id, String placa, String nome, String modelo, String marca, String anoFabricacao, Cliente cliente) {
		this.id = id;
		this.placa = placa;
		this.nome = nome;
		this.modelo = modelo;
		this.marca = marca;
		this.anoFabricacao = anoFabricacao;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDataFabricacao() {
		return anoFabricacao;
	}

	public void setDataFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, placa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		return Objects.equals(id, other.id) && Objects.equals(placa, other.placa);
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", placa=" + placa + ", nome=" + nome + ", modelo=" + modelo + ", marca=" + marca
				+ ", anoFabricacao=" + anoFabricacao + "]";
	}

}
