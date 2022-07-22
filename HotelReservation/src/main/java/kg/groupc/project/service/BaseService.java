package kg.groupc.project.service;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;

import kg.groupc.project.repository.BaseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseService<T, ID extends Serializable> {
	@PersistenceContext
	protected EntityManager em;
	
	@Autowired
	protected BaseRepository<T, ID> baseRepository;
	
	public BaseService(BaseRepository<T, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}
	
	protected JPAQuery<T> select(){
		return new JPAQuery<T>(em);
	}
	protected void refresh(Object param) {
		em.refresh(param);
	}
	protected void persist(Object param) {
		em.persist(param);
	}
}
