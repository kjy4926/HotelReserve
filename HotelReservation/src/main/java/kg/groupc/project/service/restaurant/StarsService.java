package kg.groupc.project.service.restaurant;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.entity.restaurant.Stars;
import kg.groupc.project.repository.account.AccountRepository;
import kg.groupc.project.repository.restaurant.RestaurantRepository;
import kg.groupc.project.repository.restaurant.StarsRepository;
import kg.groupc.project.service.BaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StarsService<T, ID extends Serializable> extends BaseService<Stars, Long> {
	private final StarsRepository<Stars, Long> starsRepository;
	private final AccountRepository<Account, Long> accountRepository;
	private final RestaurantRepository<Restaurant, Long> restaurantRepository;
	
	public Stars saveStars(Long restaurant, String userId) {
		Stars stars = new Stars();
		stars.setUserId(accountRepository.findByUserId(userId));
		stars.setRestaurant(restaurantRepository.findById(restaurant).get());
		return starsRepository.save(stars);
	}
	
	public void deleteStars(Long seq) {
		starsRepository.deleteById(seq);
	}
	// 이미 찜하기한 맛집인지 확인
	public boolean starsDupCheck(Long restaurant, String userId) {
		Stars star = starsRepository.findByUserIdAndRestaurant(accountRepository.findByUserId(userId), restaurantRepository.findById(restaurant).get());
		if(star != null) {
			return true;
		}
		return false;
	}
}
