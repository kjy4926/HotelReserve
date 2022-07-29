package kg.groupc.project.service.hotel;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kg.groupc.project.dto.hotel.HotelDetailFormDto;
import kg.groupc.project.dto.hotel.HotelDetailRoomFormDto;
import kg.groupc.project.dto.hotel.HotelMainFormDto;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.repository.hotel.HotelRepository;
import kg.groupc.project.repository.hotel.HotelScoreRepository;
import kg.groupc.project.repository.hotel.RoomRepository;
import kg.groupc.project.service.BaseService;
import kg.groupc.project.service.account.AccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class HotelService<T, ID extends Serializable> extends BaseService<Hotel, Long> {
	
	private final HotelRepository<Hotel, Long> hotelRepository;
	
	public Hotel getHotelBySeq(Long seq) {
		return hotelRepository.findById(seq).get();
	}
	
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}
	
	private Map<Integer, String> getScoreMap(){
		Map<Integer, String> scoreMap = new HashMap<>();
		scoreMap.put(0, "☆☆☆☆☆");
		scoreMap.put(1, "★☆☆☆☆");
		scoreMap.put(2, "★★☆☆☆");
		scoreMap.put(3, "★★★☆☆");
		scoreMap.put(4, "★★★★☆");
		scoreMap.put(5, "★★★★★");
		
		return scoreMap;
	}
	
	@Transactional
	public List<HotelMainFormDto> getHotelList(String keyword, int type, Pageable pageable){
		Map<Integer, String> scoreMap = getScoreMap();
		Page<Hotel> hotell = null;
		//num = 0(검색어 조건x), 1(호텔명), 2(지역명)
		if(type == 0 && (keyword == null || keyword == "")) {
			hotell = hotelRepository.findByStatus(1L, pageable);//status 1인것만	
		}else if(type == 1) {//호텔명
			hotell = hotelRepository.findByNameContainingAndStatusOrderByName(keyword, 1L, pageable);	
		}else if(type == 2) {//지역명
			hotell = hotelRepository.findByAddressContainingAndStatusOrderByAddress(keyword, 1L, pageable);
		}else {//에러	
		}
		
		List<Hotel> hotelList = hotell.getContent();
		long count = hotell.getTotalElements();
		List<HotelMainFormDto> hotelMainFormDtoList = new ArrayList<HotelMainFormDto>();
		for(Hotel hotel : hotelList) {
			Double sum = 0.0;
			List<HotelScore> hotelScores = hotel.getHotelScores();
			for(HotelScore hotelScore : hotelScores) {
				sum += hotelScore.getScore();
			}
			Double avg = 0.0;
			if(hotelScores.size()>0) {
				avg = Math.round(sum/hotelScores.size() * 100) / 100.0;
			}
			HotelMainFormDto hotelMainFormDto = new HotelMainFormDto();
			hotelMainFormDto.setSeq(hotel.getSeq());
			hotelMainFormDto.setName(hotel.getName());
			hotelMainFormDto.setPhone(hotel.getPhone());
			hotelMainFormDto.setAddress(hotel.getAddress());
			hotelMainFormDto.setDescription(hotel.getDescription());
			hotelMainFormDto.setImg(hotel.getImg());
			hotelMainFormDto.setAvg(avg);
			hotelMainFormDto.setScoreString(scoreMap.get(avg.intValue()));
			hotelMainFormDto.setDataCount(count);
			hotelMainFormDtoList.add(hotelMainFormDto);
		}
		return hotelMainFormDtoList;
	}
	
	@Autowired
	HotelScoreRepository<HotelScore, Long> hotelScoreRepository;
	
	@Transactional
	public HotelDetailFormDto getHotelDetail(long seq){
		Map<Integer, String> scoreMap = getScoreMap();
		Hotel hotel = hotelRepository.findById(seq).get(); //hotel객체 반환
		
		HotelDetailFormDto hotelDetailFormDto = new HotelDetailFormDto();
		Double sum = 0.0;
		List<HotelScore> hotelScores = hotel.getHotelScores();
		for(HotelScore hotelScore : hotelScores) {
			sum += hotelScore.getScore();
		}
		Double avg = 0.0; 
		if(hotelScores.size() > 0) {
			avg = Math.round(sum/hotelScores.size() * 100) / 100.0;
		}
		//호텔 상세 담기
		hotelDetailFormDto.setHotel(hotel.getSeq());
		hotelDetailFormDto.setName(hotel.getName());
		hotelDetailFormDto.setPhone(hotel.getPhone());
		hotelDetailFormDto.setAddress(hotel.getAddress());
		hotelDetailFormDto.setDescription(hotel.getDescription());
		hotelDetailFormDto.setImg(hotel.getImg());
		hotelDetailFormDto.setAvg(avg);
		hotelDetailFormDto.setScoreString(scoreMap.get(avg.intValue()));
		
		
		List<Room> roomList = hotel.getRooms();
		List<HotelDetailRoomFormDto> saveHotelRoomList = new ArrayList<HotelDetailRoomFormDto>();
		for(Room room : roomList) {//해당 호텔 모든 방 정보 담기
			HotelDetailRoomFormDto hotelDetailRoomFormDto = new HotelDetailRoomFormDto();
			hotelDetailRoomFormDto.setSeq(room.getSeq());
			hotelDetailRoomFormDto.setImg(room.getImg());
			hotelDetailRoomFormDto.setName(room.getName());
			hotelDetailRoomFormDto.setPeople(room.getPeople());
			hotelDetailRoomFormDto.setDesc(room.getDescription());
			saveHotelRoomList.add(hotelDetailRoomFormDto);
		}
		hotelDetailFormDto.setRoomList(saveHotelRoomList);
		
		return hotelDetailFormDto;
	}
	
	public final RoomRepository<Room, Long> roomRepository;
	
	public List<Room> getRoomList(Hotel hotel){//해당 방의 seq값으로 방 목록을 가져옵니다.
		return roomRepository.findAllByHotel(hotel);
	}
	
	@Autowired
	private AccountService<Account, Long> accountService;//샘플 스코어 생성 용도
	
//	@Transactional
//	public void addSampleScore() {//샘플 스코어 생성 용도
//		List<Hotel> hotelList = getAllHotel();
//		for(Hotel hotel : hotelList) {
//			for(int i = 0; i < 5; i++) {
//				HotelScore hotelScore = new HotelScore();
//				Account account = accountService.getAccountById("yangchi97");
//				hotelScore.setHotel(hotel);
//				hotelScore.setWriter(account);
//				hotelScore.setScore(5L);
//				hotelScore.setDescription("아주 편한 호텔이요 개추" + i);
//				hotelScore.setDay(Date.valueOf(LocalDate.now()));
//				hotelScoreRepository.save(hotelScore);
//				
//			}
//		}
//		
//	}
	
}
