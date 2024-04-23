package edu.iu.dcrispin.ducksservice.controllers;

import edu.iu.dcrispin.ducksservice.model.DuckData;
import edu.iu.dcrispin.ducksservice.model.Duck;
import edu.iu.dcrispin.ducksservice.repository.DucksFileRepository;
import edu.iu.dcrispin.ducksservice.repository.DucksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ducks")
public class DuckController {

    private DucksRepository ducksRepository;
    private DucksFileRepository ducksFileRepository;

    public DuckController(DucksRepository ducksRepository) {
        this.ducksRepository = ducksRepository;
    }


   @PostMapping
    public void add(@RequestBody DuckData duck) {
       try {
            ducksRepository.save(duck);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

    @GetMapping
    public List<DuckData> findAll() {
        try {
            return ducksFileRepository.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuckData> find(@PathVariable int id) {
        try {
            DuckData duck = ducksFileRepository.find(id);
            if(duck != null) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(duck);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/image")
    public boolean updateImage(@PathVariable int id,
                               @RequestParam MultipartFile file) {
        try {
            return ducksFileRepository.updateImage(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/audio")
    public boolean updateAudio(@PathVariable int id,
                               @RequestParam MultipartFile file) {
        try {
            return ducksFileRepository.updateAudio(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable int id) {
        try {
            byte[] image = ducksFileRepository.getImage(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/audio")
    public ResponseEntity<?> getAudio(@PathVariable int id) {
        try {
            byte[] image = ducksFileRepository.getAudio(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.valueOf("audio/mp3"))
                    .body(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
