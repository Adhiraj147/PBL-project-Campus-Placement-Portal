@echo off
echo =====================================================================
echo  Campus Placement Portal - Executing Automated Test Suite...
echo =====================================================================

if not exist "bin\com\placement\test\PlacementPortalTest.class" (
    call compile.bat
)

java -cp bin com.placement.test.PlacementPortalTest
pause
