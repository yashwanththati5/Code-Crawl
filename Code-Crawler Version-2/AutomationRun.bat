@echo off
setlocal

set "CURRENT_DIR=%cd%"

echo Starting the compilation

dir /s /b "%CURRENT_DIR%\src\*.java" > java_files.txt

javac -d output -cp ".;libs/*" -nowarn @"java_files.txt"

del java_files.txt

echo Compilation completed

copy "%CURRENT_DIR%\src\AutomationCore\src\automation_helper\XPath.properties" "%CURRENT_DIR%\output\AutomationCore\src\automation_helper\"
copy "%CURRENT_DIR%\src\StringProperties.properties" "%CURRENT_DIR%\output\"

echo Moved Resources files to respective location

echo Automation Run started
java -cp "output;libs/*" StartTheAutomation > output.log 2>&1
echo Automation Run ended

endlocal
