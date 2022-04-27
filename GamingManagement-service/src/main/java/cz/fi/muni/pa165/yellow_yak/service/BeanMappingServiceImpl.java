package cz.fi.muni.pa165.yellow_yak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation for dozer mapper
 *
 * @author matho
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    @Autowired
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return (u == null) ? null : dozer.map(u, mapToClass);
    }

    public Mapper getMapper() {
        return dozer;
    }
}
