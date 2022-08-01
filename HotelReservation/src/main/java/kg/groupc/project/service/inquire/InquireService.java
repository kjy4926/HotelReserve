package kg.groupc.project.service.inquire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kg.groupc.project.dto.inquire.InquireDto;
import kg.groupc.project.dto.inquire.InquireWriteForm;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.inquire.Inquire;
import kg.groupc.project.repository.account.AccountRepository;
import kg.groupc.project.repository.hotel.HotelRepository;
import kg.groupc.project.repository.inquire.InquireRepository;
import kg.groupc.project.service.BaseService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InquireService<T, ID extends Serializable> extends BaseService<Inquire, Long> {


	@Autowired
	private InquireRepository<Inquire, Long> inquireRepository;
	@Autowired
	private AccountRepository<Account, Long> accountRepository;
	@Autowired
	private HotelRepository<Hotel, Long> hotelRepository;
	
	// 문의 전체 리스트
	@Transactional(readOnly = true)
	public Page<Inquire> findAll(Pageable pageable){
		return inquireRepository.findAll(pageable);
	}
	// 검색(작성자)
	@Transactional(readOnly = true)
	public Page<Inquire> search1(String keyword, Pageable pageable) {
		return inquireRepository.findByUsernameContaining(keyword, pageable);
	}
	// 검색(제목)
	@Transactional(readOnly = true)
	public Page<Inquire> search2(String keyword, Pageable pageable) {
		return inquireRepository.findByTitleContaining(keyword, pageable);
	}
	// 검색(내용)
		@Transactional(readOnly = true)
		public Page<Inquire> search3(String keyword, Pageable pageable) {
			return inquireRepository.findByDescriptionContaining(keyword, pageable);
	}	
	// 문의 상세보기
	@Transactional(readOnly = true)
	public Inquire readInquire(Long seq) {
		return inquireRepository.findById(seq).orElse(null);
	}

	// 문의 작성
	@Transactional
	public Long saveInquire(InquireWriteForm idto) {
		return inquireRepository.save(idto.toEntity()).getSeq();	
	}
	
	// 문의 수정
	@Transactional
	public Inquire edit(Long seq, InquireWriteForm idto) {
		Inquire inquire = idto.toEntity();
		Inquire target = inquireRepository.findById(seq).orElse(null);
		if(target == null) {
			return null;
		}
		target.patch(inquire);
		return inquireRepository.save(target);
	}
	
	// 문의 답변
	@Transactional
	public Inquire reply(Long seq, InquireWriteForm idto, String userId) {
		Inquire inquire = inquireRepository.findById(seq).get();
		if(inquire == null) {
			return null;
		}
		Account account = accountRepository.findByUserId(userId);
		System.out.println("login account : "+account.getUserId());
		inquire.setComments(idto.getComments());
		inquire.setReply(account);
		return inquireRepository.save(inquire);
	}
	
	// 문의 삭제
	@Transactional
	public Inquire delete(Long seq) {
		Inquire inquire = inquireRepository.findById(seq).orElse(null);
		if(inquire == null) {
			return null;
		}
		inquireRepository.delete(inquire);
		return inquire;
	}
	
	@Transactional
	public InquireDto inquireToInquireDto(Long seq, String userId) {
		Inquire inquire = inquireRepository.findById(seq).get();
		InquireDto inquireDto = new InquireDto();
		inquireDto.setSeq(inquire.getSeq());
		if(userId != null) {
			inquireDto.setWriter(accountRepository.findByUserId(userId).getUserId());
		}
		if(inquire.getHotel() != null) {
			inquireDto.setHotel(inquire.getHotel().getName());
			inquireDto.setHotelSeq(inquire.getHotel().getSeq());
		}
		inquireDto.setTitle(inquire.getTitle());
		inquireDto.setDescription(inquire.getDescription());
		inquireDto.setComments(inquire.getComments());		
		inquireDto.setDay(inquire.getDay());
		inquireDto.setCategory(inquire.getCategory());
		inquireDto.setStatus(inquire.getStatus());
		
		return inquireDto;
	}
	
	@Transactional
	public List<InquireDto> getMypageInquire(String userId){
		Account account = accountRepository.findByUserId(userId);
		List<Inquire> inquireList = inquireRepository.findByWriterOrderBySeqDesc(account, PageRequest.of(0, 5)).getContent();
		List<InquireDto> inquireDtoList = new ArrayList<InquireDto>();
		for(Inquire inquire : inquireList) {
			InquireDto inquireDto = new InquireDto();
			inquireDto.setSeq(inquire.getSeq());
			inquireDto.setWriter(account.getUserId());
			inquireDto.setTitle(inquire.getTitle());
			inquireDto.setDay(inquire.getDay());
			inquireDto.setCategory(inquire.getCategory());
			inquireDto.setStatus(inquire.getStatus());
			inquireDtoList.add(inquireDto);
		}
		return inquireDtoList;
	}
}

	

