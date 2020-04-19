@echo off
call del /s /q .gitignore
call del /q local.properties
call del /s /q *.iml

RD /S /Q ".gradle"
RD /S /Q ".idea"
RD /S /Q "app\build"
RD /S /Q "commonlibrary\build"
RD /S /Q "redapp\build"
RD /S /Q "blueapp\build"

pause