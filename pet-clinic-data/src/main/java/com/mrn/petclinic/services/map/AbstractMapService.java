package com.mrn.petclinic.services.map;

import com.mrn.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return  new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        // if obj is not == null
        if(object != null) {
            if(object.getId() == null) { // if id is == null
                object.setId(getNextId()); // set the id with the next incremented id (see getNextId())
            }
            map.put(object.getId(), object); // put the object id and the object into the map
        } else {
            throw new RuntimeException("Object cannot be null");
        }
        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void deleteByObject(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() {
        Long nextId = null; // make a long variable null
        try {
            nextId = Collections.max(map.keySet()) + 1; // try to catch the error
        }catch (NoSuchElementException e) {
            nextId = 1L; // throws nosuchelem and give the initialize variable with 1L
        }
        return nextId;
    }
}
