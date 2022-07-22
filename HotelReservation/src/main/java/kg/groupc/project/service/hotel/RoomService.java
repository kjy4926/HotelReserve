package kg.groupc.project.service.hotel;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.service.BaseService;

@Service
public class RoomService<T, ID extends Serializable> extends BaseService<Room, Long> {

}
