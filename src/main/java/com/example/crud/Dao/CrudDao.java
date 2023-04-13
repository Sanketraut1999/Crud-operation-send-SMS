package com.example.crud.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.Model.CrudModel;

public interface CrudDao extends JpaRepository<CrudModel, Long> {

	CrudModel getByUserId(Long userId);

}
