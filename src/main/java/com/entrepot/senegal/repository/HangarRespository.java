package com.entrepot.senegal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entrepot.senegal.model.Hangar;

@Repository
public interface HangarRespository extends CrudRepository<Hangar, Long> {

}
