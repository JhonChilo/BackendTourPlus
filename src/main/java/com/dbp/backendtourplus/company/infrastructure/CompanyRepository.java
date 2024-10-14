package com.dbp.backendtourplus.company.infrastructure;

import com.dbp.backendtourplus.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
