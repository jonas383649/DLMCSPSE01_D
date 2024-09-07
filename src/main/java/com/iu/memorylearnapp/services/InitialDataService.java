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

/**
 * Service class responsible for initializing the application data.
 *
 * <p>The {@link InitialDataService} checks if {@link CardSet} entities exist in the
 * database upon application startup. If none are found, it loads initial data
 * from a predefined resource and saves it to the database.</p>
 */
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

    /**
     * Initializes the service after the bean's properties have been set.
     *
     * <p>Checks if {@link CardSet} entities are present in the repository.
     * If not, loads initial card set data from the resource and saves it to the repository.
     *
     * @throws Exception if an error occurs while loading or processing the data.
     */
    @PostConstruct
    public void initialize() throws Exception {
        if (cardSetRepository.count() == 0) {
            loadCardSetData();
        }
    }

    private void loadCardSetData() throws Exception {
        final var inputStream = resourceService.createInputStream(Data.INITIAL);
        final var cardSets = objectMapper.readValue(inputStream, new TypeReference<List<CardSet>>() {});

        cardSetRepository.saveAll(cardSets);
    }
}
