# TP2 SOA — CalculatriceWS (Jakarta XML Web Services)

## Aperçu du projet
Ce projet met en œuvre un service web SOAP en utilisant Jakarta XML Web Services (JAX-WS) et Maven. Le service, nommé `CalculatriceWS`, expose des opérations mathématiques de base : addition, soustraction, multiplication et division.

## Prérequis
Avant de lancer le projet, assurez-vous d'avoir installé les éléments suivants :
- **Java JDK** (version 11 ou supérieure)
- **IntelliJ IDEA**
- **Maven** (généralement inclus avec IntelliJ)
- **SoapUI** (version Open Source) - Nécessaire pour tester le service. Téléchargez-le depuis [https://www.soapui.org/downloads/soapui/](https://www.soapui.org/downloads/soapui/).

## Structure du projet

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

## Description des fichiers

| Fichier | Rôle |
|---|---|
| `pom.xml` | Configuration Maven contenant les dépendances Jakarta XML WS et le plugin `exec-maven-plugin` pour faciliter le lancement du serveur. |
| `service/ICalculatrice.java` | L'interface du service définissant les opérations, annotée avec `@WebService`, `@WebMethod` et `@WebParam`. |
| `service/Calculatrice.java` | L'implémentation de l'interface, annotée avec `@WebService(endpointInterface = ...)`. |
| `serveur/ServeurJWS.java` | La classe principale qui publie le service web en utilisant `Endpoint.publish()`. |

## Comment exécuter le service

1. Ouvrez le projet dans IntelliJ IDEA.
2. Assurez-vous que les dépendances Maven sont téléchargées. Ouvrez la fenêtre d'outils **Maven** sur le côté droit et cliquez sur le bouton **Reload All Maven Projects** (flèches circulaires).
3. Ouvrez le fichier `src/main/java/serveur/ServeurJWS.java`.
4. Cliquez sur le bouton vert **Run** (ou faites un clic droit et sélectionnez **Run 'ServeurJWS.main()'**).
5. Vérifiez la console IntelliJ. Vous devriez voir le message suivant :
   ```text
   Service Web publié à l'adresse : http://localhost:8080/CalculatriceWS?wsdl
   ```
6. **Important :** Laissez le serveur en cours d'exécution. Ne cliquez pas sur le bouton rouge Stop.

## Test avec SoapUI

Pour vérifier que le service fonctionne, suivez ces étapes en utilisant SoapUI :

1. Ouvrez l'application **SoapUI**.
2. Cliquez sur le bouton **SOAP** dans le coin supérieur gauche pour créer un nouveau projet SOAP.
3. Dans le champ **Initial WSDL**, collez l'URL suivante :
   ```text
   http://localhost:8080/CalculatriceWS?wsdl
   ```
4. Cliquez sur **OK**. SoapUI va analyser le WSDL et générer l'interface dans le panneau de gauche.
5. Développez l'arborescence du projet : `CalculatriceServiceSoapBinding` -> `somme`.
6. Double-cliquez sur **Request 1**. Un modèle de requête va s'ouvrir dans l'éditeur.
7. Remplacez les points d'interrogation par des nombres, par exemple :
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
8. Cliquez sur le bouton vert **Play** (Submit request to specified URL) en haut à gauche de la fenêtre de requête.
9. Sur le côté droit, dans la fenêtre de réponse **XML**, vérifiez le résultat :
   ```xml
   <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
      <soap:Body>
         <ns2:sommeResponse xmlns:ns2="http://service/">
            <return>15.0</return>
         </ns2:sommeResponse>
      </soap:Body>
   </soap:Envelope>
   ```
10. Vous pouvez répéter ce processus pour `soustraction`, `multiplication` et `division` afin de tester toutes les opérations.

## Arrêter le service
Pour arrêter le serveur, cliquez sur le bouton rouge **Stop** dans la fenêtre de console d'IntelliJ.