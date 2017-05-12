package com.example.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entities.Cd;
import com.example.entities.Status;

@Service
@Qualifier("lista")
public class CdRepositoryJ8Impl implements CdRepository {
	private List<Cd> cds = new ArrayList<Cd>(){{ 
		add(Cd.produceCd(1l, "Reign in blood", "Slayer", new BigDecimal(25),Status.NEW,"blabla",new Date()));
		add(Cd.produceCd(2L, "Kill em all", "Metallica", new BigDecimal(12), Status.USED,"blabla",new Date()));
		add(Cd.produceCd(3l, "Ride the Ligthning", "Metallica",new BigDecimal(13), Status.FORSAE,"blabla",new Date()));
				
	}};
	@Override
	public Cd create(Cd cd) throws CdAlreadyExistExceprion {
		if (!cds.isEmpty()) {
			cd.setId(
                    this.cds.stream().mapToLong(p -> p.getId()).max().getAsLong() + 1);
        } else {
        	cd.setId(1L);
        }
        this.cds.add(cd);
        return cd;
	}

	@Override
	public Cd readById(Long id) throws NoSuchCdException {
		// TODO Auto-generated method stub
		return this.cds.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst()
				.orElseThrow(NoSuchCdException::new);
				
			//.findFirst()                .orElseThrow(new NoSuchCdException("no cds")));
	}

	@Override
	public Cd update(Cd cd) throws NoSuchCdException {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.cds.size(); i++) {
            if (Objects.equals(this.cds.get(i).getId(), cd.getId())) {
                this.cds.set(i, cd);
                return cd;
            }
        }
		throw new NoSuchCdException("Brak Cd o tym id"+cd.getId());
	}

	@Override
	public void delete(Long id) throws NoSuchCdException {
		// TODO Auto-generated method stub
		for(Cd storedCd : this.cds)
		{
			if(Objects.equals(id, storedCd.getId()))
			{
				this.cds.remove(id);
			}
		}
		throw new NoSuchCdException("Brak Cd o tym id"+id);
	}

	@Override
	public List<Cd> findAll() {
		// TODO Auto-generated method stub
		return this.cds;
	}

}
