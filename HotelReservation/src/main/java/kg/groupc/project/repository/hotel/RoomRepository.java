package kg.groupc.project.repository.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

	List<Room> findAllByHotel(Hotel hotel);
	
}
