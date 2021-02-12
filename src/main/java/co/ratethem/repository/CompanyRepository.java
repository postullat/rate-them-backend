package co.ratethem.repository;

import co.ratethem.entity.Company;
import co.ratethem.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllByNameContaining(String chars);

    @Query("select cp from Company cp")
    Page<Company> findAllLimit10(Pageable pageable);
}
