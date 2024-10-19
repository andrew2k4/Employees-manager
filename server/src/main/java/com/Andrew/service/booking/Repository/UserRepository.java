package com.Andrew.service.booking.Repository;

import com.Andrew.service.booking.dto.userDtos.UserDashboardDto;
import com.Andrew.service.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstByEmail(String email);


    @Query("SELECT u FROM User u JOIN FETCH u.tasks t WHERE t.addedTime BETWEEN :startDate AND :endDate")
    User findUsersWithTasksInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT u FROM User u JOIN u.tasks t WHERE t.addedTime >= :startDate AND t.addedTime <= :endDate")
    List<User> findUsersWithTasks(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);




}
