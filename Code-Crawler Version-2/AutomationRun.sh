#!/bin/bash

# Get the current directory
CURRENT_DIR=$(pwd)

echo "Starting the compilation"

find "$CURRENT_DIR/src" -name "*.java" > java_files.txt

javac -d output -cp '.:libs/*' -nowarn "@java_files.txt"

rm java_files.txt

echo "Compilation completed"

cp "$CURRENT_DIR/src/AutomationCore/src/automation_helper/XPath.properties" "$CURRENT_DIR/output/AutomationCore/src/automation_helper/"

cp "$CURRENT_DIR/src/StringProperties.properties" "$CURRENT_DIR/output/"

echo "Moved Resources files to respective location"

echo "Automation Run started"
java -cp 'output:libs/*' StartTheAutomation > output.log 2>&1
echo "Automation Run as ended"