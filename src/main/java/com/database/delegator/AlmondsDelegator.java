package com.database.delegator;


import com.database.converter.AlmondConverter;
import com.database.repository.AlmondsRepository;
import com.database.table.AlmondsTbl;
import com.entity.entities.Almond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class AlmondsDelegator {

    @Autowired
    AlmondsRepository almondsRepository;


    @Transactional(readOnly = true)
    public List<Almond> getAlmonds() {
        List<AlmondsTbl> almonds = almondsRepository.getAlmonds();
        if (almonds.isEmpty())
            return new ArrayList<>(0);
        return almonds.stream().map(AlmondConverter.convertToEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Almond getAlmondByIndex(String index) {
        Optional<AlmondsTbl> almond = almondsRepository.findByIndex(index);
        return almond.map(AlmondConverter.convertToEntity::apply).orElse(null);
    }

    @Transactional(readOnly = true)
    public Almond getAlmondByTitle(String title) {
        Optional<AlmondsTbl> almond = almondsRepository.findByIndex(title);
        return almond.map(AlmondConverter.convertToEntity::apply).orElse(null);
    }


    @Transactional()
    public Boolean deleteAllAlmonds() {
        almondsRepository.deleteAll();
        List<AlmondsTbl> almonds = almondsRepository.getAlmonds();
        return almonds.size() < 1;
    }

    @Transactional()
    public Boolean initDataTable(List<Almond> almonds) {
        List<AlmondsTbl> toSave = almonds.stream().map(AlmondConverter.convertToDBO::apply).collect(Collectors.toList());
        almondsRepository.saveAll(toSave);
        return true;
    }
}
