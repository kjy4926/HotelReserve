package kg.groupc.project.service.restaurant;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import kg.groupc.project.entity.restaurant.Stars;
import kg.groupc.project.service.BaseService;

@Service
public class StarsService<T, ID extends Serializable> extends BaseService<Stars, Long> {

}
