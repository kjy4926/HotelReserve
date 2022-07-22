package kg.groupc.project.repository.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import kg.groupc.project.entity.hotel.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
