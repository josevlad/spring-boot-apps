package ar.com.ada.second.tdvr.service;

import java.util.List;

public interface Services<T> {

    /**
     *
     * @param dto
     * @return
     */
    T createNew(T dto);

    /**
     *
     * @return
     */
    List<T> getAll();

    /**
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     *
     * @param dto
     * @param id
     * @return
     */
    T update(T dto, Long id);

    /**
     *
     * @param id
     */
    void remove(Long id);

}
