#!/bin/bash

echo 'script demo.txt' | socat EXEC:"docker attach cli",pty STDIN
