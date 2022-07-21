package kg.groupc.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kg.groupc.project.entity.Hotel;
import kg.groupc.project.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

	List<Room> findAllByHotel(Hotel hotel);
	
}
