package kg.groupc.project.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import kg.groupc.project.entity.restaurant.RestaurantScore;

public interface RestaurantScoreRepository extends JpaRepository<RestaurantScore, Long>{

}
