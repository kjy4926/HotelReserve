package kg.groupc.project.repository.hotel;

import java.io.Serializable;
import java.util.List;


import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.repository.BaseRepository;

public interface HotelScoreRepository<T, ID extends Serializable> extends BaseRepository<HotelScore, Long>{

	List<HotelScore> findAllByHotel(Hotel hotel);

}
