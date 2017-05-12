
package com.example.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Cd;



@Repository
public interface CdsRepository  extends JpaRepository<Cd, Integer> {
	public Cd findById(Long id);
}