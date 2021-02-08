package com.database.repository;


import com.database.table.AlmondsTbl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLClientInfoException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface AlmondsRepository extends CrudRepository<AlmondsTbl, UUID> {

    Optional<AlmondsTbl> findByTitle(String title);

    Optional<AlmondsTbl> findByIndex(String index);

    @Query(value = "SELECT al.* FROM almonds al", nativeQuery = true)
    List<AlmondsTbl> getAlmonds();

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM almonds", nativeQuery = true)
    void deleteAll();
}