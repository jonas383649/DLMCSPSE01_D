package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.Data;
import com.iu.memorylearnapp.common.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest extends ApplicationTest {

    @InjectMocks
    private ResourceService service;

    @Mock
    private ApplicationContext context;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateLoader() {
        final var view = mock(View.class);
        final var loader = service.createLoader(view);
        loader.getControllerFactory().call(Class.class);
        verify(context).getBean(Class.class);
        assertNotNull(loader);
    }

    @Test
    public void testCreateInputStream() throws Exception {
        final var inputStream = service.createInputStream(Data.INITIAL);
        assertNotNull(inputStream);
    }

    @Test
    public void testCreateUrl() {
        final var result = service.createUrl(Data.INITIAL.toString());
        assertNotNull(result);
    }
}