package com.spring.timecounter.repos;

import com.spring.timecounter.models.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepo extends CrudRepository<Worker, Long> {

    Worker findByUid(Long uid);
}
