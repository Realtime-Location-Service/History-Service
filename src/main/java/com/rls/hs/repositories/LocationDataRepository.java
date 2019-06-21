package com.rls.hs.repositories;

import com.rls.hs.models.LocationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationDataRepository extends JpaRepository<LocationData, Integer> {
    @Query("SELECT l from LocationData l where l.userID in (:someUserIDs) AND LOWER(l.domain) = LOWER(:theDomain)")
    List<LocationData> find (String theDomain, String[] someUserIDs);
}
