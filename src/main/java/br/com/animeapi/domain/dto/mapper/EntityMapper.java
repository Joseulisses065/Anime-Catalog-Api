package br.com.animeapi.domain.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class EntityMapper {
    public  static  ModelMapper mapper = new ModelMapper();

    public static  <S,D> D mapObject(S source, Class<D> destinationClass ){
        return mapper.map(source, destinationClass);
    }
    public static  <S,D> List<D> mapListObject(List<S> list, Class<D> destinationClass){
        List<D> destinationList = new ArrayList<>();
        for (S s : list){
            destinationList.add(mapper.map(s,destinationClass));
        }
        return destinationList;
    }
    public static  <S,D> Page<D> mapPageObject(Page<S> list, Class<D> destinationClass){
        return list.map(s->mapper.map(s, destinationClass));
    }
}
