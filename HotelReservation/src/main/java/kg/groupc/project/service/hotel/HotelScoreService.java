package kg.groupc.project.service.hotel;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.service.BaseService;

@Service
public class HotelScoreService<T, ID extends Serializable> extends BaseService<HotelScore, Long> {

}
