package kg.groupc.project.util;

import org.springframework.data.domain.Page;

public class PagingUtil {
	
	public int pageNumber;
	public int totalPages;
	public int startBlockPage;
	public int endBlockPage;
	
	public PagingUtil(int pageBlock, Page page) {
		this.pageNumber = page.getPageable().getPageNumber();
		this.totalPages = page.getTotalPages();
		this.startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		//현재페이지가 마지막 블록이면 마지막페이지 = 전체 페이지
		this.endBlockPage = (pageNumber / pageBlock == totalPages) ? totalPages : startBlockPage + pageBlock - 1;
	}
}
