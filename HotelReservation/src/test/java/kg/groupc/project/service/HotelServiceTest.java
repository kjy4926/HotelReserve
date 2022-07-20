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
import kg.groupc.project.service.HotelService;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class HotelServiceTest {
	
	@Autowired
	HotelService hotelService;
	
	String keyword = "";
	
	@Test
	void testgetHotelListDefault() {//검색x 테스트용 메소드
		long avg = hotelService.getHotelList(keyword);
		
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
		
	}

}
