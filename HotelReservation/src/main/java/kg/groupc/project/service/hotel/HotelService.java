package kg.groupc.project.service.hotel;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kg.groupc.project.dto.hotel.HotelDetailFormDto;
import kg.groupc.project.dto.hotel.HotelDetailRoomFormDto;
import kg.groupc.project.dto.hotel.HotelMainFormDto;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.repository.hotel.HotelRepository;
import kg.groupc.project.repository.hotel.HotelScoreRepository;
import kg.groupc.project.repository.hotel.RoomRepository;
import kg.groupc.project.service.BaseService;
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
		
	public Long getHotelCount(String keyword){
		return null;
	}
	
	@Transactional
	public List<HotelMainFormDto> getHotelList(String keyword, int type, Pageable pageable){
		Page<Hotel> hotell = null;
		//num = 0(검색어 조건x), 1(호텔명), 2(지역명)
		if(type == 0 && (keyword == null || keyword == "")) {
			hotell = hotelRepository.findByStatus(1L, pageable);//status 1인것만	
		}else if(type == 1) {//호텔명
			hotell = hotelRepository.findByNameContainingAndStatusOrderByName(keyword, 1L, pageable);	
		}else if(type == 2) {//지역명
			hotell = hotelRepository.findByNameContainingAndStatusOrderByName(keyword, 1L, pageable);
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
				avg = sum/hotelScores.size();
			}
			HotelMainFormDto hotelMainFormDto = new HotelMainFormDto();
			hotelMainFormDto.setSeq(hotel.getSeq());
			hotelMainFormDto.setName(hotel.getName());
			hotelMainFormDto.setPhone(hotel.getPhone());
			hotelMainFormDto.setAddress(hotel.getAddress());
			hotelMainFormDto.setDescription(hotel.getDescription());
			hotelMainFormDto.setImg(hotel.getImg());
			hotelMainFormDto.setAvg(avg);
			hotelMainFormDto.setDataCount(count);
			hotelMainFormDtoList.add(hotelMainFormDto);
		}
		return hotelMainFormDtoList;
	}
	
	@Autowired
	HotelScoreRepository<HotelScore, Long> hotelScoreRepository;
	
	@Transactional
	public HotelDetailFormDto getHotelDetail(long seq){
		Hotel hotel = hotelRepository.findById(seq).get(); //hotel객체 반환
		
		HotelDetailFormDto hotelDetailFormDto = new HotelDetailFormDto();
		Double sum = 0.0;
		List<HotelScore> hotelScores = hotel.getHotelScores();
		for(HotelScore hotelScore : hotelScores) {
			sum += hotelScore.getScore();
		}
		Double avg = 0.0; 
		if(hotelScores.size() > 0) {
			avg = sum/hotelScores.size();
		}

		hotelDetailFormDto.setHotel(hotel.getSeq());
		hotelDetailFormDto.setName(hotel.getName());
		hotelDetailFormDto.setPhone(hotel.getPhone());
		hotelDetailFormDto.setAddress(hotel.getAddress());
		hotelDetailFormDto.setDescription(hotel.getDescription());
		hotelDetailFormDto.setImg(hotel.getImg());
		hotelDetailFormDto.setAvg(avg);
		
		
		List<Room> roomList = hotel.getRooms();
		List<HotelDetailRoomFormDto> saveHotelRoomList = new ArrayList<HotelDetailRoomFormDto>();
		for(Room room : roomList) {
			HotelDetailRoomFormDto hotelDetailRoomFormDto = new HotelDetailRoomFormDto();
			hotelDetailRoomFormDto.setSeq(room.getSeq());
			hotelDetailRoomFormDto.setName(room.getName());
			hotelDetailRoomFormDto.setPeople(room.getPeople());
			saveHotelRoomList.add(hotelDetailRoomFormDto);
		}
		hotelDetailFormDto.setRoomList(saveHotelRoomList);
		
		return hotelDetailFormDto;
	}
	
	public final RoomRepository<Room, Long> roomRepository;
	
	public List<Room> getRoomList(Hotel hotel){//해당 방의 seq값으로 방 목록을 가져옵니다.
		return roomRepository.findAllByHotel(hotel);
	}
}
