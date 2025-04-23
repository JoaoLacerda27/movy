# Etapa de construção do Maven (alterar para Gradle)
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar todos os arquivos, incluindo o gradlew e o build.gradle
COPY . .

# Executar o build com Gradle
RUN ./gradlew build -x test

# Etapa de execução
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar os artefatos construídos do Gradle (no diretório build/libs)
COPY --from=builder /app/build/libs/movy-be-*.jar app.jar

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
