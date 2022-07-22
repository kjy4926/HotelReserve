package kg.groupc.project.repository.hotel;

import java.io.Serializable;
import java.util.List;

import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.repository.BaseRepository;

public interface RoomRepository<T, ID extends Serializable> extends BaseRepository<Room, Long>{

	List<Room> findAllByHotel(Hotel hotel);
	
}
