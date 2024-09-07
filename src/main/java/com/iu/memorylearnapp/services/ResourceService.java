package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.Data;
import com.iu.memorylearnapp.common.View;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;

/**
 * Service class responsible for resource management and loading in the application.
 *
 * <p>The {@link ResourceService} provides methods to create {@link FXMLLoader} instances
 * for loading FXML views, and to access resource files as input streams. It uses the
 * {@link ApplicationContext} to configure controllers for FXML files.</p>
 *
 * <p>Additionally, it provides utility methods for obtaining URLs to resources.</p>
 */
@Service
public class ResourceService {

    @Autowired
    private ApplicationContext context;

    /**
     * Creates an {@link FXMLLoader} instance configured with the given {@link View}.
     *
     * @param view the view to load
     * @return an {@link FXMLLoader} configured with the specified view
     */
    public FXMLLoader createLoader(final View view) {
        final var url = createUrl(view.toString());
        final var loader = new FXMLLoader(url);

        loader.setControllerFactory(context::getBean);

        return loader;
    }

    /**
     * Creates an input stream for the specified {@link Data} resource.
     *
     * @param data the data resource to access
     * @return an {@link InputStream} for the specified resource
     * @throws Exception if an error occurs while opening the stream
     */
    public InputStream createInputStream(final Data data) throws Exception {
        return createUrl(data.toString()).openStream();
    }

    /**
     * Creates a {@link URL} for the specified resource path.
     *
     * @param path the path to the resource
     * @return a {@link URL} pointing to the resource
     */
    public URL createUrl(final String path) {
        return getClass().getResource(path);
    }
}
