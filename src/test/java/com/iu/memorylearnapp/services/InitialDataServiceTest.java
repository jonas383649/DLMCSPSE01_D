package com.iu.memorylearnapp.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.repositories.CardSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.InputStream;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InitialDataServiceTest {

    @InjectMocks
    private InitialDataService service;

    @Mock
    private ResourceService resourceService;

    @Mock
    private CardSetRepository cardSetRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = mock(ObjectMapper.class);
        ReflectionTestUtils.setField(service, "objectMapper", objectMapper);
    }

    @Test
    public void testInitializeWhenNoCardSetsExist() throws Exception {
        final var inputStream = mock(InputStream.class);
        final var cardSets = List.of(new CardSet());

        when(resourceService.createInputStream(any())).thenReturn(inputStream);
        when(objectMapper.readValue(any(InputStream.class), any(TypeReference.class))).thenReturn(cardSets);
        when(cardSetRepository.count()).thenReturn(0L);

        service.initialize();

        verify(cardSetRepository).count();
        verify(cardSetRepository).saveAll(cardSets);
    }

    @Test
    public void testInitializeWhenCardSetsExist() throws Exception {
        when(cardSetRepository.count()).thenReturn(1L);

        service.initialize();

        verify(cardSetRepository).count();
        verify(cardSetRepository, never()).saveAll(anyList());
    }
}