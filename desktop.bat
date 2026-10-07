@echo off
echo =====================================================================
echo  Campus Placement Portal - Launching Swing Desktop Console...
echo =====================================================================

if not exist "bin\com\placement\gui\PlacementDesktopClient.class" (
    call compile.bat
)

java -cp bin com.placement.gui.PlacementDesktopClient
