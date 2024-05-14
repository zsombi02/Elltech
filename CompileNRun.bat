@echo off
rmdir .\compiled /Q /S
mkdir .\compiled
javac -d compiled --source-path .\ .\*.java
java -cp compiled Graphic %*
