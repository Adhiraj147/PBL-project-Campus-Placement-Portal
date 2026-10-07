@echo off
echo =====================================================================
echo  Campus Placement Portal - Launching Enterprise Web Server...
echo =====================================================================

if not exist "bin\com\placement\server\PlacementServer.class" (
    echo [INFO] Binaries not found. Running compilation first...
    call compile.bat
)

echo Starting Server on http://localhost:8080/ ...
start "" "http://localhost:8080/"
java -cp bin com.placement.server.PlacementServer 8080
pause
