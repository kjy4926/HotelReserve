package kg.groupc.project.service.inquire;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import kg.groupc.project.entity.inquire.Inquire;
import kg.groupc.project.service.BaseService;

@Service
public class InquireService<T, ID extends Serializable> extends BaseService<Inquire, Long> {

}
