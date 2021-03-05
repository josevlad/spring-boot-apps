package ar.com.ada.second.tdvr.service;

import ar.com.ada.second.tdvr.component.BusinessLogicExceptionComponent;
import ar.com.ada.second.tdvr.model.dto.TrackDTO;
import ar.com.ada.second.tdvr.model.entity.Album;
import ar.com.ada.second.tdvr.model.entity.Track;
import ar.com.ada.second.tdvr.model.mapper.AvoidingMappingContext;
import ar.com.ada.second.tdvr.model.mapper.TrackMapper;
import ar.com.ada.second.tdvr.model.repository.AlbumRepository;
import ar.com.ada.second.tdvr.model.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServices implements Services<TrackDTO, Track> {
    private TrackMapper trackMapper = TrackMapper.MAPPER;

    @Autowired
    private AvoidingMappingContext context;

    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public TrackDTO createNew(TrackDTO dto) {
        return null;
    }

    public TrackDTO createNew(TrackDTO dto, Long id) {

        /**
         * Este metodo es especial por el tema de las entidades relacionadas, ademas de
         * recibir el dto (datos a guardar en la DB) se recibe el id de la entodad a buscar en la
         * DB para hacer la relacion.
         */

        /**
         * Se busca el album por id (parametro id del metodo) en la base de datos a travez del repositorio.
         * Este album será a que se le asocie la cancion a guardar.
         */
        Album album = albumRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Album", id));

        /**
         * Se comvierte los datos de la cancion del dto a entity y se guarda esos datos en la
         * variable trackToSave
         */
        Track trackToSave = trackMapper.toEntity(dto, context);

        /**
         * Se le asocia el album de la base de datos a objeto trackToSave
         */
        trackToSave.setAlbum(album);

        /**
         * Se guarda la cancion en la base de datos a travez del repositorio.
         */
        trackRepository.save(trackToSave);

        /**
         * Luego del guardado, se convierte el objeto trackToSave a DTO para ser entregago
         */
        TrackDTO trackSaved = trackMapper.toDTO(trackToSave, context);

        return trackSaved;
    }

    @Override
    public List<TrackDTO> getAll() {
        /**
         * Se llama al repositorio y se le pede que haga la consulta a la BD de todos
         * los registro de de esa entidad y se guarda en la variable trackList
         *
         * Basicamente el metodo .findAll() hace la query select * from
         */
        List<Track> trackList = trackRepository.findAll();

        /**
         * Se convierte la lista de canciones (trackList) a una lista de tipo DTO
         * y se guarda en la variable tracks para luego ser el retorno del metodo.
         */
        List<TrackDTO> tracks = trackMapper.toDTO(trackList, context);

        return tracks;
    }

    @Override
    public TrackDTO getById(Long id) {
        /**
         * Busco el Track en la base de datos por id (parametro del metodo).
         * En caso que no exista, lanzara una  ExceptionEntityNotFound
         */
        Track trackByIdFromDB = trackRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Track", id));

        /**
         * Se convierte la cancion (trackByIdFromDB) a un tipo DTO
         * y se guarda en la variable trackById para luego ser el retorno del metodo.
         */
        TrackDTO trackById = trackMapper.toDTO(trackByIdFromDB, context);

        return trackById;
    }

    @Override
    public TrackDTO update(TrackDTO dto, Long id) {
        /**
         * Busco el Track en la base de datos por id (parametro del metodo).
         * En caso que no exista, lanzara una  ExceptionEntityNotFound
         */
        Track trackByIdFromDB = trackRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Track", id));

        /**
         * Este metodo hace la validacion de datos en el dto y los sustituye en el entity
         */
        mergeData(trackByIdFromDB, dto);

        /**
         * Se guarda los nuevos datos de la cancion en la base de datos a travez del repositorio.
         */
        trackRepository.save(trackByIdFromDB);

        /**
         * Se convierte la cancion (trackByIdFromDB) a un tipo DTO con los nuevos datos
         * y se guarda en la variable trackUpdated para luego ser el retorno del metodo.
         */
        TrackDTO trackUpdated = trackMapper.toDTO(trackByIdFromDB, context);

        return trackUpdated;

    }

    @Override
    public void remove(Long id) {
        /**
         * Busco el Track en la base de datos por id (parametro del metodo).
         * En caso que no exista, lanzara una  ExceptionEntityNotFound
         */
        Track trackToDelete = trackRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Track", id));

        /**
         * Con la entidad encontrada en la DB (trackToDelete) se le indica el repository que debe hacer el borrado
         * de este objeto.
         */
        trackRepository.delete(trackToDelete);
    }


    @Override
    public void mergeData(Track entity, TrackDTO dto) {
        if (dto.hasNullOrEmptyAttributes())
            throw logicExceptionComponent.getExceptionEntityEmptyValues("Track");

        if (!entity.getTitle().equals(dto.getTitle()))
            entity.setTitle(dto.getTitle());

        if (!entity.getTrackDuration().equals(dto.getTrackDuration()))
            entity.setTrackDuration(dto.getTrackDuration());

    }

    public TrackDTO update(TrackDTO dto, Long albumId, Long trackId) {

        /**
         * Busco el Album en la base de datos, en caso que no exista, lanzara una  ExceptionEntityNotFound
         */
        Album albumByIdFromDB = albumRepository
                .findById(albumId)
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Album", albumId));

        /**
         * Busco el Track en la base de datos, en caso que no exista, lanzara una  ExceptionEntityNotFound
         */
        Track trackByIdFromDB = trackRepository
                .findById(trackId)
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Track", trackId));

        /**
         * Se le setea el album de la base de datos al track de la base de datos
         */
        trackByIdFromDB.setAlbum(albumByIdFromDB);

        /**
         * se realiza el merge de los datos del dto a los que se trajo en la base de datos.
         */
        mergeData(trackByIdFromDB, dto);

        /**
         * se procede a guardar los cambios en la base de datos.
         */
        trackRepository.save(trackByIdFromDB);

        /**
         * se convierte el entity a dto
         */
        TrackDTO trackUpdated = trackMapper.toDTO(trackByIdFromDB, context);

        /**
         * se entrega al controlador el dto con los cambios efectuados
         */
        return trackUpdated;

    }
}
