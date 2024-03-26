# Gestion des dendrées alimentaires

Ce projet est une application de gestion de denrées alimentaires conçue pour faciliter le suivi et la gestion des stocks alimentaires. 
La connexion à la base de données est réalisée en utilisant JDBC. 
L'interface graphique a été développée en utilisant Swing.

## comment se connecter à la base de données ?
il suffit juste de modifier la classe DBMSConnection
par exemple (oracle) :
```
        private static String url ="@{<ipAddress>|<hostName>}:<port>:<SID>";

	private static String driver ="oracle.jdbc.driver.OracleDriver";

	private static String user ="system";
 
	private static String password="system";
```
ce lien ci dessus peut vous aider à paramétrer la connexion à votre base de données
https://docs.bmc.com/docs/TrueSightOrchestrationPlatform/222/database-urls-and-drivers-1109345280.html

## interface graphique

![image](https://github.com/alakerkeni/Gestionnaire-de-Denr-es-Alimentaires-avec-JDBC-et-Swing/assets/132003791/1c05b219-059c-4501-9e79-e114d81fe0e3)

![image](https://github.com/alakerkeni/Gestionnaire-de-Denr-es-Alimentaires-avec-JDBC-et-Swing/assets/132003791/76523224-9aba-4528-8ef3-196cd4fb2298)

