package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.events.StageReadyEvent;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StageServiceTest extends ApplicationTest {

    @InjectMocks
    private StageService service;

    @Mock
    private ResourceService resourceService;

    private Parent parent;

    private Scene scene;

    private Stage stage;

    @BeforeEach
    public void setUp() throws Exception {
        final var loader = mock(FXMLLoader.class);

        parent = mock(Parent.class);
        scene = mock(Scene.class);
        stage = mock(Stage.class);

        when(loader.load()).thenReturn(parent);
        when(resourceService.createLoader(any())).thenReturn(loader);

        ReflectionTestUtils.setField(service, "parent", parent);
        ReflectionTestUtils.setField(service, "scene", scene);
        ReflectionTestUtils.setField(service, "stage", stage);
    }

    @Test
    public void testOnApplicationEvent() {
        final var event = new StageReadyEvent(stage);

        when(resourceService.createUrl(anyString())).thenReturn(mock(URL.class));
        when(parent.getStyleClass()).thenReturn(mock(ObservableList.class));

        service.onApplicationEvent(event);

        verify(stage).setTitle(anyString());
        verify(stage).setHeight(anyDouble());
        verify(stage).setWidth(anyDouble());
        verify(stage).show();
    }

    @Test
    public void testShow() {
        when(resourceService.createUrl(anyString())).thenReturn(mock(URL.class));
        when(parent.getStyleClass()).thenReturn(mock(ObservableList.class));

        service.show(View.MENU);

        verify(stage).setScene(any());
        verify(stage).show();
    }

    @Test
    public void testShowPopover() {
        when(scene.widthProperty()).thenReturn(new SimpleDoubleProperty());
        when(scene.heightProperty()).thenReturn(new SimpleDoubleProperty());

        service.showPopover(View.MENU);

        verify(scene).setRoot(any());
    }
}
