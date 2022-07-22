package kg.groupc.project.repository.inquire;

import java.io.Serializable;

import kg.groupc.project.entity.inquire.Inquire;
import kg.groupc.project.repository.BaseRepository;

public interface InquireRepository<T, ID extends Serializable> extends BaseRepository<Inquire, Long>{

}
