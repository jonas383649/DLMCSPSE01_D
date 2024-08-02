package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.ResourcePath;
import com.iu.memorylearnapp.events.StageReadyEvent;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StageServiceTest extends ApplicationTest {

    @Mock
    private ApplicationContext context;

    @Mock
    private Stage stage;

    @InjectMocks
    private StageService service;

    @BeforeEach
    void setUp() {
        when(context.getBean(any(Class.class))).thenAnswer(invocation -> mock((Class<?>) invocation.getArgument(0)));
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
    void testLoad() {
        service.load(ResourcePath.MENU_VIEW);

        verify(stage).setScene(any());
        verify(stage).show();
    }
}
