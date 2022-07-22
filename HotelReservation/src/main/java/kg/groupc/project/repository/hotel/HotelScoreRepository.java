package kg.groupc.project.repository.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;

public interface HotelScoreRepository extends JpaRepository<HotelScore, Long>{

	List<HotelScore> findAllByHotel(Hotel hotel);

}
