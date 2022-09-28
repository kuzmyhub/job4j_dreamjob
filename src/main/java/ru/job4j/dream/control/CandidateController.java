package ru.job4j.dream.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.service.CandidateService;
import ru.job4j.dream.service.CityService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.service.CandidateService;

import java.io.IOException;

@ThreadSafe
@Controller
public class CandidateController {

    private final CandidateService candidateService;

    private final CityService cityService;

    public CandidateController(CandidateService candidateService,
                               CityService cityService) {
        this.candidateService = candidateService;
        this.cityService = cityService;
    }

    @GetMapping("/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidates", candidateService.findAll());
        model.addAttribute("city", cityService.getAllCities());
        return "candidates";
    }

    @PostMapping("/createCandidate")
    public String createCandidate(@ModelAttribute Candidate candidate,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        candidate.setPhoto(file.getBytes());
        candidate.setCity(cityService.findById(candidate.getCity().getId()));
        candidateService.add(candidate);
        return "redirect:/candidates";
    }

    @PostMapping("/updateCandidate")
    public String updateCandidate(@ModelAttribute Candidate candidate) {
        candidate.setCity(cityService.findById(candidate.getCity().getId()));
        candidateService.update(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/formUpdateCandidate/{candidateId}")
    public String formUpdateCandidate(Model model, @PathVariable("candidateId") int id) {
        model.addAttribute("candidate", candidateService.findById(id));
        model.addAttribute("cities", cityService.getAllCities());
        return "updateCandidate";
    }

    @GetMapping("/formAddCandidate")
    public String formAddCandidate(Model model) {
        model.addAttribute("candidate", new Candidate(0, "Заполните поле"));
        model.addAttribute("cities", cityService.getAllCities());
        return "addCandidate";
    }

    @GetMapping("/photoCandidate/{candidateId}")
    public ResponseEntity<Resource> download(@PathVariable("candidateId") Integer candidateId) {
        Candidate candidate = candidateService.findById(candidateId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(candidate.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(candidate.getPhoto()));
    }
}
