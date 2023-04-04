import json
import os
import subprocess


command = 'jf rt s --user=admin --password=zEzEBf7mD2aCHA8XG4! --url=http://vmpx08.polytech.unice.fr:8002/artifactory "libs-release-local/fr/polytech/isa-devops-22-23-team-h-23/"'

output_bytes = subprocess.check_output(command, shell=True)
output_str = output_bytes.decode().strip()

file_list = json.loads(json_in)

# trier la liste des fichiers par date de modification (du plus récent au plus ancien)
file_list_sorted = sorted(file_list, key=lambda x: x['modified'], reverse=True)

# renvoyer le nom du fichier le plus récent
latest = file_list_sorted[0]['path']
url = '/'.join(latest.split('/')[:5]) + '*'
command = 'echo \\n | jf rt dl  --recursive --user=admin --password=zEzEBf7mD2aCHA8XG4! --url=http://vmpx08.polytech.unice.fr:8002/artifactory "'+url+'"./release"'
os.system(command)
