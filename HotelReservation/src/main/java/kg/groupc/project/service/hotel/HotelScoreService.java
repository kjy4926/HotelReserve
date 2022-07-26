package kg.groupc.project.service.hotel;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.repository.hotel.HotelScoreRepository;
import kg.groupc.project.service.BaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelScoreService<T, ID extends Serializable> extends BaseService<HotelScore, Long> {
	private final HotelScoreRepository<HotelScore, Long> hotelScoreRepository;
	
	public HotelScore saveHotelScore(HotelScore hotelScore) {
		return hotelScoreRepository.save(hotelScore);
	}
}
