package co.ratethem.repository;

import co.ratethem.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRespository extends JpaRepository<City, Long> {


}
