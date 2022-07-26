package kg.groupc.project.service.restaurant;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import kg.groupc.project.entity.restaurant.Stars;
import kg.groupc.project.repository.restaurant.StarsRepository;
import kg.groupc.project.service.BaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StarsService<T, ID extends Serializable> extends BaseService<Stars, Long> {
	private final StarsRepository<Stars, Long> starsRepository;
	
	public Stars saveStars(Stars stars) {
		return starsRepository.save(stars);
	}
}
