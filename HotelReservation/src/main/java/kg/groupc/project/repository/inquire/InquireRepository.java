package kg.groupc.project.repository.inquire;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import kg.groupc.project.entity.inquire.Inquire;
import kg.groupc.project.repository.BaseRepository;

@Repository
public interface InquireRepository<T, ID extends Serializable> extends BaseRepository<Inquire, Long>{

	//검색(작성자) : 페이징 X
	List<Inquire> findByUsernameContaining(String keyword);
	
	//검색(제목) : 페이징 X
	List<Inquire> findByTitleContaining(String keyword);
	
	//검색(내용) : 페이징 x
	List<Inquire> findByDescriptionContaining(String keyword);
	
	//검색(작성자) + 페이징 추가
	Page<Inquire> findByUsernameContaining(String keyword, Pageable pageable);
	
	//검색(제목) + 페이징 추가
	Page<Inquire> findByTitleContaining(String keyword, Pageable pageable);		

	//검색(내용) + 페이징
	Page<Inquire> findByDescriptionContaining(String keyword, Pageable pageable);		
	
	//페이징
	Page<Inquire> findAll(Pageable pageable);
	
	Inquire findBySeq(Long seq);	
}
