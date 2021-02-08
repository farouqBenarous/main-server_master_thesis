package com.database.repository;


import com.database.table.UserTbl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends CrudRepository<UserTbl, UUID> {

    Optional<UserTbl> findByName(String name);

    @Query(value = "SELECT users.* FROM users  WHERE lower(users.email)  = lower(:email)", nativeQuery = true)
    Optional<UserTbl> findByUsr(String email);

    @Query(value = "Select r.name from user_twentyx u join user_role  r on u.role_id = r.id  where lower(u.usr) = lower(:username)", nativeQuery = true)
    List<String> getUsersRole(String username);

    @Query(value = "select u.* from tenant  join user_tenant ut on tenant.id = ut.tenant_id join user_twentyx u on ut.user_id = u.id where tenant.name = :tenant", nativeQuery = true)
    List<UserTbl> findByNameAndUser(String tenant);
}