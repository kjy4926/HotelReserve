package kg.groupc.project.repository.hotel;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.repository.BaseRepository;

public interface HotelRepository<T, ID extends Serializable> extends BaseRepository<Hotel, Long>{

	//검색어x
	Page<Hotel> findByStatus(Long status, Pageable pageable);
	
	//검색어 : 호텔명
	Page<Hotel> findByNameContainingAndStatusOrderByName(String name, Long status, Pageable pageable);
	
	//검색어 : 지역명
	Page<Hotel> findByAddressContainingAndStatusOrderByAddress(String address, Long status, Pageable pageable);
	Page<Hotel> findByAddressContainingAndStatusOrderByName(String address, Long status, Pageable pageable);

}
