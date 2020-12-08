package com.spring.timecounter.repos;

import com.spring.timecounter.models.Message;
import com.spring.timecounter.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByForeman(User foreman);

    @Query(value = "SELECT TOP (1000) [id]  ,[foreman_id]\n" +
            "      ,[worker_id], shift, time\n" +
             "  FROM [worker_counter]\n" +
            "  WHERE foreman_id=?1 and shift=?2",nativeQuery = true)
    List<Message> findByUserandSh(Long userId, String shift);


    Message findAllById(int id);
}
