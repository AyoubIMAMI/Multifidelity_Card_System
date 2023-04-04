import psycopg2
import os
import time
#apt-get install -y socat
#sudo apt install python3-pip
#pip install psycopg2-binary



def getTable(tableName):
    # Exécuter une requête SELECT pour obtenir le contenu de la table 'customer'
    cursor.execute("SELECT * FROM " + tableName)

    # Parcourir les résultats de la requête et les afficher
    userTable = cursor.fetchall()
    return userTable

def printTable(table):
    #print("printTable")
    #print("print ",table[0])
    for row in table:
        print(row)

def connect():
    global connection
    global cursor
    # Définir les informations de connexion à la base de données
    connection = psycopg2.connect(
        host="172.21.0.3",
        database="tcf-db",
        user="postgresuser",
        password="postgrespass",
        port=5432
    )
    return connection

def disconnect(cursor, connection):
    cursor.close()
    connection.close()



# Ouvrir une connexion et un curseur
print("Hello World")
connection = connect()
cursor = connection.cursor()

userTable = getTable("customer")
printTable(userTable)

organisationTable = getTable("organisation")
printTable(organisationTable)

os.system('ls -l')
os.system('./inputInCli.sh')
time.sleep(3)

userTable = getTable("customer")
printTable(userTable)

organisationTable = getTable("organisation")
printTable(organisationTable)

# Fermer le curseur et la connexion à la base de données
cursor.close()
connection.close()