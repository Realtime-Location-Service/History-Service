package com.rls.hs.repositories;

import com.rls.hs.models.DBLocationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LocationDataRepository extends JpaRepository<DBLocationData, Integer> {
    @Query("SELECT l from DBLocationData l where l.userId in (:someUserIDs) AND LOWER(l.domain) = LOWER(:theDomain)")
    List<DBLocationData> find (String theDomain, String[] someUserIDs);

    @Query("Select l from DBLocationData l where l.userId=:theUserId AND LOWER(l.domain) = LOWER(:theDomain) AND l.clientTimestampUtc >= :theStartTimestamp AND l.clientTimestampUtc <= :theEndTimestamp ORDER BY clientTimestampUtc ASC")
    List<DBLocationData> findWithDateRange(String theDomain, String theUserId, Date theStartTimestamp, Date theEndTimestamp);
}
