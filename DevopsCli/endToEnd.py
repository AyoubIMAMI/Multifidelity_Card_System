import psycopg2
import os
import time
import subprocess
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
        host="134.59.213.138",
        database="tcf-db",
        user="postgresuser",
        password="postgrespass",
        port=8003
    )
    return connection

def disconnect(cursor, connection):
    cursor.close()
    connection.close()



# Ouvrir une connexion et un curseur
connection = connect()
cursor = connection.cursor()

command = 'echo "script full-payment-scripts.txt" | socat EXEC:"docker attach cli",pty STDIN'
output = subprocess.check_output(command, shell=True)
print(output.decode())
time.sleep(8)

try:
    userTable = getTable("customer")
    printTable(userTable)
    organisationTable = getTable("organisation")
    printTable(organisationTable)
except:
  print("An exception occurred")






# Fermer le curseur et la connexion à la base de données
cursor.close()
connection.close()