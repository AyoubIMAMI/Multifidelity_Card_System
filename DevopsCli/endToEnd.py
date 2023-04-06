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

def createTable(input):
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
#wait 20secondes the cli
time.sleep(20)

command = 'echo "script full-payment-scripts.txt" | socat EXEC:"docker attach cli",pty STDIN'
output = subprocess.check_output(command, shell=True)
print("out= ",output.decode())
time.sleep(8)

def assertEquals(expected, actual):
    if(expected != actual):
        print(str(expected)+" not equals to "+str(actual))
        exit(-1)

customerTable_expected = [(1, 'benoit@gmail.com', 0.0, False, None, 30, 'Benoit', 'benocub'),(2, 'thomas@gmail.com', 23.0, False, None, 15, 'Thomas', 'thobilou')]
printTable(customerTable_expected)

organisationTable_expected = [('Store', 3, 'Fast Market', 'yTrEA', '123456789'),('Store', 4, "Vente'2000", 'AzErTy', '987654321')]
printTable(organisationTable_expected)

paymentTable_expected = [(5, 30.0, True, 1, 4),(6, 25.0, True, 2, 3),(7, 2.0, True, 2, 3)]
printTable(paymentTable_expected)


customerTable = getTable("customer")
printTable(customerTable)

organisationTable = getTable("organisation")
printTable(organisationTable)

paymentTable = getTable("payment")
printTable(paymentTable)


assertEquals(customerTable_expected, customerTable)
assertEquals(organisationTable_expected, organisationTable)
#assertEquals(paymentTable_expected, paymentTable)

# Fermer le curseur et la connexion à la base de données
cursor.close()
connection.close()