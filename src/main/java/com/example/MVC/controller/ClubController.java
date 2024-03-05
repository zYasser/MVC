package com.example.MVC.controller;
import com.example.MVC.Service.ClubService;
import com.example.MVC.dto.ClubDto;
import com.example.MVC.entity.Club;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {


    private final ClubService clubService;


    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }


    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubDtoList = clubService.findAllClubs();
        model.addAttribute("clubs", clubDtoList);
        return "clubs-list";
    }


    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }


    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto club, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("club", club);
            return "clubs-create";
        }
        Club result = clubService.save(club);
        return "redirect:/clubs";
    }


    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model) {
        ClubDto clubDto=clubService.findClubById(clubId);
        model.addAttribute("club" , clubDto);
        return "clubs-detail";
    }


    @GetMapping("/clubs/{clubId}/edit")
    public String editClipForm(@PathVariable("clubId") long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-edit";

    }


    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("club") ClubDto club,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clubs-edit";
        }
        club.setId(clubId);
        clubService.update(club);
        return "redirect:/clubs";

    }

}
