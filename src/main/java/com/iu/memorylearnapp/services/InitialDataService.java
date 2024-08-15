package com.iu.memorylearnapp.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iu.memorylearnapp.common.Data;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.repositories.CardSetRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitialDataService {

    private final ObjectMapper objectMapper;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CardSetRepository cardSetRepository;

    public InitialDataService() {
        objectMapper = new ObjectMapper();
    }

    @PostConstruct
    public void initialize() throws Exception {
        if (cardSetRepository.count() == 0) {
            loadCardSetData();
        }
    }

    private void loadCardSetData() throws Exception {
        final var inputStream = resourceService.createInputStream(Data.INITIAL_DATA);
        final var cardSets = objectMapper.readValue(inputStream, new TypeReference<List<CardSet>>() {});

        cardSetRepository.saveAll(cardSets);
    }
}
