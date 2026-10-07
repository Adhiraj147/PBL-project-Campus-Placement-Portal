# =====================================================================
# Campus Placement Portal - PowerShell Server Runner
# =====================================================================

if (-not (Test-Path "bin\com\placement\server\PlacementServer.class")) {
    Write-Host "[INFO] Binaries not found. Running compilation first..." -ForegroundColor Yellow
    .\compile.ps1
}

Write-Host "=====================================================================" -ForegroundColor Cyan
Write-Host " Starting Placement Web Server on http://localhost:8080/ ..." -ForegroundColor Green
Write-Host "=====================================================================" -ForegroundColor Cyan

Start-Process "http://localhost:8080/"
java -cp bin com.placement.server.PlacementServer 8080
