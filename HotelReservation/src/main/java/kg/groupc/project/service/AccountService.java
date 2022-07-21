package kg.groupc.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import kg.groupc.project.dto.InfoChangeFormDto;
import kg.groupc.project.entity.Account;
import kg.groupc.project.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService{
	
	private final AccountRepository accountRepository;
	
	//test method
	public List<Account> getThreeAccounts(){
		Pageable limit = PageRequest.of(0, 3);
		return accountRepository.findAll(limit).getContent();
	}
	
	public Account getAccountById(String userId) {
		return accountRepository.findById(userId).get();
	}
	
	public List<Account> getAllAccounts(){
		return accountRepository.findAll();
	}
	
	public Account saveAccount(Account account) {
		if(!idDuplicateCheck(account.getUserId())) {
			return accountRepository.save(account);
		}else {
			return null;
		}
	}
	
	public Account changeAccountInfo(InfoChangeFormDto infoChangeFormDto) {
		Account account = accountRepository.findById(infoChangeFormDto.getUserId()).get();
		account.setName(infoChangeFormDto.getUsername());
		account.setEmail(infoChangeFormDto.getEmail());
		account.setPhone(infoChangeFormDto.getPhone());
		account.setAddress(infoChangeFormDto.getAddress() + " " +infoChangeFormDto.getAddressDetail());
		return accountRepository.save(account);
	}
	
	public boolean idDuplicateCheck(String userId) {
		Account account = accountRepository.findByUserId(userId);
		if(account == null) 
			return false;
		else 
			return true;
	}

	public Map<String, String> validateHandling(Errors errors){
		Map<String, String> validatorResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}

		return validatorResult;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Account account = accountRepository.findByUserId(userId);
		
		if(account == null) {
			throw new UsernameNotFoundException(userId);
		}
		return User.builder()
				.username(account.getUserId())
				.password(account.getPassword())
				.roles(account.getRole().toString())
				.build();
	}
}
