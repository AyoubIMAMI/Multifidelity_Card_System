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

command = 'echo "script VFP_ScriptComplet.txt" | socat EXEC:"docker attach cli",pty STDIN'
output = subprocess.check_output(command, shell=True)
print("out= ",output.decode())
time.sleep(8)

def assertEquals(expected, actual):
    if(expected != actual):
        print(str(expected)+" not equals to "+str(actual))
        exit(-1)

customerTable_expected = [(1, 'benoit@gmail.com', 0.0, False, None, 30, 'Benoit', 'benocub'),(2, 'thomas@gmail.com', 23.0, False, None, 15, 'Thomas', 'thobilou'),(14, 'benoit148494@gmail.com', 0.0, False, None, 0, 'Benoit', 'benocub'),(15, 'benoit11954196@gmail.com', 0.0, False, None, 0, 'Benoit', 'benocub'),(16, 'benoit151@gmail.com', 0.0, False, 'XLZMDLSO', 200, 'Benoit', 'benocub')]
#printTable(customerTable_expected)

shoppingListTable_expected = [(7, 5, 1),(7, 6, 2),(10, 8, 1),(10, 9, 1),(13, 11, 1),(13, 12, 1),(20, 19, 1),(21, 19, 1),(22, 19, 1),(23, 19, 1),(24, 19, 1),(25, 19, 1),(26, 19, 1),(27, 19, 1),(28, 19, 1),
(29, 19, 1)]

vfpaccountTable_expected = [(30, 16)]

advantageTable_expected = [(17, 'advantageTest')] 

organisationTable_expected = [('Store', 3, 'Fast Market', 'yTrEA', '123456789'),('Store', 4, "Vente'2000", 'AzErTy', '987654321'),('Store', 18, 'Fast Market', 'yTrEA', '123456798949989')]

buyableTable_expected = [('Product', 5, 'T-shirt', 20.0, None, 4),('Product', 6, 'Ananas', 5.0, None, 4),('Product', 8, 'Tabouret', 10.0, None, 3),('Product', 9, 'Pantalon', 15.0, None, 3),('Discount', 11, 'Cafe', None, 12, 3),('Product', 12, 'Crayon', 2.0, None, 3),('Product', 19, 'T-shirt', 20.0, None, 18)]

customerTable = getTable("customer")
printTable(customerTable)

organisationTable = getTable("organisation")
printTable(organisationTable)

buyableTable = getTable("buyable")
printTable(buyableTable)

payment_shopping_listTable = getTable("payment_shopping_list")
printTable(payment_shopping_listTable)

vfpaccountTable = getTable("vfpaccount")
printTable(vfpaccountTable)

advantageTable = getTable("advantage")
printTable(advantageTable)

assertEquals(customerTable_expected, customerTable)
assertEquals(organisationTable_expected, organisationTable)
assertEquals(buyableTable_expected, buyableTable)
assertEquals(shoppingListTable_expected, payment_shopping_listTable)
assertEquals(vfpaccountTable_expected, vfpaccountTable)
assertEquals(advantageTable_expected, advantageTable)

# Fermer le curseur et la connexion à la base de données
cursor.close()
connection.close()