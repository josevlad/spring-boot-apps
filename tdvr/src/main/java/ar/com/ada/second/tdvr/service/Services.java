package ar.com.ada.second.tdvr.service;

import java.util.List;

public interface Services<T> {

    T save(T dto);
    List<T> getAll();
    Boolean remove(Long id);
}
