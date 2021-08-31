package com.example.algamoney.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AlgaMoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageSource messageSource;

    private List<Erro> createErrorList(BindingResult br){

        List<Erro> errors = new ArrayList<>( br.getFieldErrors().size());
        for(FieldError fieldError : br.getFieldErrors()) {
            String userErrorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String devErrorMessage = fieldError.toString();
            errors.add(new Erro(userErrorMessage,devErrorMessage));
        }
        return errors;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagem = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDev = ex.getCause().toString();
        return handleExceptionInternal(ex, new Erro(mensagem,mensagemDev), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro>  errors = createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    public static class Erro {
        private String mensagemUsuario;
        private String mensagemDev;

        public Erro(String mensagemUsuario, String mensagemDev) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDev = mensagemDev;
        }

        public String getMensagemDev() {
            return mensagemDev;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }
    }
}
