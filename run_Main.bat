echo %JAVA_HOME%
echo off
call setenv_1.8.0_73
set CLASSPATH=%CLASSPATH%;.\deploy\jbones_stripes-config.jar;.\deploy\jbones_stripes.jar;
echo using classpath ...
echo %CLASSPATH%

"%JAVA_HOME%\bin\java" -classpath %CLASSPATH% org.jbones.stripes.Main

pause
