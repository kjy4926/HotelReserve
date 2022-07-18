package kg.groupc.project.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import kg.groupc.project.dto.HotelMainFormDto;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class HotelServiceTest {
	
	@Autowired
	HotelService hotelService;
	
	@Test
	void testNotSearchHotels() {
		fail("Not yet implemented");
	}

	@Test
	void testSearchHotels() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	void testSampleId() {//샘플계정생성
		hotelService.sampleId();
	}

	@Test
	void testHotelService() {
		fail("Not yet implemented");
	}

}
