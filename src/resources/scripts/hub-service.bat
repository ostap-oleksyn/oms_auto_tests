if %1 EQU start (java -jar %2selenium-server-standalone-2.45.0.jar -role hub)
else if %1 EQU stop (
taskkill.exe /F /IM powershell.exe /T
)