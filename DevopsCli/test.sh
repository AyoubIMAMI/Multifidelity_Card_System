#echo 'script demo.txt' | socat EXEC:"docker attach cli",pty STDIN
winpty 'script demo.txt' | docker attach cli
read -p "////"
