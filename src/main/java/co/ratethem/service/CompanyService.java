package co.ratethem.service;

import co.ratethem.entity.Company;
import co.ratethem.payload.CompanyResponse;
import co.ratethem.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CompanyResponse> findByChars(String chars) {

        //TODO: validate chars
        List<Company> allByNameLike = companyRepository.findAllByNameContaining(chars);

        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<CompanyResponse>>() {}.getType();
        List<CompanyResponse> companiesDto = modelMapper.map(allByNameLike, targetListType);

        return companiesDto;

    }

    public List<CompanyResponse> findFirst10() {

        Page<Company> allLimit10 = companyRepository.findAllLimit10(PageRequest.of(0, 10));

        List<Company> companies = allLimit10.getContent();
        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<CompanyResponse>>() {}.getType();
        List<CompanyResponse> companiesDto = modelMapper.map(companies, targetListType);

        return companiesDto;

    }
}
