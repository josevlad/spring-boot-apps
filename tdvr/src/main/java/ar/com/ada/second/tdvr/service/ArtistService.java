package ar.com.ada.second.tdvr.service;


import ar.com.ada.second.tdvr.model.dto.ArtistDTO;
import ar.com.ada.second.tdvr.model.entity.Artist;
import ar.com.ada.second.tdvr.model.mapper.ArtistMapper;
import ar.com.ada.second.tdvr.model.mapper.AvoidingMappingContext;
import ar.com.ada.second.tdvr.model.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService implements Services<ArtistDTO> {

    private ArtistMapper artistMapper = ArtistMapper.MAPPER;

    @Autowired
    private AvoidingMappingContext context;

    @Autowired
    private ArtistRepository artistRepository;


    @Override
    public ArtistDTO createNew(ArtistDTO dto) {
        // debo hacer la conversion de dto a entity
        Artist artist = artistMapper.toEntity(dto, context);

        // se le pide al repository que cuarde la entidad
        artistRepository.save(artist);

        // convierte a dto las instancia artist con el id que le asigno MySQL
        ArtistDTO artistSaved = artistMapper.toDTO(artist, context);

        // le entrego al controlador el dto con el id
        return artistSaved;
    }

    @Override
    public List<ArtistDTO> getAll() {

        // llamar al repositorio y pedirle que haga la consulta a la BD de todos los registro de de esa entidad
        List<Artist> artistList = artistRepository.findAll();// => select * from

        // convertir esa lista de DAO a una lista de DTO
        List<ArtistDTO> artistDTOS = artistMapper.toDTO(artistList, context);

        /*
        ArrayList<ArtistDTO> artistDTOS = new ArrayList<>();

        artistList
                .stream()
                .peek(artist -> {
                    ArtistDTO artistDTO = new ArtistDTO(artist.getId(), artist.getName());
                    artistDtoList.add(artistDTO);
                });
         */

        // retorno la lista resultante de la conversion
        return artistDTOS;
    }

    @Override
    public ArtistDTO getById(Long id) {
        return null;
    }

    @Override
    public ArtistDTO update(ArtistDTO dto, Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
