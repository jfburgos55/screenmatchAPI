package com.alura.screenmatch.excepcion;

public class ErrorConversionDuracion extends RuntimeException {
    private String mensaje;

    public ErrorConversionDuracion(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}