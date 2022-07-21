package kg.groupc.project.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kg.groupc.project.entity.Hotel;
import kg.groupc.project.entity.Room;
import kg.groupc.project.repository.HotelRepository;
import kg.groupc.project.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SeleniumUtil {
	private final HotelRepository hotelRepository;
	private final RoomRepository roomRepository;
	
	@RequestMapping("/craw")
	public void crawling() {
		Map<String, String> map = new HashMap<>();
		map.put("서울","3124");
		map.put("제주","850");
		map.put("여수","3042");
		map.put("대구","6337926");
		map.put("광주","1970");
		map.put("부산","602043");
		map.put("경기","6129075");
		map.put("강원","6129076");
		map.put("충북","6129077");
		map.put("충남","6129078");
		map.put("전북","6129079");
		map.put("전남","6129080");
		map.put("경북","6129081");
		map.put("경남","6129082");
		
		String uri = "https://www.tripbtoz.com/list/";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-popup-blocking");  
		for(String key : map.keySet()) {
			String value = map.get(key);
			String url = uri + value;
			openDriver(uri, url);
		}
	}
	public void openDriver(String uri, String url) {
		WebDriver driver = null;
		WebDriver driver2 = null; // 호텔 상세정보
		WebDriver driver3 = null; // 방 사진
		Resource resource = new ClassPathResource("chromedriver.exe");
		String driverPath;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-site-isolation-trials");
		options.addArguments("disable-popup-blocking");
//		options.addArguments("headless");
		
		try {
			driverPath = resource.getFile().getPath();
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver(options);	
			driver2 = new ChromeDriver(options);
			driver3 = new ChromeDriver(options);
			
			try {	
				driver.get(url);
				Thread.sleep(5000); // 페이지 로딩 대기
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<WebElement> hotelName = driver.findElements(By.className("sc-jacqCo"));
			List<WebElement> hotelAddr = driver.findElements(By.className("sc-eCVOVf"));
			List<WebElement> hotelImg = driver.findElements(By.className("sc-jlCeNt"));
			List<WebElement> hotelDetailLink = driver.findElements(By.className("sc-jWESwd"));
			
			for(int i=0;i<hotelName.size();i++){
				String hname = hotelName.get(i).getText();
				String haddr = hotelAddr.get(i).getText();
				String himgLink = hotelImg.get(i).getAttribute("src");
				String hdetailLink = hotelDetailLink.get(i).findElement(By.tagName("a")).getAttribute("href");
				String hphone = null;
				String hdesc = null;
								
				// 호텔 세부정보 가져오기
				try {
					driver2.get(hdetailLink);
					Thread.sleep(5000); // 페이지 로딩 대기
					List<WebElement> roomName = driver2.findElements(By.className("hlkvoo"));
					List<WebElement> priceField = driver2.findElements(By.className("iQUJmh"));
					List<WebElement> peopleAndDescField = driver2.findElements(By.className("bmxinM"));
					List<WebElement> roomImgLink = driver2.findElements(By.className("idVkeT"));
					List<WebElement> phoneField = driver2.findElement(By.className("chhgVH")).findElements(By.tagName("li"));
					
					// 전화번호 찾기
					for(int j=0;j<phoneField.size();j++) {
						String str = phoneField.get(j).getText();
						if(str.contains("호텔 전화 번호")) {
							hphone = str.split(":")[1].trim();
						}
					}
					URL fileUrl = new URL(himgLink);
					// 호텔 객체 생성
					Hotel hotel = new Hotel();
					hotel.setName(hname);
					hotel.setPhone(hphone);
					hotel.setAddress(haddr);
					hotel.setStatus(1L);
					hotelRepository.save(hotel);
										
					hotel.setImg(hotel.getSeq()+".jpg");
					String filePath = "src\\main\\webapp\\resources\\img\\hotel\\" + hotel.getSeq() + ".jpg";
					FileUtils.copyURLToFile(fileUrl, new File(filePath));
					hotelRepository.save(hotel);
					
					for(int j=0;j<roomName.size();j++) {
						String rname = roomName.get(j).getText();
						String rprice = priceField.get(j).getText().replaceAll("[0-9]박", "").replaceAll("[^0-9]", "");
						String pad = peopleAndDescField.get(j).getText();
						String rpeople = pad.split(" ")[1].replaceAll("[^0-9]", "");
						String rdesc = pad.split("/")[1].trim();
						String rimgLink = roomImgLink.get(j).findElement(By.tagName("a")).getAttribute("href");
						
						driver3.get(rimgLink);
						Thread.sleep(5000); // 페이지 로딩 대기

						
						Room room = new Room();
						room.setHotel(hotel);
						room.setName(rname);
						room.setPrice(Long.parseLong(rprice));
						room.setPeople(Long.parseLong(rpeople));
						room.setDescription(rdesc);
						
						roomRepository.save(room);
						
						room.setImg(room.getSeq()+".jpg");
						URL rfileUrl = new URL(driver3.findElement(By.id("0")).getAttribute("src"));
						String rfilePath = "src\\main\\webapp\\resources\\img\\room\\" + room.getSeq() + ".jpg";
						FileUtils.copyURLToFile(rfileUrl, new File(rfilePath));
						roomRepository.save(room);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			driver.close();
			driver2.close();
			driver3.close();
		}
	}
}