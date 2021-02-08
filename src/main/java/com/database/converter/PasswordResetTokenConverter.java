package com.database.converter;

import com.database.table.PasswordResetTokenTbl;
import com.entity.entities.PasswordResetToken;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.function.Function;

public class PasswordResetTokenConverter {


    private static MapperFacade mapperFacade;

    static {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(PasswordResetToken.class, PasswordResetTokenTbl.class)
                .byDefault()
                .register();
        mapperFacade = factory.getMapperFacade();
    }

    final public static Function<PasswordResetTokenTbl, PasswordResetToken> convertToEntity = tenantTbl -> mapperFacade.map(tenantTbl, PasswordResetToken.class);

    final public static Function<PasswordResetToken, PasswordResetTokenTbl> convertToDBO = tenant -> mapperFacade.map(tenant, PasswordResetTokenTbl.class);


}
