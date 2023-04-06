import psycopg2

# Définir les informations de connexion à la base de données
conn = psycopg2.connect(
    host="134.59.213.138",
    database="tcf-db",
    user="postgresuser",
    password="postgrespass",
    port=8003
)

# Ouvrir un curseur
cur = conn.cursor()

# Obtenir les noms de toutes les tables de la base de données
cur.execute("SELECT table_name FROM information_schema.tables WHERE table_schema='public'")

# Parcourir les résultats de la requête et afficher le contenu de chaque table
for table in cur.fetchall():
    # Afficher le nom de la table
    print(f"Contenu de la table '{table[0]}':")
    
    # Exécuter une requête SELECT pour obtenir le contenu de la table
    cur.execute(f"SELECT * FROM {table[0]}")
    
    # Parcourir les résultats de la requête et les afficher
    rows = cur.fetchall()
    for row in rows:
        print(row)
    print("") # Laisser une ligne vide entre chaque table

# Fermer le curseur et la connexion à la base de données
cur.close()
conn.close()
