package com.leodias.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leodias.oficina.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
