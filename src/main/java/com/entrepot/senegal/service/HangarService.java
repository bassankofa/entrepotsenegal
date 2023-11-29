package com.entrepot.senegal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entrepot.senegal.model.Hangar;
import com.entrepot.senegal.repository.HangarRespository;

import lombok.Data;

@Data
@Service
public class HangarService {

    @Autowired
    private HangarRespository hangarRespository;

    public Optional<Hangar> getHangarById(final Long id) {
        return hangarRespository.findById(id);
    }

    public Iterable<Hangar> getAllUser() {
        return hangarRespository.findAll();
    }

    public void deleteHangar(final Long id) {
        hangarRespository.deleteById(id);
    }

    public Hangar saveHangar(Hangar hangar) {
        Hangar saveHangar = hangarRespository.save(hangar);

        return saveHangar;

    }
}
