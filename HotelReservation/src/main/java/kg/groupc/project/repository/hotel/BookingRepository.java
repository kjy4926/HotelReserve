package kg.groupc.project.repository.hotel;

import java.io.Serializable;

import kg.groupc.project.entity.hotel.Booking;
import kg.groupc.project.repository.BaseRepository;

public interface BookingRepository<T, ID extends Serializable> extends BaseRepository<Booking, Long>{

}
