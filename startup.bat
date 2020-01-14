@echo off
rem set JAVA_HOME=D:\xlg\jdk1.8
rem set CLASSPATH=.;%JAVA_HONG%\lib\dt.jar;%JAVA_HONE%\lib\tools.jar
rem set PATH=%JAVA_HOME%\bin;
rem java -jar wristband-1.0-SNAPSHOT.jar


@echo off
rem set JAVA_HOME=D:\xlg\jdk1.8
rem set CLASSPATH=.;%JAVA_HONG%\lib\dt.jar;%JAVA_HONE%\lib\tools.jar
rem set PATH=%JAVA_HOME%\bin;
java -jar .\target\user-service-1.0-SNAPSHOT.jar -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -Xloggc:.\gc.log