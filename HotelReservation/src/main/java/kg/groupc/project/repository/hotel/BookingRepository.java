package kg.groupc.project.repository.hotel;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import kg.groupc.project.entity.hotel.Booking;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.repository.BaseRepository;

public interface BookingRepository<T, ID extends Serializable> extends BaseRepository<Booking, Long>{
	List<Booking> findByRoomAndReserveDateGreaterThanEqualAndReserveEndDateLessThanEqual(Room room, Date reserveDate, Date reserveEndDate);
}
