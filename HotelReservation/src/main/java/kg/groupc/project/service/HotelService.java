package kg.groupc.project.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kg.groupc.project.constant.Role;
import kg.groupc.project.dto.HotelMainFormDto;
import kg.groupc.project.entity.Account;
import kg.groupc.project.entity.Hotel;
import kg.groupc.project.entity.HotelScore;
import kg.groupc.project.entity.QHotel;
import kg.groupc.project.entity.QHotelScore;
import kg.groupc.project.repository.AccountRepository;
import kg.groupc.project.repository.HotelRepository;
import kg.groupc.project.repository.HotelScoreRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {
	
	
	private final HotelScoreRepository hotelScoreRepository;
	private final HotelRepository hotelRepository;
	private final AccountRepository accountRepository;
	
	@PersistenceContext
	EntityManager em;
	
	

	
	public Long getHotelList(String keyword){
		Long count;
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		//QueryDSL을 사용하기 위해선 먼저 QueryDSL이 제공하는 JPAQueryFactory 클래스의 인스턴스를 생성해야 합니다
		//EntityManager 객체를 주입하여 인스턴스를 생성해야 합니다.
		QHotelScore hotelScore = QHotelScore.hotelScore;
		QHotel hotel = QHotel.hotel;
		//QueryDsl을 통해 쿼리를 생성할 때 이 QDomain객체를 사용합니다.
		//(xml파일에서 plugin으로 com.mysema.maven추가한 설정이 Entity클래스 작성과 동시에
		//Entity클래스를 본따 Entity클래스명 앞에 Q가 붙은 객체를 만들어준다)
		//생성된 QDomain 객체를 보면 실제 도메인 객체의 모든 필드에 대해 사용가능한 모든
		//operation을 호출하는 메서드들이 정의되어 있다.

		if(keyword == null |keyword == "") {//호텔메뉴 눌렀을 시 최초로 보여지는 화면, 별점순으로 정렬합니다
	 		count = queryFactory.select
			(Projections.constructor(HotelMainFormDto.class, hotel.seq,
					hotel.name,	hotel.phone, hotel.address, hotel.description,
					hotel.img, hotel.status, hotelScore.score.avg()))
			//select 안에는 조회할 테이블 또는 컬럼을 넣습니다
			//Projections.constructor는 저장할 대상이 엔티티가 아닌 DTO일 경우 사용합니다.
			//값을 넣을 때 생성자의 자료형과 순서를 일치시켜야 합니다
			.from(hotelScore)//조회 대상 테이블을 지정합니다
			.rightJoin(hotelScore.hotel, hotel)
			.groupBy(hotel.seq, hotel.name,	hotel.phone,
					hotel.address, hotel.description,
					hotel.img, hotel.status)
			//groupBy에는 select절에 있는 컬럼을 함수 부분만 빼고 다 적어주어야 합니다
			.having(hotel.status.eq(1L))//status가 1인것만
			.orderBy(hotelScore.score.avg().desc().nullsLast())
			//nullsLast(): null값이 뒤에 오도록, 안쓰면 nullsFirst가 디폴트
			//orderBy(): 필드명(column)을 기준으로 내림차순 또는 오름차순 정렬을 합니다
			.fetchCount();
			//조회 결과 리스트 반환
				
			return count;
		}else {//키워드에 값이 있을 경우 지역명 또는 호텔명으로 검색합니
			count = queryFactory.select
			(Projections.constructor(HotelMainFormDto.class, hotel.seq,
					hotel.name,	hotel.phone, hotel.address, hotel.description,
					hotel.img, hotel.status, hotelScore.score.avg()))
			//select 안에는 조회할 테이블 또는 컬럼을 넣습니다
			//Projections.constructor는 저장할 대상이 엔티티가 아닌 DTO일 경우 사용합니다.
			//값을 넣을 때 생성자와 순서를 일치시켜야 합니다
			.from(hotelScore)//조회 대상 테이블을 지정합니다
			.rightJoin(hotelScore.hotel, hotel)//right outer join
			.groupBy(hotel.seq, hotel.name,hotel.phone,
					hotel.address, hotel.description,
					hotel.img, hotel.status)
			.having(hotel.name.contains(keyword)
					//contiains(keyword):keyword를 포함하고 있으면 출력
					.or(hotel.address.contains(keyword))
					.and(hotel.status.eq(1L)))//status가 1인것만
					//eq(필드값): 컬럼앞에 붙여 선택한 컬럼이 필드값과 같은 요소를 찾습니다
			.orderBy(hotel.name.asc())
			//정렬조건 (호텔이름 가나다순)
			//orderBy(): 필드명(column)을 기준으로 내림차순 또는 오름차순 정렬을 합니다
			//WHERE는 그룹화 하기 전이고, HAVING은 그룹화 후의 조건입니다.
			.fetchCount();//조회 결과 리스트 반환
	
			return count;
		}


	}
	
	public List<HotelMainFormDto> searchPageSimple(String keyword, Pageable pageable){
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		//QueryDSL을 사용하기 위해선 먼저 QueryDSL이 제공하는 JPAQueryFactory 클래스의 인스턴스를 생성해야 합니다
		//EntityManager 객체를 주입하여 인스턴스를 생성해야 합니다.
		QHotelScore hotelScore = QHotelScore.hotelScore;
		QHotel hotel = QHotel.hotel;
		//QueryDsl을 통해 쿼리를 생성할 때 이 QDomain객체를 사용합니다.
		//(xml파일에서 plugin으로 com.mysema.maven추가한 설정이 Entity클래스 작성과 동시에
		//Entity클래스를 본따 Entity클래스명 앞에 Q가 붙은 객체를 만들어준다)
		//생성된 QDomain 객체를 보면 실제 도메인 객체의 모든 필드에 대해 사용가능한 모든
		//operation을 호출하는 메서드들이 정의되어 있다.
		List<HotelMainFormDto> avg;
		if(keyword == null |keyword == "") {//호텔메뉴 눌렀을 시 최초로 보여지는 화면, 별점순으로 정렬합니다
			avg = queryFactory.select
			(Projections.constructor(HotelMainFormDto.class, hotel.seq,
					hotel.name,	hotel.phone, hotel.address, hotel.description,
					hotel.img, hotel.status, hotelScore.score.avg()))
			//select 안에는 조회할 테이블 또는 컬럼을 넣습니다
			//Projections.constructor는 저장할 대상이 엔티티가 아닌 DTO일 경우 사용합니다.
			//값을 넣을 때 생성자의 자료형과 순서를 일치시켜야 합니다
			.from(hotelScore)//조회 대상 테이블을 지정합니다
			.rightJoin(hotelScore.hotel, hotel)
			.groupBy(hotel.seq, hotel.name,	hotel.phone,
					hotel.address, hotel.description,
					hotel.img, hotel.status)
			//groupBy에는 select절에 있는 컬럼을 함수 부분만 빼고 다 적어주어야 합니다
			.having(hotel.status.eq(1L))//status가 1인것만
			.orderBy(hotelScore.score.avg().desc().nullsLast())
			//nullsLast(): null값이 뒤에 오도록, 안쓰면 nullsFirst가 디폴트
			//orderBy(): 필드명(column)을 기준으로 내림차순 또는 오름차순 정렬을 합니다
			.offset(pageable.getOffset())
			//offset은 0부터 시작하며 몇번째 row부터 데이터 조회를 시작할지 정한다
			//중요한건 시작 페이지를 정하는게 아니라 시작 row를 정하는 것이다.
			//만약 한 화면에 보여지는 데이터의 개수가 10개이고, 
			//2번 페이지(페이지는 0부터 시작하므로 2번 페이지는 3번째 페이지이다)를 조회하고 싶다면
			//offset은 1이 아니라 10이 되어야 한다
			//pageable의 getOffset은 page*size를 반환한다
			.limit(pageable.getPageSize())
			//limit는 한 화면에 보여줄 데이터 개수이다
			.fetch();
			//조회 결과 리스트 반환
			
		}else {//키워드에 값이 있을 경우 지역명 또는 호텔명으로 검색합니
			avg = queryFactory.select
			(Projections.constructor(HotelMainFormDto.class, hotel.seq,
					hotel.name,	hotel.phone, hotel.address, hotel.description,
					hotel.img, hotel.status, hotelScore.score.avg()))
			//select 안에는 조회할 테이블 또는 컬럼을 넣습니다
			//Projections.constructor는 저장할 대상이 엔티티가 아닌 DTO일 경우 사용합니다.
			//값을 넣을 때 생성자와 순서를 일치시켜야 합니다
			.from(hotelScore)//조회 대상 테이블을 지정합니다
			.rightJoin(hotelScore.hotel, hotel)//right outer join
			.groupBy(hotel.seq, hotel.name,hotel.phone,
					hotel.address, hotel.description,
					hotel.img, hotel.status)
			.having(hotel.name.contains(keyword)
					//contiains(keyword):keyword를 포함하고 있으면 출력
					.or(hotel.address.contains(keyword))
					.and(hotel.status.eq(1L)))//status가 1인것만
			.orderBy(hotel.name.asc())
			//정렬조건 (호텔이름 가나다순)
			//orderBy(): 필드명(column)을 기준으로 내림차순 또는 오름차순 정렬을 합니다
			//WHERE는 그룹화 하기 전이고, HAVING은 그룹화 후의 조건입니다.
			.offset(pageable.getOffset())
			//offset은 0부터 시작하며 몇번째 row부터 데이터 조회를 시작할지 정한다
			//중요한건 시작 페이지를 정하는게 아니라 시작 row를 정하는 것이다.
			//만약 한 화면에 보여지는 데이터의 개수가 10개이고, 
			//2번 페이지(페이지는 0부터 시작하므로 2번 페이지는 3번째 페이지이다)를 조회하고 싶다면
			//offset은 1이 아니라 10이 되어야 한다
			.limit(pageable.getPageSize())
			//limit는 한 화면에 보여줄 데이터 개수이다
			.fetch();
			//조회 결과 리스트 반환
	 		
		}
		
		return avg;
		
	}
	
	
	
	public List<Tuple> searchAvg(){//left outer Join
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		//QueryDSL을 사용하기 위해선 먼저 QueryDSL이 제공하는 JPAQueryFactory 클래스의 인스턴스를 생성해야 합니다
		//EntityManager 객체를 주입하여 인스턴스를 생성해야 합니다.
		QHotelScore hotelScore = QHotelScore.hotelScore;
		QHotel hotel = QHotel.hotel;
		List<Tuple> avgList = queryFactory.select(hotel.seq, hotel.name,
				hotel.phone, hotel.address, hotel.description,
				hotel.img, hotel.status, hotelScore.score)
				.from(hotelScore)
				.leftJoin(hotel)//hotelScore의 모든 값에 fk와 동일한 pk를 가진 행과 결합됩니다
				.on(hotelScore.hotel.eq(hotel.hotel))
				.fetch();
		
		return avgList;
	}
	
	public List<Tuple> searchAvg2(){//right outer join
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		//QueryDSL을 사용하기 위해선 먼저 QueryDSL이 제공하는 JPAQueryFactory 클래스의 인스턴스를 생성해야 합니다
		//EntityManager 객체를 주입하여 인스턴스를 생성해야 합니다.
		QHotelScore hotelScore = QHotelScore.hotelScore;
		QHotel hotel = QHotel.hotel;
		List<Tuple> avgList = queryFactory.select(hotel.seq, hotel.name,
				hotel.phone, hotel.address, hotel.description,
				hotel.img, hotel.status, hotelScore.score)
				.from(hotelScore)
				.rightJoin(hotel)
				//hotel테이블의 모든 값에 hotelscore의 pk와 동일한 fk를 가진 값들이 결합되어 출력됩니다
				.on(hotelScore.hotel.eq(hotel.hotel))
				.fetch();
		
		return avgList;
	}
	
	public List<Tuple> searchAvg3(){//inner join
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		//QueryDSL을 사용하기 위해선 먼저 QueryDSL이 제공하는 JPAQueryFactory 클래스의 인스턴스를 생성해야 합니다
		//EntityManager 객체를 주입하여 인스턴스를 생성해야 합니다.
		QHotelScore hotelScore = QHotelScore.hotelScore;
		QHotel hotel = QHotel.hotel;
		List<Tuple> avgList = queryFactory.select(hotel.seq, hotel.name,
				hotel.phone, hotel.address, hotel.description,
				hotel.img, hotel.status, hotelScore.score)
				.from(hotelScore)
				.join(hotel)//hotelscore의 모든 값에 hotel의 값이 붙습니다.
				//hotelscore에 데이터가 없으면 행이 만들어지지 않습니다.
				.on(hotelScore.hotel.eq(hotel.hotel))
				.fetch();
		
		return avgList;
	}
	
	
	
	public void createHotelTest() {//샘플호텔 넣는 용도입니다 
		for(int i=1;i<=10;i++) {
			Hotel hotel = new Hotel();
			hotel.setName("호텔"+ i);
			hotel.setPhone("0101111"+ i);
			hotel.setAddress("서울" + i);
			hotel.setDescription("설명" + i);
			hotel.setImg("이미지경로" + i);
			Hotel savedHotel = hotelRepository.save(hotel);//엔티티 저장 및 수정
		}
	}
	

	public void createHotelScore() {//샘플 점수 주입용도입니다 뭐좀 확인하려고 만들다 만거예요 
		
		java.util.Date date = new java.util.Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date sqlDate = Date.valueOf(simpleDateFormat.format(date));
		List<Account> accountList = accountRepository.findAll();
		List<Hotel> hotelList= hotelRepository.findAll();
		for(int i = 0; i < hotelList.size(); i++) {
			Hotel hotel = hotelList.get(i);
			for(int j = 0; j < accountList.size(); j++) {
				Account account = accountList.get(j);
				System.out.println(accountList.get(j));
				HotelScore hotelScore = new HotelScore();
				hotelScore.setHotel(hotel.getSeq());
				hotelScore.setWriter(account.getUserId());
				hotelScore.setScore(j+1L);
				hotelScore.setDescription("설명"+j+1);
				hotelScore.setDay(sqlDate);
				
				hotelScoreRepository.save(hotelScore);
			}

		}
		
	}
	
	public void sampleId() {
		//샘플 계정 넣을 용도입니다 만들다 만거예요 뭐좀 확인하려다가
		for(int i=1;i<=10;i++) {
			Account account = new Account();
//			account.setUserId("id"+i);
			account.setPassword("12312321"+i);
			account.setName("이름" + i);
			account.setEmail("이메일" + i);
			account.setPhone("0100101000");
			account.setAddress("서울" + i);
			account.setBirth("생일" + i);
			account.setRole(Role.CLIENT);
			account.setStatus(1L);
			accountRepository.save(account);
			//엔티티 저장 및 수정
		}
	}
}
