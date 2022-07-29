package kg.groupc.project.service.hotel;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import kg.groupc.project.dto.review.ReviewFormDto;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.repository.account.AccountRepository;
import kg.groupc.project.repository.hotel.HotelRepository;
import kg.groupc.project.repository.hotel.HotelScoreRepository;
import kg.groupc.project.service.BaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelScoreService<T, ID extends Serializable> extends BaseService<HotelScore, Long> {
	private final HotelScoreRepository<HotelScore, Long> hotelScoreRepository;
	private final HotelRepository<Hotel, Long> hotelRepository;
	private final AccountRepository<Account, Long> accountRepository;
	
	public HotelScore saveHotelScore(ReviewFormDto reviewFormDto, Long seq, String userId) {
		Hotel hotel = hotelRepository.findById(seq).get();
		HotelScore hotelScore = new HotelScore();
		hotelScore.setHotel(hotel);
		hotelScore.setWriter(accountRepository.findByUserId(userId));
		hotelScore.setScore(reviewFormDto.getScore());
		hotelScore.setDescription(reviewFormDto.getReviewDesc());
		hotelScore.setDay(Date.valueOf(LocalDate.now()));
		
		return hotelScoreRepository.save(hotelScore);
	}
	
	public void deleteHotelScore(Long seq) {
		hotelScoreRepository.deleteById(seq);
	}
	
}
