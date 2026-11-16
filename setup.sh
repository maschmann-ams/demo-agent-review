#!/usr/bin/env bash
set -euo pipefail
DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
git -C "$DIR" init -b main
git -C "$DIR" add .
git -C "$DIR" -c user.name='Training' -c user.email='training@example.invalid' commit -m "feat: initial commit for agent review exercise G"
echo "demo-agent-review initialisiert."
