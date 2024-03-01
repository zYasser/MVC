package com.example.MVC.Service;
import com.example.MVC.dto.ClubDto;
import com.example.MVC.entity.Club;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ClubService {
    List<ClubDto> findAllClubs();

    Club save(Club club);
    ClubDto findClubById(long clubId);
    void update(ClubDto club);
}
