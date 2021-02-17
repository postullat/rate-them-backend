package co.ratethem.repository;

import co.ratethem.entity.Company;
import co.ratethem.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select rw from Review rw ORDER BY rw.created DESC")
    Page<Review> findLastAdded(Pageable pageable);

    Page<Review> findReviewsByCompanyIn(List<Company> companies, Pageable pageable);

}
