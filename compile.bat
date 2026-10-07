@echo off
echo =====================================================================
echo  Campus Placement Portal - Compiling Java Enterprise Sources...
echo =====================================================================

if not exist bin mkdir bin

javac -d bin -encoding UTF-8 src\main\java\com\placement\model\*.java src\main\java\com\placement\db\*.java src\main\java\com\placement\dao\*.java src\main\java\com\placement\auth\*.java src\main\java\com\placement\student\*.java src\main\java\com\placement\recruiter\*.java src\main\java\com\placement\admin\*.java src\main\java\com\placement\server\*.java src\main\java\com\placement\gui\*.java src\main\java\com\placement\test\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo [SUCCESS] Compilation completed successfully into 'bin/'!
    echo Run 'run.bat' to launch the web portal.
    echo Run 'test.bat' to execute the automated test suite.
    echo Run 'desktop.bat' to open the Swing TPO Desktop console.
) else (
    echo.
    echo [ERROR] Compilation failed. Please check errors above.
)
pause
