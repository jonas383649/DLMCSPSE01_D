package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.Data;
import com.iu.memorylearnapp.common.View;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ResourceService {

    @Autowired
    private ApplicationContext context;

    public FXMLLoader createLoader(final View view) {
        final var loader = new FXMLLoader(getClass().getResource(view.toString()));
        loader.setControllerFactory(context::getBean);
        return loader;
    }

    public InputStream createInputStream(final Data data) throws Exception {
        return new ClassPathResource(data.toString()).getInputStream();
    }
}
