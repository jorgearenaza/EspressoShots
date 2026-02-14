#!/bin/bash
set -e

echo "=== Building EspressoShots APK ==="
echo "Running gradle build..."

# Este script asume que existe ./gradlew
if [ ! -f "./gradlew" ]; then
    echo "Error: gradlew not found"
    exit 1
fi

chmod +x ./gradlew
./gradlew clean :app:assembleDebug

echo "=== Build complete ==="
ls -lh app/build/outputs/apk/debug/
