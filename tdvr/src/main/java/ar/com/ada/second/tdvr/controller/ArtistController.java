package ar.com.ada.second.tdvr.controller;

import ar.com.ada.second.tdvr.model.dto.ArtistDTO;
import ar.com.ada.second.tdvr.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping({ "/", "" })
    public ResponseEntity getArtistsMethod() {
        // se llama al servicio y se le pide el listado de artistas
        List<ArtistDTO> artists = artistService.getAll();

        // se crea el response request
        return ResponseEntity
                .ok()
                .body(artists);
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public ResponseEntity getArtistByIdMethod(@PathVariable Long id) {

        ArtistDTO byId = artistService.getById(id);

        return ResponseEntity
                .ok()
                .body(byId);
    }

    @PostMapping({ "/", "" })
    public ResponseEntity postArtistMethod(@Valid @RequestBody ArtistDTO dto) throws URISyntaxException {
        // se llama al servicio y se le pide que guarde el artista
        ArtistDTO newArtist = artistService.createNew(dto);

        URI uri = new URI("/artists/" + newArtist.getId());

        return ResponseEntity
                .created(uri)
                .body(newArtist);
    }

//    @PutMapping({ "/{id}", "/{id}/" })
//    public ResponseEntity putArtistByIdMethod() {
//        return null;
//    }

    @PatchMapping({ "/{id}", "/{id}/" })
    public ResponseEntity patchArtistByIdMethod(@RequestBody ArtistDTO dto, @PathVariable Long id) {

        ArtistDTO artisUpdated = artistService.update(dto, id);

        return ResponseEntity
                .ok()
                .body(artisUpdated);
    }

    @DeleteMapping({ "/{id}", "/{id}/" })
    public ResponseEntity deleteArtistByIdMethod(@PathVariable Long id) {

        artistService.remove(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
