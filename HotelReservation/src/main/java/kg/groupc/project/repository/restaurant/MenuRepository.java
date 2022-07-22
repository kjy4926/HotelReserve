package kg.groupc.project.repository.restaurant;

import java.io.Serializable;

import kg.groupc.project.entity.restaurant.Menu;
import kg.groupc.project.repository.BaseRepository;

public interface MenuRepository<T, ID extends Serializable> extends BaseRepository<Menu, Long> {

}
