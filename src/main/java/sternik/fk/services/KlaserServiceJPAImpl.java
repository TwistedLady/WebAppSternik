package sternik.fk.services;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sternik.fk.entities.Cd;
import sternik.fk.repositories.springdata.CdsRepository;


@Service
@Qualifier("spring")
public class KlaserServiceJPAImpl implements KlasterService {

    @Autowired
    private CdsRepository bazaDanych;

    @Override
    public List<Cd> findAll() {
        List<Cd> l = new ArrayList<>();
        for (Cd item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Cd> findAllToSell() {
        List<Cd> l = new ArrayList<>();
        for (Cd item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public Optional<Cd> findById(Long id) {
        return Optional.ofNullable(bazaDanych.findById(id));
    }

    @Override
    public Optional<Cd> create(Cd cd) {
        return Optional.of(bazaDanych.save(cd));
    }

    @Override
    public Optional<Cd> edit(Cd cd) {
        return Optional.of(bazaDanych.save(cd));
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        bazaDanych.delete(id.intValue());
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<Cd> findLatest3() {
        return Collections.emptyList();
    }

}
