# =====================================================================
# Campus Placement Portal - PowerShell Compilation Script
# =====================================================================

Write-Host "=====================================================================" -ForegroundColor Cyan
Write-Host " Campus Placement Portal - Compiling Java Enterprise Sources..." -ForegroundColor Cyan
Write-Host "=====================================================================" -ForegroundColor Cyan

if (-not (Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
}

$sources = Get-ChildItem -Path "src\main\java" -Recurse -Filter "*.java" | ForEach-Object { $_.FullName }
javac -d bin -encoding UTF-8 $sources

if ($LASTEXITCODE -eq 0) {
    Write-Host "`n[SUCCESS] Compilation completed successfully into 'bin/'!" -ForegroundColor Green
    Write-Host "Run .\run.ps1 to launch the web portal on port 8080." -ForegroundColor Yellow
    Write-Host "Run .\test.ps1 to execute the 25 automated tests." -ForegroundColor Yellow
    Write-Host "Run .\desktop.ps1 to open the Swing TPO Desktop console." -ForegroundColor Yellow
} else {
    Write-Host "`n[ERROR] Compilation failed. Please check compiler output." -ForegroundColor Red
}
