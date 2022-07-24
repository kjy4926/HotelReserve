package kg.groupc.project.service.hotel;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.repository.hotel.RoomRepository;
import kg.groupc.project.service.BaseService;

@Service
public class RoomService<T, ID extends Serializable> extends BaseService<Room, Long> {
	@Autowired
	private RoomRepository<Room, Long> roomRepository;
	public Room getRoomBySeq(Long seq) {
		return roomRepository.findById(seq).get();
	}
}
