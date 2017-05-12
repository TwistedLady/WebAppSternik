package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entities.Cd;
import com.example.repositories.CdAlreadyExistExceprion;
import com.example.repositories.CdRepository;
import com.example.repositories.NoSuchCdException;


@Service
@Qualifier("tablica")
public class KlasterServiceImpl implements KlasterService{

	@Autowired
    @Qualifier("tablica")
	private CdRepository bazaDanych;
	@Override
	public List<Cd> findAll() {
		// TODO Auto-generated method stub
		return bazaDanych.findAll();
	}

	@Override
	public List<Cd> findAllToSell() {
		// TODO Auto-generated method stub
		return bazaDanych.findAll();
	}

	@Override
	public Optional<Cd> findById(Long id) {
		// TODO Auto-generated method stub
		  try {
	            return Optional.of(bazaDanych.readById(id));
	        } catch (NoSuchCdException e) {
	            return Optional.empty();
	        }
	}

	@Override
	public Optional<Cd> create(Cd cd) {
		try {
            return Optional.of(bazaDanych.create(cd));
        } catch (CdAlreadyExistExceprion e) {
            return Optional.empty();
        }
	}

	@Override
	public Optional<Cd> edit(Cd cd) {
		try {
            return Optional.of(bazaDanych.update(cd));
        } catch (NoSuchCdException e) {
            return Optional.empty();
        }
	}

	@Override
	public Optional<Boolean> deleteById(Long id) {
		try {
            bazaDanych.delete(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchCdException e) {
            return Optional.of(Boolean.FALSE);
        }
	}

	@Override
	public List<Cd> findLatest3() {
		// TODO Auto-generated method stub
		return null;
	}

}
