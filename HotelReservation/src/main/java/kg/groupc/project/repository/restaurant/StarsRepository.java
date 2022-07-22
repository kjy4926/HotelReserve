package kg.groupc.project.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import kg.groupc.project.entity.restaurant.Stars;
import kg.groupc.project.entity.restaurant.StarsId;

// Stars Table 복합키를 담는 IdClass
public interface StarsRepository extends JpaRepository<Stars, StarsId>{

}
