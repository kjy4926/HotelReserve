package kg.groupc.project.service.hotel;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kg.groupc.project.dto.hotel.HotelAddFormDto;
import kg.groupc.project.dto.hotel.HotelDetailFormDto;
import kg.groupc.project.dto.hotel.HotelDetailRoomFormDto;
import kg.groupc.project.dto.hotel.HotelDto;
import kg.groupc.project.dto.hotel.HotelMainFormDto;
import kg.groupc.project.dto.hotel.RoomDto;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.entity.hotel.QHotel;
import kg.groupc.project.entity.hotel.QHotelScore;
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
	private final HotelScoreRepository<HotelScore, Long> hotelScoreRepository;
	private final HotelRepository<Hotel, Long> hotelRepository;
	
	public Hotel saveHotel(HotelAddFormDto hotelAddFormDto) {
		Hotel hotel = new Hotel();

		hotel.setName(hotelAddFormDto.getName());
		hotel.setPhone(hotelAddFormDto.getPhone());
		hotel.setAddress(hotelAddFormDto.getAddress()+" "+hotelAddFormDto.getAddressDetail());
		hotel.setDescription(hotelAddFormDto.getDescription());
		hotel.setStatus(1L);
		hotelRepository.save(hotel);
		
		String filePath = System.getProperty("user.dir")+"\\src\\main\\webapp\\resources\\img\\hotel\\" + hotel.getSeq() + ".jpg";
		MultipartFile mfile = hotelAddFormDto.getUploadFile();
		
		try {
			mfile.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hotel.setImg(hotel.getSeq()+".jpg");
		
		return hotelRepository.save(hotel);
	}
	
	public Hotel updateHotel(Long seq, HotelAddFormDto hotelAddFormDto) {
		Hotel hotel = hotelRepository.findById(seq).get();
		
		hotel.setName(hotelAddFormDto.getName());
		hotel.setPhone(hotelAddFormDto.getPhone());
		if(hotelAddFormDto.getAddress().length() != 0) {
			hotel.setAddress(hotelAddFormDto.getAddress()+" "+hotelAddFormDto.getAddressDetail());
		}
		hotel.setDescription(hotelAddFormDto.getDescription());
		hotel.setStatus(1L);
		
		MultipartFile mfile = hotelAddFormDto.getUploadFile();
		String filePath = System.getProperty("user.dir")+"\\src\\main\\webapp\\resources\\img\\hotel\\" + hotel.getSeq() + ".jpg";
		
		if(!mfile.isEmpty()) {
			try {
				mfile.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return hotelRepository.save(hotel);
	}
	
	public boolean deleteHotel(Long seq) {
		try {
			hotelRepository.deleteById(seq);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Hotel getHotelBySeq(Long seq) {
		return hotelRepository.findById(seq).get();
	}
	
	
	public HotelDto hotelToHotelDto(Hotel hotel) {
		HotelDto hotelDto = new HotelDto();
		hotelDto.setSeq(hotel.getSeq());
		hotelDto.setName(hotel.getName());
		hotelDto.setPhone(hotel.getPhone());
		hotelDto.setAddress(hotel.getAddress());
		hotelDto.setDescription(hotel.getDescription());
		hotelDto.setImg(hotel.getImg());
		return hotelDto;
	}
	
	@Transactional
	public List<RoomDto> getHotelRoomList(Long seq){
		Hotel hotel = hotelRepository.findById(seq).get();
		List<Room> roomList = hotel.getRooms();
		List<RoomDto> roomDtoList = new ArrayList<RoomDto>();
		for(Room room : roomList) {
			RoomDto roomDto = new RoomDto();
			roomDto.setSeq(room.getSeq());
			roomDto.setHotelSeq(hotel.getSeq());
			roomDto.setName(room.getName());
			roomDto.setPeople(room.getPeople());
			roomDto.setPrice(room.getPrice());
			roomDto.setDescription(room.getDescription());
			roomDto.setImg(room.getImg());
			
			roomDtoList.add(roomDto);
		}		
		return roomDtoList;
	}
	
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}
	
	private Map<Integer, String> getScoreMap(){
		Map<Integer, String> scoreMap = new HashMap<>();
		scoreMap.put(0, "???????????????");
		scoreMap.put(1, "???????????????");
		scoreMap.put(2, "???????????????");
		scoreMap.put(3, "???????????????");
		scoreMap.put(4, "???????????????");
		scoreMap.put(5, "???????????????");
		
		return scoreMap;
	}
	
	public Page<Hotel> getSearchHotelPage(int page, String searchType, String search){
		if(searchType.equals("name")) {
			return hotelRepository.findByNameContainingAndStatusOrderByName(search, 1L, PageRequest.of(page, 20));
		}else if(searchType.equals("addr")){
			return hotelRepository.findByAddressContainingAndStatusOrderByName(search, 1L, PageRequest.of(page, 20));
		}
		return null;
	}
	
	public Page<Hotel> getHotelPage(int page) {
		return hotelRepository.findByStatus(1L, PageRequest.of(page, 20, Sort.Direction.ASC, "seq"));
	}
	
	@Transactional
	public List<HotelDto> getHotelList(Page<Hotel> hotelPage){
		List<Hotel> hotelList = hotelPage.getContent();
		List<HotelDto> hotelDtoList = new ArrayList<HotelDto>();
		for(Hotel hotel : hotelList) {
			HotelDto hotelDto = new HotelDto();
			hotelDto.setSeq(hotel.getSeq());
			hotelDto.setName(hotel.getName());
			hotelDto.setPhone(hotel.getPhone());
			hotelDto.setAddress(hotel.getAddress());
			hotelDto.setDescription(hotel.getDescription());
			hotelDto.setImg(hotel.getImg());
			
			hotelDtoList.add(hotelDto);
		}
		
		return hotelDtoList;
	}
	
	@Transactional
	public List<HotelMainFormDto> getHotelList(String keyword, int type, int sortAvg ,Pageable pageable){
		Map<Integer, String> scoreMap = getScoreMap();
		Page<Hotel> hotell = null;
		List<HotelMainFormDto> hotelMainFormDtoList = new ArrayList<HotelMainFormDto>();
		long count;
		if(sortAvg == 0) {//????????? ??????x
			//num = 0(????????? ??????x), 1(?????????), 2(?????????)
			if(type == 0 && (keyword == null || keyword == "")) {
				hotell = hotelRepository.findByStatus(1L, pageable);//status 1?????????	
			}else if(type == 1) {//?????????
				hotell = hotelRepository.findByNameContainingAndStatusOrderByName(keyword, 1L, pageable);	
			}else if(type == 2) {//?????????
				hotell = hotelRepository.findByAddressContainingAndStatusOrderByAddress(keyword, 1L, pageable);
			}
			count = hotell.getTotalElements();//?????? ????????? ???
			List<Hotel> hotelList = hotell.getContent();//?????? ??????
			for(Hotel hotel : hotelList) {
				Double sum = 0.0;
				List<HotelScore> hotelScores = hotel.getHotelScores();
				for(HotelScore hotelScore : hotelScores) {
					sum += hotelScore.getScore();
				}
				Double avg = 0.0;
				if(hotelScores.size()>0) {//????????? 2?????? ????????? ?????? ?????????
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
			
		}else if(sortAvg == 1) {//??????????????? ??????	
			JPAQueryFactory queryFactory = new JPAQueryFactory(em);
			QHotelScore hotelScore = QHotelScore.hotelScore;
			QHotel hotel = QHotel.hotel;
			QueryResults<HotelMainFormDto> results;
			if(type == 0) {
				results = queryFactory
						.select(Projections.fields(HotelMainFormDto.class,
								hotel.seq.as("seq"), hotel.name.as("name"), hotel.phone.as("phone"),
								hotel.address.as("address"), hotel.description.as("description"),
						hotel.img.as("img"), hotel.status.as("status"), hotelScore.score.avg().as("avg")))
				.from(hotelScore)//?????? ?????? ???????????? ???????????????
				.rightJoin(hotelScore.hotel, hotel)
				.groupBy(hotel.seq, hotel.name,	hotel.phone,
						hotel.address, hotel.description,
						hotel.img, hotel.status)//groupBy?????? select?????? ?????? ????????? ?????? ????????? ?????? ??? ??????????????? ?????????		
				.having(hotel.status.eq(1L))//status??? 1?????????
				.orderBy(hotelScore.score.avg().desc().nullsLast())//null??? ???????????????
				.offset(pageable.getOffset())//offset??? 0?????? ???????????? ????????? row?????? ????????? ????????? ???????????? ????????????.
				.limit(pageable.getPageSize())//limit??? ??? ????????? ????????? ????????? ???????????????.
				.fetchResults();
				//?????? ?????? ?????????, ?????? ????????? ??? ??????
				hotelMainFormDtoList = results.getResults();//?????? DTO
				count = results.getTotal();//?????? ????????? ???
				
				
			}else if(type == 1) {//??????????????? ???????????????
				results = queryFactory.select
				(Projections.fields(HotelMainFormDto.class,
						hotel.seq.as("seq"), hotel.name.as("name"), hotel.phone.as("phone"),
						hotel.address.as("address"), hotel.description.as("description"),
				hotel.img.as("img"), hotel.status.as("status"), hotelScore.score.avg().as("avg")))
				.from(hotelScore)//?????? ?????? ???????????? ???????????????
				.rightJoin(hotelScore.hotel, hotel)//right outer join
				.groupBy(hotel.seq, hotel.name,hotel.phone,
						hotel.address, hotel.description,
						hotel.img, hotel.status)
				.having(hotel.name.contains(keyword)
						//contiains(keyword): keyword??? ???????????? ????????? ??????
						.and(hotel.status.eq(1L)))//status??? 1?????????
				.orderBy(hotelScore.score.avg().desc().nullsLast())//null??? ???????????????
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())//limit??? ??? ????????? ????????? ????????? ???????????????.
				.fetchResults();
				//?????? ?????? ?????????, ?????? ????????? ??? ??????
				
				hotelMainFormDtoList = results.getResults();//?????? DTO
				count = results.getTotal();//?????? ????????? ???
			}else if(type == 2) {//??????????????? ???????????????.
				results = queryFactory.select
				(Projections.fields(HotelMainFormDto.class,
						hotel.seq.as("seq"), hotel.name.as("name"), hotel.phone.as("phone"),
						hotel.address.as("address"), hotel.description.as("description"),
				hotel.img.as("img"), hotel.status.as("status"), hotelScore.score.avg().as("avg")))
				.from(hotelScore)//?????? ?????? ???????????? ???????????????
				.rightJoin(hotelScore.hotel, hotel)//right outer join
				.groupBy(hotel.seq, hotel.name,hotel.phone,
						hotel.address, hotel.description,
						hotel.img, hotel.status)
				.having(hotel.address.contains(keyword)
						//contiains(keyword): keyword??? ???????????? ????????? ??????
						.and(hotel.status.eq(1L)))//status??? 1?????????
				.orderBy(hotelScore.score.avg().desc().nullsLast())//null??? ???????????????
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())//limit??? ??? ????????? ????????? ????????? ???????????????.
				.fetchResults();
				//?????? ?????? ?????????, ?????? ????????? ??? ??????
				
				hotelMainFormDtoList = results.getResults();//?????? DTO
				count = results.getTotal();//?????? ????????? ???
				
			}else {
				count = 0;
			}
			
			for(HotelMainFormDto hotelMainFormDto : hotelMainFormDtoList) {
				
				if(hotelRepository.findById(hotelMainFormDto.getSeq()).get().getHotelScores().size() > 0) {
					hotelMainFormDto.setAvg(Math.round(hotelMainFormDto.getAvg() * 100) / 100.0);
				}
				Double avg = hotelMainFormDto.getAvg();
				hotelMainFormDto.setScoreString(scoreMap.get(avg.intValue()));
				hotelMainFormDto.setDataCount(count);
			}
			
		}
		

		return hotelMainFormDtoList;
	}

	
	@Transactional
	public HotelDetailFormDto getHotelDetail(long seq){
		Map<Integer, String> scoreMap = getScoreMap();
		Hotel hotel = hotelRepository.findById(seq).get(); //hotel?????? ??????
		
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
		//?????? ?????? ??????
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
		for(Room room : roomList) {//?????? ?????? ?????? ??? ?????? ??????
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
	
	public List<Room> getRoomList(Hotel hotel){//?????? ?????? seq????????? ??? ????????? ???????????????.
		return roomRepository.findAllByHotel(hotel);
	}
}
