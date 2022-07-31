package kg.groupc.project.repository.restaurant;

import java.io.Serializable;
import java.util.List;

import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.entity.restaurant.RestaurantScore;
import kg.groupc.project.repository.BaseRepository;

public interface RestaurantScoreRepository<T, ID extends Serializable> extends BaseRepository<RestaurantScore, Long>{
	
	List<RestaurantScore> findAllByRestaurant(Restaurant restaurant);

}
