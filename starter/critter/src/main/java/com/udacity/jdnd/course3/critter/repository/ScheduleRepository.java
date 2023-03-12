package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.dto.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
