package edu.iu.dcrispin.ducksservice.repository;

import edu.iu.dcrispin.ducksservice.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{
    Customer findByUsername(String username);
}
