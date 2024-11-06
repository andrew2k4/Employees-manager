# Utilise une image de base avec Java 17
FROM openjdk:17

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie tout le contenu du projet dans le répertoire de travail
COPY . .

# Exécute la commande Maven pour construire l'application et générer le JAR
RUN ./mvnw clean install -DskipTests


ARG JAR_FILE=target/*.jar

# Copie le JAR généré dans l'image Docker sous le nom app.jar
RUN cp ${JAR_FILE} app.jar

# Expose le port de l'application
EXPOSE 8090

# Lance l'application avec le fichier JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
