package com.dbp.backendtourplus.person.infrastructure;

import com.dbp.backendtourplus.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
