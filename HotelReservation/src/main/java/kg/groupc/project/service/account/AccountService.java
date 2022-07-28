package kg.groupc.project.service.account;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import kg.groupc.project.dto.account.BookingDto;
import kg.groupc.project.dto.account.HotelScoreDto;
import kg.groupc.project.dto.account.InfoChangeFormDto;
import kg.groupc.project.dto.account.PwdChangeFormDto;
import kg.groupc.project.dto.account.RestaurantScoreDto;
import kg.groupc.project.dto.account.StarsDto;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Booking;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.entity.restaurant.RestaurantScore;
import kg.groupc.project.entity.restaurant.Stars;
import kg.groupc.project.repository.account.AccountRepository;
import kg.groupc.project.service.BaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService<T, ID extends Serializable> extends BaseService<Account, Long> implements UserDetailsService{
	
	private final AccountRepository<Account, Long> accountRepository;
	private final PasswordEncoder passwordEncoder;
	
	private Map<Long, String> getScoreMap(){
		Map<Long, String> scoreMap = new HashMap<>();
		scoreMap.put(0L, "☆☆☆☆☆");
		scoreMap.put(1L, "★☆☆☆☆");
		scoreMap.put(2L, "★★☆☆☆");
		scoreMap.put(3L, "★★★☆☆");
		scoreMap.put(4L, "★★★★☆");
		scoreMap.put(5L, "★★★★★");
		
		return scoreMap;
	}
	
	public Account getAccountById(String userId) {
		return accountRepository.findByUserId(userId);
	}
	
	public List<Account> getAllAccounts(){
		return accountRepository.findAll();
	}
	
	public Account saveAccount(Account account) {
		if(!idDuplicateCheck(account.getUserId())) {
			return accountRepository.save(account);
		}else {
			return null;
		}
	}
	
	public Account changeAccountInfo(InfoChangeFormDto infoChangeFormDto) {
		Account account = (Account) accountRepository.findByUserId(infoChangeFormDto.getUserId());
		account.setName(infoChangeFormDto.getUsername());
		account.setEmail(infoChangeFormDto.getEmail());
		account.setPhone(infoChangeFormDto.getPhone());
		account.setAddress(infoChangeFormDto.getAddress() + " " +infoChangeFormDto.getAddressDetail());
		return accountRepository.save(account);
	}
	
	public Account changeAccountPasswordChange(String userId, PwdChangeFormDto pwdChangeFormDto) {
		Account account = accountRepository.findByUserId(userId);
		account.setPassword(passwordEncoder.encode(pwdChangeFormDto.getPassword()));
		return accountRepository.save(account);
	}
	
	public boolean resignAccount(String userId) {
		Account account = accountRepository.findByUserId(userId);
		account.setStatus(0L);
		accountRepository.save(account);
		if(account.getStatus() == 0L) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public List<ArrayList<BookingDto>> getBookingList(String userId){
		Date today = Date.valueOf(LocalDate.now());
		List<Booking> bookingList = accountRepository.findByUserId(userId).getBookings();
		List<ArrayList<BookingDto>> bookingDtoList = new ArrayList<ArrayList<BookingDto>>();
		// list init
		bookingDtoList.add(new ArrayList<BookingDto>());
		bookingDtoList.add(new ArrayList<BookingDto>());
		for(Booking booking : bookingList) {
			BookingDto bookingDto = new BookingDto();
			Room room = booking.getRoom();
			String roomName = room.getName();
			Hotel hotel = room.getHotel();
			
			bookingDto.setSeq(booking.getSeq());
			bookingDto.setHotelSeq(hotel.getSeq());
			bookingDto.setHotel(hotel.getName());
			bookingDto.setRoom(roomName);
			bookingDto.setReserver(booking.getReserver().getUserId());
			bookingDto.setReserveDate(booking.getReserveDate());
			bookingDto.setReserveEndDate(booking.getReserveEndDate());
			bookingDto.setStatus(booking.getStatus());
			bookingDto.setPrice(booking.getPrice());
			bookingDto.setPeople(booking.getPeople());
			// 예약 내역
			if(booking.getReserveDate().before(today)) {
				bookingDtoList.get(1).add(bookingDto);
			}
			// 이용 내역
			else { 
				bookingDtoList.get(0).add(bookingDto);
			}
		}
		return bookingDtoList;
	}
	
	@Transactional
	public List<StarsDto> getStarsList(String userId){
		List<Stars> starsList = accountRepository.findByUserId(userId).getStars();
		List<StarsDto> starsDtoList = new ArrayList<>();
		for(Stars star : starsList) {
			StarsDto starsDto = new StarsDto();
			Restaurant restaurant = star.getRestaurant();
			
			starsDto.setSeq(star.getSeq());
			starsDto.setRestaurantSeq(restaurant.getSeq());
			starsDto.setRestaurantName(restaurant.getName());
			starsDto.setRestaurantPhone(restaurant.getPhone());
			starsDtoList.add(starsDto);
		}
		return starsDtoList;
	}
	
	@Transactional
	public List<HotelScoreDto> getHotelScoreList(String userId){
		Map<Long, String> scoreMap = getScoreMap();
		List<HotelScore> hotelScoreList = accountRepository.findByUserId(userId).getHotelScores();
		List<HotelScoreDto> hotelScoreDtoList = new ArrayList<>();
		for(HotelScore hotelScore : hotelScoreList) {
			HotelScoreDto hotelScoreDto = new HotelScoreDto();
			Hotel hotel = hotelScore.getHotel();
			
			hotelScoreDto.setSeq(hotelScore.getSeq());
			hotelScoreDto.setHotelSeq(hotel.getSeq());
			hotelScoreDto.setHotelName(hotel.getName());
			hotelScoreDto.setScore(hotelScore.getScore());
			hotelScoreDto.setScoreString(scoreMap.get(hotelScore.getScore()));
			hotelScoreDto.setDesc(hotelScore.getDescription() == null ? null : hotelScore.getDescription().replace("\n", "<br>"));
			hotelScoreDto.setWriter(hotelScore.getWriter().getName());
			hotelScoreDto.setDay(hotelScore.getDay());
			hotelScoreDtoList.add(hotelScoreDto);
		}
		return hotelScoreDtoList;
	}
	
	@Transactional
	public List<RestaurantScoreDto> getRestaurantScoreList(String userId){
		Map<Long, String> scoreMap = getScoreMap();
		List<RestaurantScore> restaurantScoreList = accountRepository.findByUserId(userId).getRestaurantScores();
		List<RestaurantScoreDto> restaurantScoreDtoList = new ArrayList<>();
		for(RestaurantScore restaurantScore : restaurantScoreList) {
			RestaurantScoreDto restaurantScoreDto = new RestaurantScoreDto();
			Restaurant restaurant = restaurantScore.getRestaurant();
			
			restaurantScoreDto.setSeq(restaurantScore.getSeq());
			restaurantScoreDto.setRestaurantSeq(restaurant.getSeq());
			restaurantScoreDto.setRestaurantName(restaurant.getName());
			restaurantScoreDto.setScore(restaurantScore.getScore());
			restaurantScoreDto.setScoreString(scoreMap.get(restaurantScore.getScore()));
			restaurantScoreDto.setDesc(restaurantScore.getDescription() == null ? null : restaurantScore.getDescription().replace("\n", "<br>"));
			restaurantScoreDto.setWriter(restaurantScore.getWriter().getName());
			restaurantScoreDto.setDay(restaurantScore.getDay());
			restaurantScoreDtoList.add(restaurantScoreDto);
		}
		return restaurantScoreDtoList;
	}
	
	public boolean idDuplicateCheck(String userId) {
		Account account = accountRepository.findByUserId(userId);
		if(account == null) 
			return false;
		else 
			return true;
	}

	public Map<String, String> validateHandling(Errors errors){
		Map<String, String> validatorResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		return validatorResult;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Account account = accountRepository.findByUserId(userId);
		
		if(account == null) {
			throw new UsernameNotFoundException(userId);
		}else if(account.getStatus()==0) {
			// 탈퇴한 계정이라면
			throw new UsernameNotFoundException(userId);
		}
		return User.builder()
				.username(account.getUserId())
				.password(account.getPassword())
				.roles(account.getRole().toString())
				.build();
	}
}
