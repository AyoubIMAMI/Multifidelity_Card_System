import json
import subprocess

ARTIFACTORY_URL = "http://134.59.213.138:8002/artifactory"
ARTIFACTORY_USER = "admin"
ARTIFACTORY_PASSWORD = "zEzEBf7mD2aCHA8XG4!"


def get_latest_version(artifactory_path):
    # Search all file in the given folder path and convert the std output to json
    command = f'echo \\n | jf rt s --user={ARTIFACTORY_USER} --password={ARTIFACTORY_PASSWORD} --url={ARTIFACTORY_URL} "{artifactory_path}"'
    print("Command : " + command)
    output_bytes = subprocess.check_output(command, shell=True)
    output_str = output_bytes.decode().strip()
    artifactory_verisons_list = json.loads(output_str)

    # Removing all .xml elements
    artifactory_verisons_list = [ x for x in artifactory_verisons_list if ".xml" not in x['path']]

    # Sorting versions by date
    artifactory_verisons_list_sorted = sorted(artifactory_verisons_list, key=lambda x: x['modified'], reverse=True)

    # Find the latest version path
    latest_full_path = artifactory_verisons_list_sorted[0]['path']
    splitted_path = latest_full_path.split('/')
    print(splitted_path[-2])

