package com.example.MVC.Service;
import com.example.MVC.dto.ClubDto;
import com.example.MVC.entity.Club;
import com.example.MVC.repository.ClubRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;


    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }


    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((this::mapToClubDto)).collect(Collectors.toList());
    }


    private ClubDto mapToClubDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }


    @Override
    public Club save(ClubDto club) {
        Club entity=mapToClub(club);
        return clubRepository.save(entity);
    }


    @Override
    public ClubDto findClubById(long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new RuntimeException("Clip Doesn't exist"));
        return mapToClubDto(club);

    }


    private Club mapToClub(ClubDto club) {
        return Club.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .photoUrl(club.getPhotoUrl())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }


    @Override
    public void update(ClubDto clubDto) {
        Club club=mapToClub(clubDto);
        clubRepository.save(club);
    }
}
