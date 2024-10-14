package com.dbp.backendtourplus.company.domain;

import com.dbp.backendtourplus.company.infrastructure.CompanyRepository;
import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company createCompany(Long id, String name, String ruc) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setRuc(ruc);
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, String name, String ruc) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        company.setName(name);
        company.setRuc(ruc);
        return companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}