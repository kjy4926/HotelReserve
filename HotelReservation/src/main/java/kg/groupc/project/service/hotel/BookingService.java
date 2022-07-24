package kg.groupc.project.service.hotel;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kg.groupc.project.entity.hotel.Booking;
import kg.groupc.project.repository.hotel.BookingRepository;
import kg.groupc.project.service.BaseService;

@Service
public class BookingService<T, ID extends Serializable> extends BaseService<Booking, Long> {
	@Autowired
	private BookingRepository<Booking, Long> bookingRepository;
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
}
