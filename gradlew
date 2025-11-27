#!/bin/bash
if command -v gradle >/dev/null 2>&1; then
  gradle "$@"
else
  echo "Gradle not found. CI should use the Gradle action."
  exit 1
fi
