package com.dbp.backendtourplus.company.application;

import com.dbp.backendtourplus.company.domain.Company;
import com.dbp.backendtourplus.company.domain.CompanyService;
import com.dbp.backendtourplus.company.dto.CompanyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CompanyControllerTest {

    private CompanyController companyController;
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        companyService = Mockito.mock(CompanyService.class);
        companyController = new CompanyController(companyService);
    }

    @Test
    void testGetAllCompanies() {
        Company company1 = new Company();
        Company company2 = new Company();
        when(companyService.getAllCompanies()).thenReturn(Arrays.asList(company1, company2));

        List<Company> result = companyController.getAllCompanies();

        assertEquals(2, result.size());
        verify(companyService, times(1)).getAllCompanies();
    }

    @Test
    void testGetCompanyById_Found() {
        Long companyId = 1L;
        Company company = new Company();
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.of(company));

        ResponseEntity<Company> response = companyController.getCompanyById(companyId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(company, response.getBody());
    }

    @Test
    void testGetCompanyById_NotFound() {
        Long companyId = 1L;
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.empty());

        ResponseEntity<Company> response = companyController.getCompanyById(companyId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateCompany() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(1L);
        companyDto.setName("Test Company");
        companyDto.setRuc("123456789");

        Company company = new Company();
        when(companyService.createCompany(companyDto.getId(), companyDto.getName(), companyDto.getRuc())).thenReturn(company);

        Company result = companyController.createCompany(companyDto);

        assertEquals(company, result);
        verify(companyService, times(1)).createCompany(companyDto.getId(), companyDto.getName(), companyDto.getRuc());
    }

    @Test
    void testUpdateCompany_Found() {
        Long companyId = 1L;
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Updated Company");
        companyDto.setRuc("987654321");

        Company existingCompany = new Company();
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.of(existingCompany));
        when(companyService.updateCompany(companyId, companyDto.getName(), companyDto.getRuc())).thenReturn(existingCompany);

        ResponseEntity<Company> response = companyController.updateCompany(companyId, companyDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingCompany, response.getBody());
    }

    @Test
    void testUpdateCompany_NotFound() {
        Long companyId = 1L;
        CompanyDto companyDto = new CompanyDto();
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.empty());

        ResponseEntity<Company> response = companyController.updateCompany(companyId, companyDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteCompany_Found() {
        Long companyId = 1L;
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.of(new Company()));

        ResponseEntity<Void> response = companyController.deleteCompany(companyId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(companyService, times(1)).deleteCompany(companyId);
    }

    @Test
    void testDeleteCompany_NotFound() {
        Long companyId = 1L;
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = companyController.deleteCompany(companyId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
