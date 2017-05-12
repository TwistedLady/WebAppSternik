package com.example.repositories;

import java.util.List;

import com.example.entities.Cd;

public interface CdRepository {
	Cd create(Cd cd) throws CdAlreadyExistExceprion;
	Cd readById(Long id) throws NoSuchCdException;
	Cd update(Cd cd) throws NoSuchCdException;
	void delete(Long id) throws NoSuchCdException;
	List<Cd> findAll();
	List<Cd> findToSell();
}
