package kg.groupc.project.service.restaurant;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import kg.groupc.project.dto.review.ReviewFormDto;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.entity.restaurant.RestaurantScore;
import kg.groupc.project.repository.account.AccountRepository;
import kg.groupc.project.repository.restaurant.RestaurantRepository;
import kg.groupc.project.repository.restaurant.RestaurantScoreRepository;
import kg.groupc.project.service.BaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantScoreService<T, ID extends Serializable> extends BaseService<RestaurantScore, Long> {
	private final RestaurantRepository<Restaurant, Long> restaurantRepository;
	private final RestaurantScoreRepository<RestaurantScore, Long> restaurantScoreRepository;
	private final AccountRepository<Account, Long> accountRepository;
	
	public RestaurantScore saveRestaurantScore(ReviewFormDto reviewFormDto, Long seq, String userId) {
		Restaurant restaurant = restaurantRepository.findById(seq).get();
		RestaurantScore restaurantScore = new RestaurantScore();
		restaurantScore.setRestaurant(restaurant);
		restaurantScore.setWriter(accountRepository.findByUserId(userId));
		restaurantScore.setScore(reviewFormDto.getScore());
		restaurantScore.setDescription(reviewFormDto.getReviewDesc());
		restaurantScore.setDay(Date.valueOf(LocalDate.now()));
		
		return restaurantScoreRepository.save(restaurantScore);
	}
}