package kg.groupc.project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.service.hotel.HotelService;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class HotelPageUtil {
	
	@Autowired
	HotelService<Hotel, Long> hotelService;
	
	int prevPage;//이전 10개 페이지(<), 9로 끝남, 클릭하면 9로 끝나는 페이지로 이동
	int firstPage;//처음 페이지(<<), 클릭하면 첫페이지로 이동
	
	int nextPage;//다음페이지(>), 0으로 끝남, 클릭하면 0으로 끝나는 페이지로 이동
	int maxPage;//마지막 페이지(>>), 첫 페이지가 0부터 시작한다는걸 감안하고 계산
	//현재페이지를 10으로 나눈 몫이 마지막 페이지를 10으로 나눈 몫과 같거나 크면 비활성화 
	
	int startPage;//c:forEach안의 시작값
	int lastPage;//c:forEach안의 끝값
	
	public int pageRequest(int data, Pageable pageable) {
		//data: 총 컬럼개수, Pageable : 현재 페이지 정보
		System.out.println(data);
		int num = pageable.getPageNumber()/10;//현재페이지/10
		if((pageable.getPageNumber() + 1)%10 == 0) {//마지막 페이지(>>), 0페이지부터 시작이라 1을 더함
			this.setMaxPage(data/10 - 1);//마지막 데이터 개수가 0으로 끝나면 끝
		}else {
			this.setMaxPage(data/10);//마지막 데이터 개수가 0으로 안끝나면 한페이지 더 만들기
		}
		
//		this.setMaxPage(data-1);//데이터 1개전용 코드
		
		
		if(pageable.getPageNumber()/10 >= this.getMaxPage()/10) {//다음페이지(>)로 이동용도
			this.setNextPage(-1);//nextPage가 -1이면 버튼 비활성
		}else {
			this.setNextPage(num*10 + 10);
		}
		
		if(pageable.getPageNumber() < 10) {//이전 10개 페이지(<)로 이동용도, 현재 페이지가 10보다 작으면 비활성
			this.setPrevPage(-1);//prevPage가 -1이면 버튼 비활성
		}else {
			this.setPrevPage((num - 1)*10 + 9);
		}
		
		if(pageable.getPageNumber() < 10) {//처음 페이지(<<)로 이동용도, 현재 페이지가 10보다 작으면 비활성
			this.setFirstPage(-1);//firstPage가 -1이면 버튼 비활성
		}else {
			this.setFirstPage(0);
		}
		
		if(this.getMaxPage() == -1) {//eltag에 값 없을 시 에러페이지 방지용
			this.setStartPage(0);
			this.setLastPage(0);
		}else {//페이지 이동버튼 출력범위 설정
			this.setStartPage(num * 10 + 1);
			
			if(this.getMaxPage()/10 == pageable.getPageNumber()/10) {
				this.setLastPage(this.getMaxPage());
			}else {
				this.setLastPage(num * 10 + 10);
			}
			
		}
		
		
		
		return this.getMaxPage();
	};
	
	
	
}
