package kg.groupc.project.repository.restaurant;

import java.io.Serializable;

import kg.groupc.project.entity.restaurant.Stars;
import kg.groupc.project.repository.BaseRepository;

// Stars Table 복합키를 담는 IdClass
public interface StarsRepository<T, ID extends Serializable> extends BaseRepository<Stars, Long>{

}
