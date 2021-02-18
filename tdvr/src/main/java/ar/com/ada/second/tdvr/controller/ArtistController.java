package ar.com.ada.second.tdvr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "artist")
public class ArtistController {

    @GetMapping({ "/", "" })
    public ResponseEntity getArtistsMethod() {
        return null;
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public ResponseEntity getArtistByIdMethod() {
        return null;
    }

    @PostMapping({ "/", "" })
    public ResponseEntity postArtistMethod() {
        return null;
    }

    @PutMapping({ "/{id}", "/{id}/" })
    public ResponseEntity putArtistByIdMethod() {
        return null;
    }

    @PatchMapping({ "/{id}", "/{id}/" })
    public ResponseEntity patchArtistByIdMethod() {
        return null;
    }

    @DeleteMapping({ "/{id}", "/{id}/" })
    public ResponseEntity deleteArtistByIdMethod() {
        return null;
    }
}
