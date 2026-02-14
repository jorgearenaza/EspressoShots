#!/bin/bash
# Quick build and test script for EspressoShots

echo "Building EspressoShots APK..."
./gradlew clean :app:assembleDebug

if [ $? -eq 0 ]; then
    echo "✓ Build successful!"
    echo "APK location: app/build/outputs/apk/debug/app-debug.apk"
else
    echo "✗ Build failed!"
    exit 1
fi
