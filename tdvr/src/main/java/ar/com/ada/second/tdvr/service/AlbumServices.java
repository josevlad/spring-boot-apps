package ar.com.ada.second.tdvr.service;

import ar.com.ada.second.tdvr.component.BusinessLogicExceptionComponent;
import ar.com.ada.second.tdvr.model.dto.AlbumDTO;
import ar.com.ada.second.tdvr.model.entity.Album;
import ar.com.ada.second.tdvr.model.entity.Artist;
import ar.com.ada.second.tdvr.model.mapper.AlbumMapper;
import ar.com.ada.second.tdvr.model.mapper.AvoidingMappingContext;
import ar.com.ada.second.tdvr.model.repository.AlbumRepository;
import ar.com.ada.second.tdvr.model.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServices implements Services<AlbumDTO, Album> {

    private AlbumMapper albumMapper = AlbumMapper.MAPPER;

    @Autowired
    private AvoidingMappingContext context;

    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public AlbumDTO createNew(AlbumDTO dto) {
        return null;
    }

    public AlbumDTO createNew(AlbumDTO dto, Long id) {

        /**
         * Este metodo es especial por el tema de las entidades relacionadas, ademas de
         * recibir el dto (datos a guardar en la DB) se recibe el id de la entodad a buscar en la
         * DB para hacer la relacion.
         */

        Artist artist = artistRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Artist", id));

        Album albumToSave = albumMapper.toEntity(dto, context);

        albumToSave.setArtist(artist);

        albumRepository.save(albumToSave);

        AlbumDTO albumSaved = albumMapper.toDTO(albumToSave, context);

        return albumSaved;
    }

    @Override
    public List<AlbumDTO> getAll() {
        return null;
    }

    @Override
    public AlbumDTO getById(Long id) {
        return null;
    }

    @Override
    public AlbumDTO update(AlbumDTO dto, Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void mergeData(Album entity, AlbumDTO dto) {

    }
}
