#!/bin/bash
echo "script full-payment-scripts.txt" | socat -exec:"docker attach cli",pty STDIN
