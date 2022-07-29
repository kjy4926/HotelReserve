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
	private HotelService<Hotel, Long> hotelService;
	
	private int prevPage;//이전 10개 페이지(<), 10으로 끝남, 클릭하면 10으로 끝나는 페이지로 이동
	private int firstPage;//처음 페이지(<<), 클릭하면 첫페이지로 이동
	
	private int nextPage;//다음페이지(>), 10으로 끝남, 클릭하면 10으로 끝나는 페이지로 이동
	private int maxPage;
	//마지막 페이지(>>), 첫 페이지가 0부터 시작한다는 것에 대한 처리는 controller에서 했음
	//현재페이지를 10으로 나눈 몫이 마지막 페이지를 10으로 나눈 몫과 같거나 크면 비활성화 
	
	private int startPage;//c:forEach안의 시작값
	private int lastPage;//c:forEach안의 끝값
	
	public int pageButtonInitialize(int data, Pageable pageable) {
		//첫 페이지가 0부터 시작하는 것에 대한 처리는 controller에서 해놓음
		//data: 총 컬럼개수, Pageable : 현재 페이지 정보
		int dataNum = 10;//한페이지에 띄울 데이터 양
		int buttonNum = 10;//한페이지에 띄울 최대 숫자 버튼 개수
		int num = pageable.getPageNumber()/buttonNum;//현재페이지의 십의자리 수를 반환
		int limit;//이걸로 maxPage를 정함
		//데이터를 10으로 나눠서 0으로 떨어지면 끝,
		//나머지가 있을 경우에는 페이지를 하나 더 추가
		if(data % dataNum == 0) {
			limit = data/dataNum;//마지막 데이터 개수가 0으로 끝나면 끝
		}else {
			limit = data/dataNum + 1;//마지막 데이터 개수가 0으로 안끝나면 maxPage하나 더 추가
		}
		
		
//		this.setMaxPage(data);//데이터 1개전용 코드
		
//		System.out.println("데이터 개수 : " + data);
		
		this.setMaxPage(limit);//maxPage 지정
		
		
		if(num >= this.getMaxPage()/buttonNum) {//다음페이지(>)로 이동용도
			this.setNextPage(-1);//nextPage가 -1이면 버튼 비활성
		}else {
			this.setNextPage(num*buttonNum + buttonNum + 1);
		}
		
		if(pageable.getPageNumber() < buttonNum) {//이전 10개 페이지(<)로 이동용도, 현재 페이지가 10보다 작으면 비활성
			this.setPrevPage(-1);//prevPage가 -1이면 버튼 비활성
		}else {
			this.setPrevPage(num*buttonNum);
		}
		
		if(pageable.getPageNumber() < buttonNum) {//처음 페이지(<<)로 이동용도, 현재 페이지가 10보다 작으면 비활성
			this.setFirstPage(-1);//firstPage가 -1이면 버튼 비활성
		}else {
			this.setFirstPage(1);
		}
		
		if(this.getMaxPage() == -1) {//eltag에 값 없을 시 에러페이지 방지용
			this.setStartPage(0);
			this.setLastPage(0);
		}else {//숫자로 출력되는 페이지 이동 버튼 범위 설정
			this.setStartPage(num * buttonNum + 1);//
			
			if(this.getMaxPage()/10 == num) {//현재 페이지의 십의자리 숫자가 마지막 페이지와 같을 시
				this.setLastPage(this.getMaxPage());//숫자로 출력되는 마지막 페이지 버튼은 maxPage와 같아짐
			}else {
				this.setLastPage(num * buttonNum + buttonNum);
			}
		}
		return this.getMaxPage();
	}
}
