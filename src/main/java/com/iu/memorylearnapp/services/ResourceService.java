package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.Data;
import com.iu.memorylearnapp.common.View;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;

@Service
public class ResourceService {

    @Autowired
    private ApplicationContext context;

    public FXMLLoader createLoader(final View view) {
        final var url = createUrl(view.toString());
        final var loader = new FXMLLoader(url);

        loader.setControllerFactory(context::getBean);

        return loader;
    }

    public InputStream createInputStream(final Data data) throws Exception {
        return createUrl(data.toString()).openStream();
    }

    public URL createUrl(final String path) {
        return getClass().getResource(path);
    }
}
