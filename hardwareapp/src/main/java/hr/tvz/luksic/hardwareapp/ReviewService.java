package hr.tvz.luksic.hardwareapp;


import java.util.List;
import java.util.Optional;

public interface ReviewService {


    List<ReviewDTO> findAll();
    List<ReviewDTO> findAllByHardwareCode(String code);
    List<ReviewDTO> findAllByReviewText(String text);
    List<ReviewDTO> findAllByRatingBetween(Integer lowRating, Integer highRating);

}

