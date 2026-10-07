# =====================================================================
# Campus Placement Portal - PowerShell Test Runner
# =====================================================================

if (-not (Test-Path "bin\com\placement\test\PlacementPortalTest.class")) {
    .\compile.ps1
}

java -cp bin com.placement.test.PlacementPortalTest
