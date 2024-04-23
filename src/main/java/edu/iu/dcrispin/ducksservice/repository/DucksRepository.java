package edu.iu.dcrispin.ducksservice.repository;

import edu.iu.dcrispin.ducksservice.model.DuckData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DucksRepository extends CrudRepository<DuckData, String>{
    DuckData findByName(String name);
}