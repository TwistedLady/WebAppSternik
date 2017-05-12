package com.example.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.example.entities.Cd;
import com.example.entities.Status;



@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements CdRepository{
	private Cd[] baza;
	
	public ProstaBazaDanych() {
			baza= new Cd[15];
			Cd cd= new Cd(1l, "Inferno", "Motorhead", new BigDecimal(12), Status.NEW, "-",new Date());
			baza[0]=cd;
			Cd cd2= new Cd(1l, "Iron fist", "Motorhead", new BigDecimal(32), Status.NEW, "-",new Date());
			baza[1]=cd2;
	}

	@Override
	public Cd create(Cd cd) throws CdAlreadyExistExceprion {
		if (cd.getId() != null && baza[cd.getId().intValue()] != null) {
            if (cd.getId().equals(baza[cd.getId().intValue()].getId())) {
                throw new CdAlreadyExistExceprion("Już jest moneta o takim numerze.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = cd;
                cd.setId((long) i);
                return cd;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
	}

	@Override
	public Cd readById(Long id) throws NoSuchCdException {
		int idcd = id.intValue();
        if (!cheackId(idcd) || isFree(idcd)) {
            throw new NoSuchCdException();
        }
        return baza[idcd];
	}

	@Override
	public Cd update(Cd cd) throws NoSuchCdException {
		int idcd = cd.getId().intValue();
        if (!cheackId(idcd)) {
            throw new NoSuchCdException("Nie poprawny numer katologowy");
        }

        Cd cdUpdate = baza[cd.getId().intValue()];
        if (cdUpdate == null) {
            throw new NoSuchCdException("Brak takiej monety.");
        } else {
            baza[cd.getId().intValue()] = cd;
        }
        return cd;
	}

	@Override
	public void delete(Long id) throws NoSuchCdException {
		int cdId = id.intValue();
        if (!cheackId(cdId)) {
            throw new NoSuchCdException("Nie poprawny numer katologowy");
        }
        // tu troche zle ;)
        baza[cdId] = null;
		
	}

	@Override
	public List<Cd> findAll() {
		 List<Cd> tmp = new ArrayList<>();
	        for (int i = 0; i < baza.length; i++) {
	            if (baza[i] != null)
	                tmp.add(baza[i]);
	        }
	        return tmp;
	}
	private boolean cheackId(int id) {
        if (id < 0 || id >= baza.length) {
            System.out.println("Zły numer katalogowy");
            return false;
        }
        return true;
    }
	  private boolean isFree(int id) {
	        if(baza[id]!= null)
	            return false;
	        return true;
	    }
}
