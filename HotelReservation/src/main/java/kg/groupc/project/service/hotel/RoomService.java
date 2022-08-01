package kg.groupc.project.service.hotel;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.dto.hotel.RoomAddFormDto;
import kg.groupc.project.dto.hotel.RoomDto;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.repository.hotel.HotelRepository;
import kg.groupc.project.repository.hotel.RoomRepository;
import kg.groupc.project.service.BaseService;

@Service
public class RoomService<T, ID extends Serializable> extends BaseService<Room, Long> {
	@Autowired
	private RoomRepository<Room, Long> roomRepository;
	@Autowired
	private HotelRepository<Hotel, Long> hotelRepository;
	
	public Room getRoomBySeq(Long seq) {
		return roomRepository.findById(seq).get();
	}
	
	public Room saveRoom(RoomAddFormDto roomAddFormDto) {
		Hotel hotel = hotelRepository.findById(roomAddFormDto.getHotelSeq()).get();
		Room room = new Room();
		room.setHotel(hotel);
		room.setName(roomAddFormDto.getName());
		room.setPrice(roomAddFormDto.getPrice());
		room.setPeople(roomAddFormDto.getPeople());
		room.setDescription(roomAddFormDto.getDescription());
		roomRepository.save(room);
		
		String filePath = System.getProperty("user.dir")+"\\src\\main\\webapp\\resources\\img\\room\\" + room.getSeq() + ".jpg";
		MultipartFile mfile = roomAddFormDto.getUploadFile();
		
		try {
			mfile.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		room.setImg(room.getSeq()+".jpg");
		
		return roomRepository.save(room);
	}
	
	public RoomDto RoomToRoomDto(Room room) {
		Hotel hotel = room.getHotel();
		RoomDto roomDto = new RoomDto();
		roomDto.setSeq(room.getSeq());
		roomDto.setHotelSeq(hotel.getSeq());
		roomDto.setName(room.getName());
		roomDto.setPeople(room.getPeople());
		roomDto.setPrice(room.getPrice());
		roomDto.setDescription(room.getDescription());
		roomDto.setImg(room.getImg());
		return roomDto;
	}
	
	public Room updateRoom(Long seq, RoomAddFormDto roomAddFormDto) {
		Room room = roomRepository.findById(seq).get();
		room.setName(roomAddFormDto.getName());
		room.setPrice(roomAddFormDto.getPrice());
		room.setPeople(roomAddFormDto.getPeople());
		room.setDescription(roomAddFormDto.getDescription());
		
		String filePath = System.getProperty("user.dir")+"\\src\\main\\webapp\\resources\\img\\room\\" + room.getSeq() + ".jpg";
		MultipartFile mfile = roomAddFormDto.getUploadFile();
		
		if(!mfile.isEmpty()) {
			try {
				mfile.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return roomRepository.save(room);
	}
	
	public boolean deleteRoom(Long seq) {
		try {
			roomRepository.deleteById(seq);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
