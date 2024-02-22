package org.example.api;

import org.example.exception.BadRequestException;
import org.example.exception.DuplicatedException;
import org.example.exception.NotFoundException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Connection {

    public String doGet(String url) throws Exception {
        System.out.println("URL solicitada: " + url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try (HttpClient client = HttpClient.newHttpClient()){
            HttpResponse<String> respuesta = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(respuesta.body());

            if (respuesta.statusCode() == 200) {
                return respuesta.body();
            } else if (respuesta.statusCode() == 404) {
                throw new NotFoundException();
            } else if(respuesta.statusCode()== 400){
                throw new BadRequestException();
            }else {
                throw new Exception("Error: " + respuesta.statusCode());
            }

        }

    }

    public void doPost(String body, String url) throws Exception {
        System.out.println("URL solicitada: " + url);
        //url= "localhost:8080/flights/a2022-01-01?date=2022-01-01/passenger";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .build();

        try (HttpClient client = HttpClient.newHttpClient()){
            HttpResponse<String> respuesta = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(respuesta.body());
            if(respuesta.statusCode()==201){
                System.out.println("Creado correctamente");
            }else if(respuesta.statusCode()==409){
                throw new DuplicatedException();
            }else if(respuesta.statusCode()==400){
                throw new BadRequestException();
            }else{
                throw new RuntimeException();
            }

        }

    }

    public String doUpdate(String body, String url) throws Exception {
        System.out.println("URL solicitada: " + url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .build();

        try (HttpClient client = HttpClient.newHttpClient()){
            HttpResponse<String> respuesta = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(respuesta.body());

            if (respuesta.statusCode() == 200) {
                return respuesta.body();
            } else if (respuesta.statusCode() == 404) {
                throw new NotFoundException();
            } else {
                throw new Exception("Error: " + respuesta.statusCode());
            }
        }

    }

    public String doDelete(String url) throws Exception {
        System.out.println("URL solicitada: " + url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try (HttpClient client = HttpClient.newHttpClient()){
            HttpResponse<String> respuesta = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(respuesta.body());

            if (respuesta.statusCode() == 200) {
                return respuesta.body();
            } else if (respuesta.statusCode() == 404) {
                throw new NotFoundException();
            } else {
                throw new Exception("Error: " + respuesta.statusCode());
            }
        }

    }
}
