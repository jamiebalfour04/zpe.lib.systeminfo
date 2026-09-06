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

download com.github.oshi oshi-common 7.4.3
download com.github.oshi oshi-core-ffm 7.4.3
download org.slf4j slf4j-api 2.0.18
download org.slf4j slf4j-simple 2.0.18
