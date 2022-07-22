package kg.groupc.project.repository.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import kg.groupc.project.entity.hotel.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

	Hotel findBySeq(Long seq);
}
