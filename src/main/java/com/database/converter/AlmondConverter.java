package com.database.converter;

import com.database.table.AlmondsTbl;
import com.entity.entities.Almond;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.function.Function;

public class AlmondConverter {

    private static MapperFacade mapperFacade;

    static {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Almond.class, AlmondsTbl.class)
                .byDefault()
                .register();
        mapperFacade = factory.getMapperFacade();
    }

    final public static Function<AlmondsTbl, Almond> convertToEntity = almondsTbl -> mapperFacade.map(almondsTbl, Almond.class);

    final public static Function<Almond, AlmondsTbl> convertToDBO = almond -> mapperFacade.map(almond, AlmondsTbl.class);
}
