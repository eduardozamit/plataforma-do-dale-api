package com.github.plataformadodaleapi.controller;

import com.github.plataformadodaleapi.model.recruiter.RecruiterDetalingData;
import com.github.plataformadodaleapi.model.recruiter.RegisterRecruiterDTO;
import com.github.plataformadodaleapi.model.recruiter.UpdateRecruiterDTO;
import com.github.plataformadodaleapi.service.RecruiterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {
    private RecruiterService recruiterService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<RecruiterDetalingData> createRecruiter(@RequestBody @Valid RegisterRecruiterDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        RecruiterDetalingData recruiterDTO = recruiterService.createRecruiter(dto);

        URI uri = uriComponentsBuilder.path("/api/recruiters/create/{id}").buildAndExpand(recruiterDTO.id()).toUri();

        return ResponseEntity.created(uri).body(recruiterDTO);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<RecruiterDetalingData> updateUser(@RequestBody @Valid UpdateRecruiterDTO updateDTO, HttpServletRequest request) {
        RecruiterDetalingData userDto = recruiterService.updateRecruiter(updateDTO, request);

        return ResponseEntity.ok(userDto);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/profile")
    @Transactional
    public ResponseEntity<RecruiterDetalingData> recruiterProfile(HttpServletRequest request) {
        RecruiterDetalingData recruiterProfile = recruiterService.getRecruiterProfile(request);

        return ResponseEntity.ok(recruiterProfile);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(HttpServletRequest request) {
        recruiterService.deleteRecruiter(request);

        return ResponseEntity.ok().build();
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/add-favorite-student")
    public ResponseEntity<RecruiterDetalingData> favoriteStudent(@RequestParam("student") Long studentId,
                                                                 HttpServletRequest request) {
        RecruiterDetalingData recruiterDetalingData = recruiterService.favoriteStudent(request, studentId);
        return recruiterDetalingData != null
                ? ResponseEntity.ok(recruiterDetalingData)
                : ResponseEntity.badRequest().build();
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/disfavor-student")
    public ResponseEntity<RecruiterDetalingData> disfavorStudent(@RequestParam("student") Long studentId,
                                                                 HttpServletRequest request
    ) {
        RecruiterDetalingData recruiterDetalingData = recruiterService.disfavorStudent(request, studentId);
        return recruiterDetalingData != null
                ? ResponseEntity.ok(recruiterDetalingData)
                : ResponseEntity.notFound().build();
    }

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }
}
