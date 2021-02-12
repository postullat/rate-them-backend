package co.ratethem.service;

import co.ratethem.entity.City;
import co.ratethem.entity.Company;
import co.ratethem.payload.CityResponse;
import co.ratethem.payload.CompanyResponse;
import co.ratethem.repository.CityRespository;
import co.ratethem.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRespository cityRespository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CityResponse> findAll() {

        List<City> cities = cityRespository.findAll();

        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<CityResponse>>() {}.getType();
        List<CityResponse> citiesDto = modelMapper.map(cities, targetListType);

        return citiesDto;

    }
}
