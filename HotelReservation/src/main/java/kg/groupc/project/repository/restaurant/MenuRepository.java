package kg.groupc.project.repository.restaurant;


import org.springframework.data.jpa.repository.JpaRepository;

import kg.groupc.project.entity.restaurant.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
