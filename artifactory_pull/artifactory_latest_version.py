import json


def main(output_str):
    artifactory_verisons_list = json.loads(output_str)

    # Removing all .xml elements
    artifactory_verisons_list = [ x for x in artifactory_verisons_list if ".xml" not in x['path']]

    # Sorting versions by modified date
    artifactory_verisons_list_sorted = sorted(artifactory_verisons_list, key=lambda x: x['modified'], reverse=True)

    # Find the latest version path
    latest_full_path = artifactory_verisons_list_sorted[0]['path']
    splitted_path = latest_full_path.split('/')
    
    print(splitted_path[-2])

if __name__ == "__main__":
    main(input())