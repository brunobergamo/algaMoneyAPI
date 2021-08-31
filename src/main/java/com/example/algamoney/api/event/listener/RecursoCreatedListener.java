package com.example.algamoney.api.event.listener;

import com.example.algamoney.api.event.RecursoCreatedEvent;
import javafx.application.Application;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCreatedListener implements ApplicationListener<RecursoCreatedEvent> {

    @Override
    public void onApplicationEvent(RecursoCreatedEvent event) {
        HttpServletResponse response = event.getResponse();
        Long id = event.getId();

        URI uri = getUri(id);
        response.setHeader("Location", uri.toASCIIString());
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(id).toUri();
    }
}
