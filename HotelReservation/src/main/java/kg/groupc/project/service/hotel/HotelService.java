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
	
	
	private long dataCount;//데이터 전체 개수 반환 용도
	
	@Transactional
	public List<HotelMainFormDto> getHotelList(String keyword, int num, Pageable pageable){
		//num = 0(검색어 조건x), 1(호텔명), 2(지역명)
		if(num == 0 && (keyword == null || keyword == "")) {
			Page<Hotel> hotell = hotelRepository.findByStatus(1L, pageable);//status 1인것만
			List<Hotel> hotelList = hotell.getContent();
			long count = hotell.getTotalElements();
			this.setDataCount(count);
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
			this.setDataCount(count);
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
			this.setDataCount(count);
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
		
		
		
		//keyword는 검색 키워드, page는 페이지 번호
//		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//		
//		QHotelScore hotelScore = QHotelScore.hotelScore;
//		QHotel hotel = QHotel.hotel;
//		
//		List<HotelMainFormDto> hotelList;//DTO저장용
//		if(keyword == null |keyword == "") {//호텔메뉴 눌렀을 시 최초로 보여지는 화면, 별점순으로 정렬합니다
//			hotelList = queryFactory.select
//			(Projections.constructor(HotelMainFormDto.class, hotel.seq,
//					hotel.name,	hotel.phone, hotel.address, hotel.description,
//					hotel.img, hotel.status, hotelScore.score.avg()))
//			.from(hotelScore)//조회 대상 테이블을 지정합니다
//			.rightJoin(hotelScore.hotel, hotel)
//			.groupBy(hotel.seq, hotel.name,	hotel.phone,
//					hotel.address, hotel.description,
//					hotel.img, hotel.status)//groupBy에는 select절에 있는 컬럼을 함수 부분만 빼고 다 적어주어야 합니다		
//			.having(hotel.status.eq(1L))//status가 1인것만
//			.orderBy(hotelScore.score.avg().desc().nullsLast())//null이 뒤에오도록
//			.offset(pageable.getOffset())//offset은 0부터 시작하며 몇번째 row부터 데이터 조회를 시작할지 정합니다.
//			.limit(pageable.getPageSize())//limit는 한 화면에 보여줄 데이터 개수입니다.
//			.fetch();
//			//조회 결과 리스트 반환
//			
//		}else {//키워드에 값이 있을 경우 지역명 또는 호텔명으로 검색합니다
//			hotelList = queryFactory.select
//			(Projections.constructor(HotelMainFormDto.class, hotel.seq,
//					hotel.name,	hotel.phone, hotel.address, hotel.description,
//					hotel.img, hotel.status, hotelScore.score.avg()))
//			.from(hotelScore)//조회 대상 테이블을 지정합니다
//			.rightJoin(hotelScore.hotel, hotel)//right outer join
//			.groupBy(hotel.seq, hotel.name,hotel.phone,
//					hotel.address, hotel.description,
//					hotel.img, hotel.status)
//			.having(hotel.name.contains(keyword)
//					//contiains(keyword): keyword를 포함하고 있으면 출력
//					.or(hotel.address.contains(keyword))
//					.and(hotel.status.eq(1L)))//status가 1인것만
//			.orderBy(hotel.name.asc())//정렬조건 (호텔이름 가나다순)
//			.offset(pageable.getOffset())
//			.limit(pageable.getPageSize())//limit는 한 화면에 보여줄 데이터 개수입니다.
//			.fetch();//조회 결과 리스트 반환
//	
//		}
		
//		return null;
	}
	
	//QueryDSL을 사용하기 위해선 먼저 QueryDSL이 제공하는 JPAQueryFactory 클래스의 인스턴스를 생성해야 합니다
	//EntityManager 객체를 주입하여 인스턴스를 생성해야 합니다.
	//QueryDsl을 통해 쿼리를 생성할 때 이 QDomain객체를 사용합니다.
	//(xml파일에서 plugin으로 com.mysema.maven추가한 설정이 Entity클래스 작성과 동시에
	//Entity클래스를 본따 Entity클래스명 앞에 Q가 붙은 객체를 만들어준다)
	//생성된 QDomain 객체를 보면 실제 도메인 객체의 모든 필드에 대해 사용가능한 모든
	//operation을 호출하는 메서드들이 정의되어 있다.
	//Pageable pageable = PageRequest.of(page, 10);
	//PageRequest.of():Pageable객체를 생성합니다. 
	//Pageable객체로 페이지를 조회할 때는 첫번째 페이지는 0부터 시작합니다
	//첫번째 파라미터에는 현재 페이지, 두번째 파라미터에는 한 페이지에 띄울 데이터의 양을 정합니다
	//select 안에는 조회할 테이블 또는 컬럼을 넣습니다
	//Projections.constructor는 저장할 대상이 엔티티가 아닌 DTO일 경우 사용합니다.
	//값을 넣을 때 생성자의 자료형과 순서를 일치시켜야 합니다
	//orderBy(): 필드명(column)을 기준으로 내림차순 또는 오름차순 정렬을 합니다
	//WHERE는 그룹화 하기 전이고, HAVING은 그룹화 후의 조건입니다.
	//nullsLast(): null값이 뒤에 오도록, 안쓰면 nullsFirst가 디폴트
	//offset은 0부터 시작하며 몇번째 row부터 데이터 조회를 시작할지 정합니다.
	//중요한건 시작 페이지를 정하는게 아니라 시작 row를 정하는 것입니다.
	//만약 한 화면에 보여지는 데이터의 개수가 10개이고, 
	//두번째 페이지(페이지는 0부터 시작하므로 2번 페이지는 3번째 페이지이다)를 조회하고 싶다면
	//offset안에 들어갈 수치는 1이 아니라 10이 되어야 합니다.
	//pageable의 getOffset은 page*size를 반환합니다.
	//두번째 페이지에서는 10번째 데이터부터 조회하기 시작합니다.
	
	
	public HotelMainFormDto getHotelDetail(long seq){
		

		
		

		return null;

	}
	
	
	
	public final RoomRepository<Room, Long> roomRepository;
	
	
	public List<Room> getRoomList(Hotel hotel){//해당 방의 seq값으로 방 목록을 가져옵니다.
		return roomRepository.findAllByHotel(hotel);
	}
}
