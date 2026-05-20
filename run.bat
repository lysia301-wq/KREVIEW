@echo off
cd "C:\Users\Lysia\Documents\java demo"
C:\Users\Lysia\Downloads\apache-maven-3.9.15-bin\apache-maven-3.9.15\bin\mvn clean install -DskipTests
C:\Users\Lysia\Downloads\apache-maven-3.9.15-bin\apache-maven-3.9.15\bin\mvn exec:java -Dexec.mainClass="com.com.Main" -DskipTests
pause