package kg.groupc.project.service.restaurant;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import kg.groupc.project.entity.restaurant.RestaurantScore;
import kg.groupc.project.service.BaseService;

@Service
public class RestaurantScoreService<T, ID extends Serializable> extends BaseService<RestaurantScore, Long> {

}
