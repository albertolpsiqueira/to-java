# Estágio de Build (usando Maven)
FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
# Baixa as dependências e prepara o cache
RUN mvn dependency:go-offline
COPY src ./src
# Constrói o JAR
RUN mvn package -DskipTests

# Estágio de Execução (para um JAR executável)
FROM amazoncorretto:21
WORKDIR /app
# Argumentos de execução (se necessário)
ARG JAR_FILE=target/*.jar
# Copia o JAR do estágio de build
COPY --from=build /app/${JAR_FILE} app.jar
# Define a variável de ambiente para a porta (se não estiver no código)
ENV PORT=8080
EXPOSE 8080
# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
