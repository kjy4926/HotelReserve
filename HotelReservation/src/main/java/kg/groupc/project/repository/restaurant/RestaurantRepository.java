package kg.groupc.project.repository.restaurant;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kg.groupc.project.entity.restaurant.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	//서치(상호명) : 페이징 X
	List<Restaurant> findByNameContaining(String searchKeyword);
	
	//서치(주소) : 페이징 X
	List<Restaurant> findByAddressContaining(String searchKeyword);
	
	//서치(상호명) + 페이징 추가
	Page<Restaurant> findByNameContaining(String searchKeyword, Pageable pageable);
	
	//서치(주소) + 페이징 추가
	Page<Restaurant> findByAddressContaining(String searchKeyword, Pageable pageable);
	
	//페이징
	Page<Restaurant> findAll(Pageable pageable);
}
