#!/bin/bash
echo "script full-payment-scripts.txt" | socat EXEC:"docker attach cli",pty STDIN
