package hr.tvz.luksic.hardwareapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @GetMapping(params = "code")
    public List<ReviewDTO> getReviewsByHardwareCode(@RequestParam String code){
        return reviewService.findAllByHardwareCode(code);
    }

    @GetMapping("getreviews/{text}")
    public List<ReviewDTO> getReviewsByText(@PathVariable final String text){
        return reviewService.findAllByReviewText(text);
    }
    @GetMapping("/{lowRating}/{highRating}")
    public List<ReviewDTO> findByRatingBetween(@PathVariable final Integer lowRating, @PathVariable final Integer highRating){
        return reviewService.findAllByRatingBetween(lowRating, highRating);

    }

}
