FROM openjdk:21-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR para o diretório de trabalho
COPY target/smconsumer*.jar /app/smconsumer.jar

# Comando para iniciar o aplicativo
ENTRYPOINT ["java", "-jar", "smconsumer.jar"]

# Exponha a porta que o aplicativo usa
EXPOSE 8080