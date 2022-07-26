package kg.groupc.project.repository.restaurant;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.repository.BaseRepository;

@Repository
public interface RestaurantRepository<T, ID extends Serializable> extends BaseRepository<Restaurant, Long>{
	
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
	
	Restaurant findBySeq(Long seq);
}
