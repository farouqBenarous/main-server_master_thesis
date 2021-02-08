package com.database.converter;

import com.database.table.UserTbl;
import com.entity.entities.User;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.function.Function;

public class UserConverter {

    private static MapperFacade mapperFacade;

    static {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(User.class, UserTbl.class)
                .byDefault()
                .register();
        mapperFacade = factory.getMapperFacade();
    }

    final public static Function<UserTbl, User> convertToEntity = userTbl -> mapperFacade.map(userTbl, User.class);

    final public static Function<User, UserTbl> convertToDBO = user -> mapperFacade.map(user, UserTbl.class);
}
