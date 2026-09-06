#!/usr/bin/env bash
set -euo pipefail

GRAAL_HOME="${GRAAL_HOME:-${GRAALVM_HOME:-$HOME/.sdkman/candidates/java/current}}"
DEPENDENCY_DIR="${ZPE_DEPENDENCY_DIR:-$HOME/Sync/Programs/JARs}"
BUILD_DIR="${BUILD_DIR:-build/native}"
PLATFORM="$(uname -s)"
DEPENDENCIES=(oshi-core-6.6.0.jar jna-5.13.0.jar jna-platform-5.13.0.jar slf4j-api-2.0.17.jar slf4j-simple-2.0.17.jar)

if [[ "$PLATFORM" == MINGW* || "$PLATFORM" == MSYS* || "$PLATFORM" == CYGWIN* ]]; then
  GRAAL_HOME="$(cygpath -u "$GRAAL_HOME")"
  JAVAC="${JAVAC:-$GRAAL_HOME/bin/javac.exe}"
  NATIVE_IMAGE="${NATIVE_IMAGE:-$GRAAL_HOME/bin/native-image.cmd}"
  CP_SEPARATOR=';'
else
  JAVAC="${JAVAC:-$GRAAL_HOME/bin/javac}"
  NATIVE_IMAGE="${NATIVE_IMAGE:-$GRAAL_HOME/bin/native-image}"
  CP_SEPARATOR=':'
fi

if [ ! -f "$NATIVE_IMAGE" ] && command -v native-image >/dev/null 2>&1; then
  NATIVE_IMAGE="$(command -v native-image)"
  JAVAC="$(command -v javac)"
fi
if [ ! -f "$NATIVE_IMAGE" ]; then
  printf 'GraalVM native-image was not found. Set GRAAL_HOME or GRAALVM_HOME.\n' >&2
  exit 1
fi

mkdir -p "$BUILD_DIR/classes"
CP="$BUILD_DIR/classes"
for dependency in "${DEPENDENCIES[@]}"; do
  if [ ! -f "$DEPENDENCY_DIR/$dependency" ]; then
    printf 'Missing dependency: %s\n' "$DEPENDENCY_DIR/$dependency" >&2
    exit 1
  fi
  CP="$CP$CP_SEPARATOR$DEPENDENCY_DIR/$dependency"
done

"$JAVAC" -source 11 -target 11 -cp "$CP" -d "$BUILD_DIR/classes" native-src/SystemInfoNativePlugin.java
"$NATIVE_IMAGE" --shared --no-fallback --enable-native-access=ALL-UNNAMED \
  -cp "$CP" \
  -H:+UnlockExperimentalVMOptions \
  -H:Path="$BUILD_DIR" \
  -H:Name=zpe.lib.systeminfo \
  -H:IncludeResources='META-INF/services/.*' \
  -H:IncludeResources='com/sun/jna/.*' \
  -H:-UnlockExperimentalVMOptions \
  SystemInfoNativePlugin

case "$PLATFORM" in
  Darwin) [ -f "$BUILD_DIR/libzpe.lib.systeminfo.dylib" ] && mv "$BUILD_DIR/libzpe.lib.systeminfo.dylib" "$BUILD_DIR/zpe.lib.systeminfo.dylib"; OUTPUT="$BUILD_DIR/zpe.lib.systeminfo.dylib" ;;
  Linux) [ -f "$BUILD_DIR/libzpe.lib.systeminfo.so" ] && mv "$BUILD_DIR/libzpe.lib.systeminfo.so" "$BUILD_DIR/zpe.lib.systeminfo.so"; OUTPUT="$BUILD_DIR/zpe.lib.systeminfo.so" ;;
  MINGW*|MSYS*|CYGWIN*) OUTPUT="$BUILD_DIR/zpe.lib.systeminfo.dll" ;;
  *) OUTPUT="$BUILD_DIR/zpe.lib.systeminfo.dll" ;;
esac

if [ ! -f "$OUTPUT" ]; then
  printf 'Native plugin was not produced at %s\n' "$OUTPUT" >&2
  exit 1
fi
printf 'Built %s\n' "$OUTPUT"
