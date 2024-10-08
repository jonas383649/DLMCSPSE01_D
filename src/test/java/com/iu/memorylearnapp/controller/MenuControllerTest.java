package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.repositories.CardSetRepository;
import com.iu.memorylearnapp.services.StageService;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest extends ApplicationTest {

    @InjectMocks
    private MenuController controller;

    @Mock
    private CardSetRepository cardSetRepository;

    @Mock
    private EditorController editorController;

    @Mock
    private StageService stageService;

    private ListView<CardSet> cardSetListView;

    @BeforeEach
    void setUp() {
        cardSetListView = (ListView<CardSet>) mock(ListView.class);
        ReflectionTestUtils.setField(controller, "cardSetListView", cardSetListView);
    }

    @Test
    public void testInitialize() {
        controller.initialize();

        verify(cardSetListView).setCellFactory(any());
        verify(cardSetListView).setItems(any());
        verify(cardSetRepository).findAll();
    }

    @Test
    public void testCreateCardSet() {
        controller.createCardSet();
        verify(editorController).setCardSet(any());
        verify(stageService).show(any());
    }
}
