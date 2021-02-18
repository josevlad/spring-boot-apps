package ar.com.ada.second.tdvr.service;


import ar.com.ada.second.tdvr.model.dto.ArtistDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService implements Services<ArtistDTO> {

    @Override
    public ArtistDTO save(ArtistDTO dto) {
        return null;
    }

    @Override
    public List<ArtistDTO> getAll() {
        return null;
    }

    @Override
    public Boolean remove(Long id) {
        return null;
    }
}
