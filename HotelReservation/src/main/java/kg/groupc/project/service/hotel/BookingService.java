package kg.groupc.project.service.hotel;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kg.groupc.project.dto.hotel.BookingFormDto;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Booking;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.repository.account.AccountRepository;
import kg.groupc.project.repository.hotel.BookingRepository;
import kg.groupc.project.repository.hotel.RoomRepository;
import kg.groupc.project.service.BaseService;

@Service
public class BookingService<T, ID extends Serializable> extends BaseService<Booking, Long> {
	@Autowired
	private BookingRepository<Booking, Long> bookingRepository;
	@Autowired
	private RoomRepository<Room, Long> roomRepository;
	@Autowired
	private AccountRepository<Account, Long> accountRepository;
	
	public Booking saveBooking(BookingFormDto bookingFormDto, String userId) {
		Room room = roomRepository.findById(bookingFormDto.getSeq()).get();
		Account account = accountRepository.findByUserId(userId);
		Booking booking = new Booking();
		booking.setRoom(room);
		booking.setReserver(account);
		booking.setReserveDate(bookingFormDto.getCheckin());
		booking.setReserveEndDate(bookingFormDto.getCheckout());
		booking.setStatus(1L);
		booking.setPrice(bookingFormDto.getPrice());
		booking.setPeople(bookingFormDto.getPeople());
		return bookingRepository.save(booking);
	}
	// 예약 날짜에 대하여 예약 가능 여부 확인
	public boolean reserveDateValidCheck(Long seq, String checkin, String checkout, String userId) {
		Room room = roomRepository.findById(seq).get();
		
		int[] checkinYMD = Arrays.stream(checkin.split("-")).mapToInt(Integer::parseInt).toArray();
		int[] checkoutYMD = Arrays.stream(checkout.split("-")).mapToInt(Integer::parseInt).toArray();
		
		Date checkinDate = Date.valueOf(LocalDate.of(checkinYMD[0], checkinYMD[1], checkinYMD[2]));
		Date checkoutDate = Date.valueOf(LocalDate.of(checkoutYMD[0], checkoutYMD[1], checkoutYMD[2]));

		List<Booking> booking = bookingRepository.findByRoomAndReserveDateGreaterThanEqualAndReserveEndDateLessThanEqual(room, checkinDate, checkoutDate);

		if(booking.size() == 0) {
			return true;
		}
		return false;
	}
	
	public void deleteBooking(Long seq) {
		bookingRepository.deleteById(seq);
	}
}
