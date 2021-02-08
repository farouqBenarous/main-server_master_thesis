package com.database.repository;

import com.database.table.PasswordResetTokenTbl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenTbl, UUID> {

    @Query(value = "FROM PasswordResetTokenTbl p LEFT JOIN FETCH p.user where p.token = :token")
    Optional<PasswordResetTokenTbl> findByTokenWithUser(@Param("token") String token);
}
