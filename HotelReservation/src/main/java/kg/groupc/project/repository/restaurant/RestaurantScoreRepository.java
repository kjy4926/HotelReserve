package kg.groupc.project.repository.restaurant;

import java.io.Serializable;

import kg.groupc.project.entity.restaurant.RestaurantScore;
import kg.groupc.project.repository.BaseRepository;

public interface RestaurantScoreRepository<T, ID extends Serializable> extends BaseRepository<RestaurantScore, Long>{

}
