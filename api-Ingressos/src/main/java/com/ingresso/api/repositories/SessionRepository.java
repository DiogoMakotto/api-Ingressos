package com.ingresso.api.repositories;

import com.ingresso.api.domain.session.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    @Query("SELECT e FROM Session e LEFT JOIN FETCH e.address a WHERE e.date >= :currentDate")
    public Page<Session> findUpcomingSessions(@Param("currentDate") Date currentDate, Pageable pageable);

    @Query("SELECT e FROM Session e LEFT JOIN e.address a " +
            "WHERE (:city = '' OR a.city LIKE %:city%) " +
            "AND (:uf = '' OR a.uf LIKE %:uf%) " +
            "AND (e.date >= :startDate AND e.date <= :endDate)")
    Page<Session> findFilteredSessions(@Param("city") String city,
                                       @Param("uf") String uf,
                                       @Param("startDate") Date startDate,
                                       @Param("endDate") Date endDate,
                                       Pageable pageable);

    @Query("SELECT e FROM Session e LEFT JOIN e.address a " +
            "WHERE (:title = '' OR e.title LIKE %:title%) ")
    List<Session> findSessionByTitle(@Param("title") String title);


}
