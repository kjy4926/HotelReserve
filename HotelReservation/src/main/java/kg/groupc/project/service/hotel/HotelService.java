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
import org.apache.commons.io.FileUtils;

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
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.entity.hotel.QHotel;
import kg.groupc.project.entity.hotel.QHotelScore;
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
	
	public Page<Hotel> getHotelPage(int page) {
		System.out.println(page);
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
		if(sortAvg == 0) {//평점순 정렬x
			//num = 0(검색어 조건x), 1(호텔명), 2(지역명)
			if(type == 0 && (keyword == null || keyword == "")) {
				hotell = hotelRepository.findByStatus(1L, pageable);//status 1인것만	
			}else if(type == 1) {//호텔명
				hotell = hotelRepository.findByNameContainingAndStatusOrderByName(keyword, 1L, pageable);	
			}else if(type == 2) {//지역명
				hotell = hotelRepository.findByAddressContainingAndStatusOrderByAddress(keyword, 1L, pageable);
			}
			count = hotell.getTotalElements();//전체 데이터 수
			List<Hotel> hotelList = hotell.getContent();//전체 호텔
			for(Hotel hotel : hotelList) {
				Double sum = 0.0;
				List<HotelScore> hotelScores = hotel.getHotelScores();
				for(HotelScore hotelScore : hotelScores) {
					sum += hotelScore.getScore();
				}
				Double avg = 0.0;
				if(hotelScores.size()>0) {//소숫점 2자리 이상은 출력 안되게
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
			
		}else if(sortAvg == 1) {//평점순으로 정렬	
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
				.from(hotelScore)//조회 대상 테이블을 지정합니다
				.rightJoin(hotelScore.hotel, hotel)
				.groupBy(hotel.seq, hotel.name,	hotel.phone,
						hotel.address, hotel.description,
						hotel.img, hotel.status)//groupBy에는 select절에 있는 컬럼을 함수 부분만 빼고 다 적어주어야 합니다		
				.having(hotel.status.eq(1L))//status가 1인것만
				.orderBy(hotelScore.score.avg().desc().nullsLast())//null이 뒤에오도록
				.offset(pageable.getOffset())//offset은 0부터 시작하며 몇번째 row부터 데이터 조회를 시작할지 정합니다.
				.limit(pageable.getPageSize())//limit는 한 화면에 보여줄 데이터 개수입니다.
				.fetchResults();
				//조회 결과 리스트, 전체 데이터 수 반환
				hotelMainFormDtoList = results.getResults();//전체 DTO
				count = results.getTotal();//전체 데이터 수
				
				
			}else if(type == 1) {//호텔명으로 검색합니다
				results = queryFactory.select
				(Projections.fields(HotelMainFormDto.class,
						hotel.seq.as("seq"), hotel.name.as("name"), hotel.phone.as("phone"),
						hotel.address.as("address"), hotel.description.as("description"),
				hotel.img.as("img"), hotel.status.as("status"), hotelScore.score.avg().as("avg")))
				.from(hotelScore)//조회 대상 테이블을 지정합니다
				.rightJoin(hotelScore.hotel, hotel)//right outer join
				.groupBy(hotel.seq, hotel.name,hotel.phone,
						hotel.address, hotel.description,
						hotel.img, hotel.status)
				.having(hotel.name.contains(keyword)
						//contiains(keyword): keyword를 포함하고 있으면 출력
						.and(hotel.status.eq(1L)))//status가 1인것만
				.orderBy(hotel.name.asc())//정렬조건 (호텔이름 가나다순)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())//limit는 한 화면에 보여줄 데이터 개수입니다.
				.fetchResults();
				//조회 결과 리스트, 전체 데이터 수 반환
				
				hotelMainFormDtoList = results.getResults();//전체 DTO
				count = results.getTotal();//전체 데이터 수
			}else if(type == 2) {//지역명으로 검색합니다.
				results = queryFactory.select
				(Projections.fields(HotelMainFormDto.class,
						hotel.seq.as("seq"), hotel.name.as("name"), hotel.phone.as("phone"),
						hotel.address.as("address"), hotel.description.as("description"),
				hotel.img.as("img"), hotel.status.as("status"), hotelScore.score.avg().as("avg")))
				.from(hotelScore)//조회 대상 테이블을 지정합니다
				.rightJoin(hotelScore.hotel, hotel)//right outer join
				.groupBy(hotel.seq, hotel.name,hotel.phone,
						hotel.address, hotel.description,
						hotel.img, hotel.status)
				.having(hotel.address.contains(keyword)
						//contiains(keyword): keyword를 포함하고 있으면 출력
						.and(hotel.status.eq(1L)))//status가 1인것만
				.orderBy(hotel.name.asc())//정렬조건 (호텔이름 가나다순)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())//limit는 한 화면에 보여줄 데이터 개수입니다.
				.fetchResults();
				//조회 결과 리스트, 전체 데이터 수 반환
				
				hotelMainFormDtoList = results.getResults();//전체 DTO
				count = results.getTotal();//전체 데이터 수
				
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
