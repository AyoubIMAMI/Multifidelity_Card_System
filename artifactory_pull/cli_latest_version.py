from artifactory_pull import get_latest_version


CLI_PATH = "libs-release-local/fr/univcotedazur/fidelity/cli/"


if __name__ == "__main__":
    get_latest_version(CLI_PATH)