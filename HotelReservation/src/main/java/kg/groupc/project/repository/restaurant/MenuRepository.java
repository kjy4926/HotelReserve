package kg.groupc.project.repository.restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kg.groupc.project.entity.restaurant.Menu;
import kg.groupc.project.repository.BaseRepository;

@Repository
public interface MenuRepository<T, ID extends Serializable> extends BaseRepository<Menu, Long> {
	
	ArrayList<Menu> findAll();
	
	@Query(value = "SELECT * FROM MENU WHERE restaurant = :restaurant", nativeQuery = true)
	List<Menu> findByRestaurant(Long restaurant);

}
