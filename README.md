# TP2 SOA — CalculatriceWS (Jakarta XML Web Services)

## Project Overview
This project implements a SOAP Web Service using Jakarta XML Web Services (JAX-WS) and Maven. The service, named `CalculatriceWS`, exposes basic mathematical operations: addition, subtraction, multiplication, and division.

## Prerequisites
Before running the project, ensure you have the following installed:
- **Java JDK** (version 11 or higher)
- **IntelliJ IDEA**
- **Maven** (usually bundled with IntelliJ)
- **SoapUI** (Open Source version) - Required for testing the service. Download it from [https://www.soapui.org/downloads/soapui/](https://www.soapui.org/downloads/soapui/).

## Project Structure

```text
CalculatriceWS/
├── .idea/
├── .mvn/
├── src/
│   └── main/
│       └── java/
│           ├── serveur/
│           │   └── ServeurJWS.java
│           └── service/
│               ├── Calculatrice.java
│               └── ICalculatrice.java
├── pom.xml
└── README.md
```

## File Descriptions

| File | Role |
|---|---|
| `pom.xml` | Maven configuration containing Jakarta XML WS dependencies and the `exec-maven-plugin` to run the server easily. |
| `service/ICalculatrice.java` | The service interface defining the operations, annotated with `@WebService`, `@WebMethod`, and `@WebParam`. |
| `service/Calculatrice.java` | The implementation of the interface, annotated with `@WebService(endpointInterface = ...)`. |
| `serveur/ServeurJWS.java` | The main class that publishes the web service using `Endpoint.publish()`. |

## How to Run the Service

1. Open the project in IntelliJ IDEA.
2. Ensure Maven dependencies are downloaded. Open the **Maven** tool window on the right side and click the **Reload All Maven Projects** button (circular arrows).
3. Open `src/main/java/serveur/ServeurJWS.java`.
4. Click the green **Run** button (or right-click and select **Run 'ServeurJWS.main()'**).
5. Check the IntelliJ console. You should see the following message:
   ```text
   Service Web publié à l'adresse : http://localhost:8080/CalculatriceWS?wsdl
   ```
6. **Important:** Leave the server running. Do not click the red Stop button.

## Testing with SoapUI

To verify that the service works, follow these steps using SoapUI:

1. Open the **SoapUI** application.
2. Click on the **SOAP** button in the top-left corner to create a new SOAP project.
3. In the **Initial WSDL** field, paste the following URL:
   ```text
   http://localhost:8080/CalculatriceWS?wsdl
   ```
4. Click **OK**. SoapUI will analyze the WSDL and generate the interface on the left panel.
5. Expand the project tree: `CalculatriceServiceSoapBinding` -> `somme`.
6. Double-click on **Request 1**. A request template will open in the editor.
7. Replace the question marks with numbers, for example:
   ```xml
   <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service/">
      <soapenv:Header/>
      <soapenv:Body>
         <ser:somme>
            <a>10</a>
            <b>5</b>
         </ser:somme>
      </soapenv:Body>
   </soapenv:Envelope>
   ```
8. Click the green **Play** button (Submit request to specified URL) at the top left of the request window.
9. On the right side, in the **XML** response window, verify the result:
   ```xml
   <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
      <soap:Body>
         <ns2:sommeResponse xmlns:ns2="http://service/">
            <return>15.0</return>
         </ns2:sommeResponse>
      </soap:Body>
   </soap:Envelope>
   ```
10. You can repeat this process for `soustraction`, `multiplication`, and `division` to test all operations.

## Exiting the Service
To stop the server, click the red **Stop** button in the IntelliJ console window.