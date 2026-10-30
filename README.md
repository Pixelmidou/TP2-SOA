# TP2 SOA — CalculatriceWS (Jakarta XML Web Services)

## Domaine choisi
Création d'un service web SOAP exposant des opérations mathématiques de base (somme, soustraction, multiplication, division).

## Arborescence du projet

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
| `pom.xml` | Configuration Maven avec les dépendances Jakarta XML WS. |
| `service/ICalculatrice.java` | Interface du service avec les annotations `@WebService`, `@WebMethod`, `@WebParam`. |
| `service/Calculatrice.java` | Implémentation de l'interface avec `@WebService(endpointInterface=...)`. |
| `serveur/ServeurJWS.java` | Classe principale qui publie le service via `Endpoint.publish`. |

## Activités réalisées

1. **Activité 1** : Projet Maven configuré avec les dépendances Jakarta.
2. **Activité 2** : Interface et classe métier créées. *(Note : j'ai implémenté les 4 méthodes d'un coup, mais pour suivre le TP, tu peux tester uniquement `somme` d'abord).*
3. **Activité 3** : Annotations ajoutées (`@WebService`, `@WebMethod`, `@WebParam`).
4. **Activité 4** : Classe `ServeurJWS` publiée sur le port 8080.
5. **Activité 5** : Test avec SOAPUI.

## Comment tester avec SOAPUI

1. Lance `ServeurJWS.java` dans IntelliJ. Le service tourne en arrière-plan.
2. Ouvre ton navigateur et va à l'adresse : `http://localhost:8080/CalculatriceWS?wsdl` (Tu verras le WSDL généré, c'est la preuve que le service fonctionne).
3. Ouvre **SoapUI**.
4. Clique sur **SOAP** (pour créer un nouveau projet SOAP).
5. Dans **Initial WSDL**, colle : `http://localhost:8080/CalculatriceWS?wsdl`
6. Clique sur **OK**. SoapUI va générer toutes les requêtes possibles.
7. Dans l'arborescence à gauche, déplie `CalculatriceServiceSoapBinding` -> `somme`.
8. Double-clique sur `Request 1`. Remplace les `?` par tes nombres, par exemple :
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
9. Clique sur le bouton vert **Play** (Submit request to specified URL).
10. Tu verras la réponse SOAP contenant `<return>15.0</return>`.
11. Répète l'opération pour `soustraction`, `multiplication` et `division`.