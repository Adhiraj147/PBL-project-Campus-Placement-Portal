# =====================================================================
# Campus Placement Portal - PowerShell Desktop GUI Runner
# =====================================================================

if (-not (Test-Path "bin\com\placement\gui\PlacementDesktopClient.class")) {
    .\compile.ps1
}

java -cp bin com.placement.gui.PlacementDesktopClient
