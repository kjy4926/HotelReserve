package kg.groupc.project.repository.account;

import java.io.Serializable;

import kg.groupc.project.entity.account.Account;
import kg.groupc.project.repository.BaseRepository;

public interface AccountRepository<T, ID extends Serializable> extends BaseRepository<Account, Long> {
	Account findByUserId(String userId);
}
