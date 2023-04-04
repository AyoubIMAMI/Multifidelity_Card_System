import json
import os
import subprocess

ARTIFACTORY_URL = "http://vmpx08.polytech.unice.fr:8002/artifactory"
ARTIFACTORY_USER = "admin"
ARTIFACTORY_PASSWORD = "zEzEBf7mD2aCHA8XG4!"

CLI_PATH = "libs-release-local/fr/univcotedazur/fidelity/cli/"
BACKEND_PATH = "libs-release-local/fr/polytech/isa-devops-22-23-team-h-23/"

DOWNLOAD_DESTINATION = "./releases"


def download_lastest(artifactory_path):
    # Search all file in the given folder path and convert the std output to json
    command = f'echo \\n | jf rt s --user={ARTIFACTORY_USER} --password={ARTIFACTORY_PASSWORD} --url={ARTIFACTORY_URL} "{artifactory_path}"'
    output_bytes = subprocess.check_output(command, shell=True)
    output_str = output_bytes.decode().strip()
    artifactory_verisons_list = json.loads(output_str)

    # Sort the 
    artifactory_verisons_list_sorted = sorted(artifactory_verisons_list, key=lambda x: x['modified'], reverse=True)

    # Find the latest version path
    latest_full_path = artifactory_verisons_list_sorted[0]['path']
    latest_path = '/'.join(latest_full_path.split('/')[:5]) + '*'
    command = f'echo \\n | jf rt dl  --recursive --user={ARTIFACTORY_USER} --password={ARTIFACTORY_PASSWORD} --url={ARTIFACTORY_URL} "{latest_path}" "{DOWNLOAD_DESTINATION}"'
    os.system(command)


if __name__ == "__main__":
    download_lastest(BACKEND_PATH)
    download_lastest(CLI_PATH)