package ar.com.ada.second.tdvr.controller;

import ar.com.ada.second.tdvr.model.dto.AlbumDTO;
import ar.com.ada.second.tdvr.service.AlbumServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class AlbumController {

    @Autowired
    private AlbumServices albumServices;

    @PostMapping({ "/artists/{artistId}/albums", "/artists/{artistId}/albums/" })
    public ResponseEntity postArtistMethod(
            @Valid @RequestBody AlbumDTO dto,
            @PathVariable Long artistId) throws URISyntaxException {

        /**
         * Este metodo tiene una definicion especial ya que la entidad Album tiene relacion
         * con Artist, y al momento de crear un Albun, es necesario indicarle a que artista estara
         * asociado, es por ello que en la URL y en el parametro del metodo se coloca una varia (artistId)
         * para indicar cual es el id del artista a buscar en la base de datos para asociarlo a los datos
         * que llegan en el body (dto)
         *
         * esa logica esta en el servicio.
         */

        AlbumDTO albumSaved = albumServices.createNew(dto, artistId);
        URI uri = new URI("/artist/" + albumSaved.getId());

        return ResponseEntity
                .created(uri)
                .body(albumSaved);
    }
}
