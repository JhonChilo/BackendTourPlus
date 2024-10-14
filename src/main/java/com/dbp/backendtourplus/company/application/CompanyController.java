package com.dbp.backendtourplus.company.application;

import com.dbp.backendtourplus.company.domain.Company;
import com.dbp.backendtourplus.company.domain.CompanyService;
import com.dbp.backendtourplus.company.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompanyById(id);
        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Company createCompany(@RequestBody CompanyDto companyDTO) {
        return companyService.createCompany(companyDTO.getId(), companyDTO.getName(), companyDTO.getRuc());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyService.getCompanyById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setName(companyDto.getName());
            company.setRuc(companyDto.getRuc());
            return ResponseEntity.ok(companyService.updateCompany(id, companyDto.getName(), companyDto.getRuc()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        if (companyService.getCompanyById(id).isPresent()) {
            companyService.deleteCompany(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}