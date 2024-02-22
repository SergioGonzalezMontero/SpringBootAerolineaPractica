package org.example.tools;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Tools {

        // Método para comprobar si una cadena es un DNI válido
        public boolean esDNIValido(String dni) {
            // Verificar que la cadena tiene longitud 9 (8 dígitos + 1 letra)
            if (dni == null || dni.length() != 9)
                return false;

            // Extraer los dígitos y la letra
            String digitos = dni.substring(0, 8);
            char letra = Character.toUpperCase(dni.charAt(8));

            // Verificar si los primeros 8 caracteres son dígitos
            for (int i = 0; i < digitos.length(); i++) {
                if (!Character.isDigit(digitos.charAt(i)))
                    return false;
            }

            // Calcular la letra correspondiente a los dígitos
            char letraCalculada = calcularLetra(digitos);

            // Comparar la letra calculada con la letra recibida
            return letra == letraCalculada;
        }

        // Método para calcular la letra del DNI a partir de los dígitos
        private char calcularLetra(String digitos) {
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int resto = Integer.parseInt(digitos) % 23;
            return letras.charAt(resto);
        }

    // Método para comprobar si una cadena es una fecha válida en el formato yyyy-mm-dd
    public boolean esFechaValida(String fechaStr) {
        try {
            // Intentar analizar la cadena como una fecha
            LocalDate fecha = LocalDate.parse(fechaStr);

            // La fecha se analizó correctamente, por lo que es válida
            return true;
        } catch (DateTimeParseException e) {
            // La cadena no pudo ser analizada como una fecha en el formato esperado
            return false;
        }
    }

    // Método para comprobar si una cadena es un número mayor que 0 y menor que 500
    public static boolean esNumeroValido(String cadena) {
        try {
            int numero = Integer.parseInt(cadena);
            return numero > 0 && numero < 500;
        } catch (NumberFormatException e) {
            // Si la cadena no se puede convertir a un número, entonces no es un número válido
            return false;
        }
    }



}
