#!/usr/bin/env bash
set -euo pipefail

DESTINATION="${1:-build/native-dependencies}"
mkdir -p "$DESTINATION"

download() {
  local group="$1" artifact="$2" version="$3"
  local group_path="${group//.//}"
  local file="$artifact-$version.jar"
  if [ ! -f "$DESTINATION/$file" ]; then
    curl -fL "https://repo1.maven.org/maven2/$group_path/$artifact/$version/$file" -o "$DESTINATION/$file"
  fi
}

download com.github.oshi oshi-core 6.6.0
download net.java.dev.jna jna 5.13.0
download net.java.dev.jna jna-platform 5.13.0
download org.slf4j slf4j-api 2.0.17
download org.slf4j slf4j-simple 2.0.17
