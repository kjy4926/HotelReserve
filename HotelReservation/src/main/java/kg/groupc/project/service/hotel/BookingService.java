package kg.groupc.project.service.hotel;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import kg.groupc.project.entity.hotel.Booking;
import kg.groupc.project.service.BaseService;

@Service
public class BookingService<T, ID extends Serializable> extends BaseService<Booking, Long> {

}
