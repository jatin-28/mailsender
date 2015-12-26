@echo off
set CLASSPATH=
for %%i in (lib\*.jar) do call cpappend.bat %%i
set CLASSPATH=%CLASSPATH%;config
java -Dlog4j.configuration=log4j.xml -Djava.endorsed.dirs=lib\endorsed -classpath %CLASSPATH% com.ss.mailshot.ApplicationRunner  %1 %2 %3 %4 %5
