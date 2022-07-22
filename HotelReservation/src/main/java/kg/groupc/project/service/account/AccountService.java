package kg.groupc.project.service.account;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import kg.groupc.project.dto.account.InfoChangeFormDto;
import kg.groupc.project.dto.account.PwdChangeFormDto;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.repository.account.AccountRepository;
import kg.groupc.project.service.BaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService<T, ID extends Serializable> extends BaseService<Account, Long> implements UserDetailsService{
	
	private final AccountRepository<Account, Long> accountRepository;
	private final PasswordEncoder passwordEncoder;
	
	//test method
	public List<Account> getThreeAccounts(){
		Pageable limit = PageRequest.of(0, 3);
		return accountRepository.findAll(limit).getContent();
	}
	
	public Account getAccountById(String userId) {
		return accountRepository.findByUserId(userId);
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
		Account account = (Account) accountRepository.findByUserId(infoChangeFormDto.getUserId());
		account.setName(infoChangeFormDto.getUsername());
		account.setEmail(infoChangeFormDto.getEmail());
		account.setPhone(infoChangeFormDto.getPhone());
		account.setAddress(infoChangeFormDto.getAddress() + " " +infoChangeFormDto.getAddressDetail());
		return accountRepository.save(account);
	}
	
	public Account changeAccountPasswordChange(String userId, PwdChangeFormDto pwdChangeFormDto) {
		Account account = accountRepository.findByUserId(userId);
		account.setPassword(passwordEncoder.encode(pwdChangeFormDto.getPassword()));
		return accountRepository.save(account);
	}
	
	public Account resignAccount(String userId) {
		Account account = accountRepository.findByUserId(userId);
		account.setStatus(0L);
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
