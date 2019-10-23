
# GRIP Vision Code is Crashing
Double check the GRIP generated pipeline doesn't contain the `static` block at line 33

# RC App is Crashing, Unsure of Cause
1. Find the Android Debug Bridge (adb) command line tool on your system
    * ~/Library/Android/sdk/platform-tools on macOS
2. Connect the RC to the computer
3. Run `adb tcpip 5555` to enable remote debugging of the RC
4. Go to settings -> About -> Status, find the IP address of the RC
5. Run `adb connect <RC IP>:5555` to connect to the RC remotely
6. Click the debug button to deploy code (Green bug button)
7. Open the debugger view (Bottom dock)
8. When the RC crashes, a stack trace will be printed, look for files you've written in here. It will specify the line with the error