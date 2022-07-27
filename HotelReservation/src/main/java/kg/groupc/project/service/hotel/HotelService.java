package kg.groupc.project.service.hotel;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kg.groupc.project.dto.hotel.HotelMainFormDto;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.repository.hotel.HotelRepository;
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
	public List<HotelMainFormDto> getHotelList(String keyword, int num, Pageable pageable){
		//num = 0(검색어 조건x), 1(호텔명), 2(지역명)
		if(num == 0 && (keyword == null || keyword == "")) {
			Page<Hotel> hotell = hotelRepository.findByStatus(1L, pageable);//status 1인것만
			List<Hotel> hotelList = hotell.getContent();
			long count = hotell.getTotalElements();
			List<HotelMainFormDto> hotelMainFormDtoList = new ArrayList<HotelMainFormDto>();
			for(Hotel hotel : hotelList) {
				Double sum = 0.0;
				List<HotelScore> hotelScores = hotel.getHotelScores();
				for(HotelScore hotelScore : hotelScores) {
					sum += hotelScore.getScore();
				}
				Double avg = sum/hotelList.size();
				HotelMainFormDto hotelMainFormDto = new HotelMainFormDto();
				hotelMainFormDto.setSeq(hotel.getSeq());
				hotelMainFormDto.setName(hotel.getName());
				hotelMainFormDto.setPhone(hotel.getPhone());
				hotelMainFormDto.setAddress(hotel.getAddress());
				hotelMainFormDto.setDescription(hotel.getDescription());
				hotelMainFormDto.setImg(hotel.getImg());
				hotelMainFormDto.setAvg(avg);
				hotelMainFormDtoList.add(hotelMainFormDto);
			}
			
			return hotelMainFormDtoList;
			
		}else if(num == 1) {//호텔명
			Page<Hotel> hotell = hotelRepository.findByNameContainingAndStatusOrderByName(keyword, 1L, pageable);
			List<Hotel> hotelList = hotell.getContent();
			long count = hotell.getTotalElements();
			List<HotelMainFormDto> hotelMainFormDtoList = new ArrayList<HotelMainFormDto>();
			for(Hotel hotel : hotelList) {
				Double sum = 0.0;
				List<HotelScore> hotelScores = hotel.getHotelScores();
				for(HotelScore hotelScore : hotelScores) {
					sum += hotelScore.getScore();
				}
				Double avg = sum/hotelList.size();
				HotelMainFormDto hotelMainFormDto = new HotelMainFormDto();
				hotelMainFormDto.setSeq(hotel.getSeq());
				hotelMainFormDto.setName(hotel.getName());
				hotelMainFormDto.setPhone(hotel.getPhone());
				hotelMainFormDto.setAddress(hotel.getAddress());
				hotelMainFormDto.setDescription(hotel.getDescription());
				hotelMainFormDto.setImg(hotel.getImg());
				hotelMainFormDto.setAvg(avg);
				hotelMainFormDtoList.add(hotelMainFormDto);
			}
			return hotelMainFormDtoList;
			
		}else if(num == 2) {//지역명
			Page<Hotel> hotell = hotelRepository.findByNameContainingAndStatusOrderByName(keyword, 1L, pageable);
			List<Hotel> hotelList = hotell.getContent();
			long count = hotell.getTotalElements();
			List<HotelMainFormDto> hotelMainFormDtoList = new ArrayList<HotelMainFormDto>();
			for(Hotel hotel : hotelList) {
				Double sum = 0.0;
				List<HotelScore> hotelScores = hotel.getHotelScores();
				for(HotelScore hotelScore : hotelScores) {
					sum += hotelScore.getScore();
				}
				Double avg = sum/hotelList.size();
				HotelMainFormDto hotelMainFormDto = new HotelMainFormDto();
				hotelMainFormDto.setSeq(hotel.getSeq());
				hotelMainFormDto.setName(hotel.getName());
				hotelMainFormDto.setPhone(hotel.getPhone());
				hotelMainFormDto.setAddress(hotel.getAddress());
				hotelMainFormDto.setDescription(hotel.getDescription());
				hotelMainFormDto.setImg(hotel.getImg());
				hotelMainFormDto.setAvg(avg);
				hotelMainFormDtoList.add(hotelMainFormDto);
			}
			return hotelMainFormDtoList;
			
		}else {//에러
			return null;
		}
	}
	public HotelMainFormDto getHotelDetail(long seq){
		return null;
	}
	
	public final RoomRepository<Room, Long> roomRepository;
	
	public List<Room> getRoomList(Hotel hotel){//해당 방의 seq값으로 방 목록을 가져옵니다.
		return roomRepository.findAllByHotel(hotel);
	}
}
