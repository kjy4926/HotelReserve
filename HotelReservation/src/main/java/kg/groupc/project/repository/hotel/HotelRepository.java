package kg.groupc.project.repository.hotel;

import java.io.Serializable;
import java.util.List;

import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.repository.BaseRepository;

public interface HotelRepository<T, ID extends Serializable> extends BaseRepository<Hotel, Long>{

}
