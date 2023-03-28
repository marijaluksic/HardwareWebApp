package hr.tvz.luksic.hardwareapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    //List<Review> findBy();

    List<Review> findAllByHardware_Code(String code);

    List<Review> findByTextContainingIgnoreCase(String text);

    List<Review> findAllByRatingBetween(Integer lowRating, Integer highRating);

}

