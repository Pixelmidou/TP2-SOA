package serveur;

import jakarta.xml.ws.Endpoint;
import service.Calculatrice;

public class ServeurJWS {
    public static void main(String[] args) {
        String url = "http://localhost:8080/CalculatriceWS";
        Endpoint.publish(url, new Calculatrice());

        System.out.println("Service Web publié à l'adresse : " + url + "?wsdl");
    }
}