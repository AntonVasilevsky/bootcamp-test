package com.example.bootcamp.service;

import java.util.List;

public interface DtoConverter<E,D> {
    E toEntity(D d);
    D toDto(E e);
    List<E> manyToEntity(List<D> dtoList);
    List<D> manyToDto(List<E> entityList);

}
