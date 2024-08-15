package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.events.StageReadyEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StageServiceTest extends ApplicationTest {

    @InjectMocks
    private StageService service;

    @Mock
    private ResourceService resourceService;

    private Stage stage;

    @BeforeEach
    void setUp() throws Exception {
        final var loader = mock(FXMLLoader.class);
        final var parent = mock(Parent.class);

        stage = mock(Stage.class);

        when(resourceService.createLoader(any())).thenReturn(loader);
        when(loader.load()).thenReturn(parent);
        when(parent.getStyleClass()).thenReturn(mock(ObservableList.class));

        ReflectionTestUtils.setField(service, "stage", stage);
    }

    @Test
    void testOnApplicationEvent() {
        final var event = new StageReadyEvent(stage);

        service.onApplicationEvent(event);

        verify(stage).setTitle(anyString());
        verify(stage).setScene(any());
        verify(stage).show();
    }

    @Test
    void testShow() throws Exception {
        service.show(View.MENU_VIEW);

        verify(stage).setScene(any());
        verify(stage).show();
    }
}
