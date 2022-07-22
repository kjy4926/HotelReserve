package kg.groupc.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Booking;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.entity.hotel.Room;
import kg.groupc.project.entity.inquire.Inquire;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.entity.restaurant.RestaurantScore;
import kg.groupc.project.entity.restaurant.Stars;
import kg.groupc.project.service.account.AccountService;
import kg.groupc.project.service.hotel.BookingService;
import kg.groupc.project.service.hotel.HotelScoreService;
import kg.groupc.project.service.hotel.HotelService;
import kg.groupc.project.service.hotel.RoomService;
import kg.groupc.project.service.inquire.InquireService;
import kg.groupc.project.service.restaurant.RestaurantScoreService;
import kg.groupc.project.service.restaurant.RestaurantService;
import kg.groupc.project.service.restaurant.StarsService;

public class BaseController {
	@Autowired
	protected AccountService<Account, Long> accountService;
	
	@Autowired
	protected BookingService<Booking, Long> bookingService;
	
	@Autowired
	protected HotelScoreService<HotelScore, Long> hotelScoreService;
	
	@Autowired
	protected HotelService<Hotel, Long> hotelService;
	
	@Autowired
	protected RoomService<Room, Long> roomService;
	
	@Autowired
	protected InquireService<Inquire, Long> inquireService;
	
	@Autowired
	protected RestaurantScoreService<RestaurantScore, Long> restaurantScoreService;
	
	@Autowired
	protected RestaurantService<Restaurant, Long> restaurantService;
	
	@Autowired
	protected StarsService<Stars, Long> starsService;
}
