package kg.groupc.project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import kg.groupc.project.service.HotelService;
import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class HotelPageRequest {
	
	@Autowired
	HotelService hotelService;
	
	int prevPage;//이전 10개 페이지(<), 9로 끝남, 클릭하면 9로 끝나는 페이지로 이동
	int firstPage;//처음 페이지(<<), 클릭하면 첫페이지로 이동
	
	int nextPage;//다음페이지(>), 0으로 끝남, 클릭하면 0으로 끝나는 페이지로 이동
	int maxPage;//마지막 페이지(>>), 첫 페이지가 0부터 시작한다는걸 감안하고 계산
	
	public int pageRequest(String keyword, Pageable pageable) {
		int count = hotelService.getHotelCount(keyword);//총 컬럼개수
		int num = pageable.getPageNumber()/10;//현재페이지/10
		
		if(count%10 == 0) {//마지막 페이지
			this.setMaxPage(count/10 - 1);
		}else {
			this.setMaxPage(count/10);
		}
		
		if(pageable.getPageNumber()< 10) {//이전 10개 페이지(<)로 이동용도
			this.setPrevPage(-1);
		}else {
			this.setPrevPage((num - 1)*10 + 9);
		}
		
		if(pageable.getPageNumber()< 10) {//처음 페이지(<<)로 이동용도
			this.setFirstPage(-1);
		}else {
			this.setFirstPage(0);
		}
		
		if(pageable.getPageNumber() >= 10 && count > 10) {//다음페이지(>)로 이동용돈
			this.setNextPage(num);
		}
		
		return this.getMaxPage();
	};
	
	
	
}
