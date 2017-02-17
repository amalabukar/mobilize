package com.theironyard.services;

import com.theironyard.entities.Cause;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by amalabukar on 2/3/17.
 */
public interface CauseRepository  extends CrudRepository<Cause, Integer> {
    List<Cause> findByCategory (String category);
}
