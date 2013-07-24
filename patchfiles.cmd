@echo off
IF "%1"=="-h" echo "Usage: patchfiles.cmd <mcpdirectory>"
IF [%1]==[] (
	echo "Usage: patchfiles.cmd <mcpdirectory>"
)
IF EXIST "%1\runtime\bin\python\python_mcp.exe" %1\runtime\bin\python\python_mcp.exe patchfiles.py -d %1
PAUSE