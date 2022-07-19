package kg.groupc.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kg.groupc.project.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>, QuerydslPredicateExecutor<Account> {
	Account findByUserId(String userId);
}
