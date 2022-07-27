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

import kg.groupc.project.dto.hotel.HotelMainFormDto;
import kg.groupc.project.repository.hotel.HotelRepository;
import kg.groupc.project.service.hotel.HotelService;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class HotelServiceTest {
	
	@Autowired
	HotelService hotelService;
	
	String keyword = "";
	
	@Test
	void testgetHotelListDefault() {//검색x 테스트용 메소드
//		long avg = hotelService.getHotelCount(keyword);
		
	}


	@Autowired
	HotelRepository hotelRepository;
	@PersistenceContext
	EntityManager em;
	
	
	@Test
	void testHotelService() {
		System.out.println(hotelService.getHotelDetail(1));
	}

}
