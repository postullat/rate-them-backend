package co.ratethem.repository;

import co.ratethem.entity.EmailSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSubscriptionRepository extends JpaRepository<EmailSubscription, Long> {


    boolean existsByEmail(String email);
}
