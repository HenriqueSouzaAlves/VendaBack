package com.noorden.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noorden.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
