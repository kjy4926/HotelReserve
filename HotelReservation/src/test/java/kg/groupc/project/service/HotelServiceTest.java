package kg.groupc.project.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.querydsl.core.Tuple;

import kg.groupc.project.dto.HotelMainFormDto;
import kg.groupc.project.repository.HotelRepository;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class HotelServiceTest {
	
	@Autowired
	HotelService hotelService;
	
	@Test
	void testNotSearchHotels() {//검색x 테스트용 메소드
		List<HotelMainFormDto> avg = hotelService.notSearchHotels();
		for(int i = 0; i < avg.size(); i++) {
			System.out.println("객체" + avg.get(i));
		}
	}

	@Test
	void testSearchHotels() {//이름 또는 호텔명 검색 테스트용 메소드
		List<HotelMainFormDto> avg = hotelService.SearchHotels("서울");
		for(int i = 0; i < avg.size(); i++) {
			System.out.println("객체" + avg.get(i));
		}
		
	}

	@Test
	void testCreateHotelTest() {//샘플호텔데이터생성
		hotelService.createHotelTest();
		
//		List<HotelMainFormDto> hotelList = hotelService.notSearchHotels();
//		for(HotelMainFormDto hotelMainFormDto : hotelList) {
//			System.out.println(hotelMainFormDto.toString());
//		}
	}

	@Test
	void testCreateHotelScore() {
		hotelService.createHotelScore();
	}

	@Test
	void testSampleId() {//샘플계정생성
		hotelService.sampleId();
	}

	@Autowired
	HotelRepository hotelRepository;
	@PersistenceContext
	EntityManager em;
	
	@Test
	void testHotelService() {
//		List<Hotel> hotelList = hotelRepository.findAll(); 
//		for(int i = 0; i < hotelList.size(); i++) {
//			System.out.println(hotelList.get(i));
//		}
		
//		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//		QHotelScore hotelScore = QHotelScore.hotelScore;
//		List<HotelScore> scoreList = queryFactory.selectFrom(hotelScore)
//				.fetch();
//		System.out.println(scoreList);
//		System.out.println(scoreList.size());
//		for(int i = 0; i < scoreList.size(); i++) {		
//			System.out.println(scoreList.get(i));
//			System.out.println(scoreList + "있으면 출력");
//		}
		List<Tuple> avgList = hotelService.searchAvg();//left outer join
		for(int i = 0; i < avgList.size(); i++) {
			System.out.println(avgList.get(i));
		}
		
		avgList = hotelService.searchAvg2();//right outer join
		for(int i = 0; i < avgList.size(); i++) {
			System.out.println(avgList.get(i));
		}
		
		avgList = hotelService.searchAvg3();//inner join
		for(int i = 0; i < avgList.size(); i++) {
			System.out.println(avgList.get(i));
		}
	}

}
