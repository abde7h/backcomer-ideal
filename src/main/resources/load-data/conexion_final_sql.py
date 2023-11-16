# En primer lugar, importamos las librerías principales de Python necesarias para nuestro proyecto.
import mysql.connector
import pandas as pd
import numpy as np
import ssl


import ssl
from urllib.request import urlopen


#########################

import ssl

# Desactivar la verificación del certificado SSL
ssl._create_default_https_context = ssl._create_unverified_context

# Luego, intenta cargar el archivo
df_venta = pd.read_csv('https://opendata-ajuntament.barcelona.cat/data/dataset/1144c2b2-43a5-48f6-b447-5f11a3a34b87/resource/e42cf2cf-a76e-4a32-9357-cf90e0ea8ead/download/locveevolucio.csv')


#1) Precio VENTA de locales comerciales.
#df_venta = pd.read_csv('https://opendata-ajuntament.barcelona.cat/data/dataset/1144c2b2-43a5-48f6-b447-5f11a3a34b87/resource/e42cf2cf-a76e-4a32-9357-cf90e0ea8ead/download/locveevolucio.csv')

#2) Precio ALQUILER de locales comerciales.
df_lloguer = pd.read_csv('https://opendata-ajuntament.barcelona.cat/data/dataset/fedae1fc-bd48-4676-afdc-979423786512/resource/97356d26-30b9-436a-8dbb-d0d05f0a87fd/download/loclloevolucio.csv')

#3) Actividades economicas (locales) planta baja (comercios).
tiendas_planta_baja = pd.read_csv('https://opendata-ajuntament.barcelona.cat/data/dataset/62fb990e-4cc3-457a-aea1-497604e15659/resource/c897c912-0f3c-4463-bdf2-a67ee97786ac/download/2019_censcomercialbcn_detall.csv', dtype={35: 'str', 37: 'str'})

#4) Mercados y ferias.
mercados_ferias = pd.read_json('https://opendata-ajuntament.barcelona.cat/data/dataset/067f5ef9-c5c5-452a-ad22-c20f41f2b1ca/resource/cf159ae2-0852-4df0-a7e4-72672dc68998/download/opendatabcn_mercats-centrescomercials_mercats-fires-al-carrer.json')

#5) Mercados municipales
mercados_municipales = pd.read_json('https://opendata-ajuntament.barcelona.cat/data/dataset/706e7507-7f84-480e-940d-23265bf5d853/resource/b204171d-79e3-49d1-9a0a-0bdbb9d46bd2/download/opendatabcn_mercats-centrescomercials_mercats-municipals.json')

#6) Grandes centros comerciales
centros_comerciales = pd.read_json('https://opendata-ajuntament.barcelona.cat/data/dataset/5ce7863c-789a-46b5-977d-c5df4e263f94/resource/214ccb96-16d4-45aa-b0f5-cf8941f52400/download/opendatabcn_mercats-centrescomercials_grans-centres-comercials.json')

#7) GFalerias comerciales
galerias_comerciales=pd.read_json('https://opendata-ajuntament.barcelona.cat/data/dataset/500d773a-a09a-49d3-b521-c2de3961090d/resource/a6e54794-fb4b-4a58-b633-b5ede492bab7/download/opendatabcn_mercats-centrescomercials_galeries-comercials.json')


# #Depuración de datos

# ##1. Nuestro dataset base: Local dataset

# Realizamos la depuración de datos necesaria antes de unificar nuestros datasets

# In[3]:

# In[4]:


locals_dt = tiendas_planta_baja[['Nom_Local','Nom_Via','Porta','Codi_Districte','Nom_Sector_Activitat', 'Codi_Barri', 'Nom_Districte', 'Nom_Activitat','Longitud', 'Latitud']]


# In[5]:


#Creamos una nueva columna '' que indique el nº de locales de la misma actividad en un distrito determinado
locals_dt['Num_Activitats_Districte'] = locals_dt.groupby('Nom_Activitat')['Nom_Districte'].transform('count')


# In[6]:


#Descartamos los datos que contengan información no definida para la simplificación de nuestro dataset
locals_dt = locals_dt[(locals_dt['Nom_Activitat']!= 'Sense informació') | (locals_dt['Nom_Local']!= 'SN')]


# In[7]:


#Añadimos una columa según la disponibilidad de dicho local para alquiler y venta
#limitaremos nuestro dataset para realizar la muestra del proyecto a pequeña escala en nuestra primera versión)

locals_dt['isAvailability'] = np.where((locals_dt['Nom_Sector_Activitat']=='Locals buits en venda i lloguer'), True, False)


# In[8]:


#Añadimos una columna que concatene la dirección completa del local

#1) Eliminar nulos de columna Porta
locals_dt.dropna(subset=['Porta'], inplace=True)
#2) Casteamos la columna Porta a int y str
locals_dt['Porta']=locals_dt['Porta'].astype(int)
locals_dt['Porta']=locals_dt['Porta'].astype(str)
#3) Concatenamos y añadimos una nueva variable
locals_dt['address'] = locals_dt['Nom_Via'] + " " + locals_dt['Porta']


# In[9]:


# In[10]:


#Renombramos las variables del dataset
newOrder = ['Nom_Local', 'address', 'Codi_Districte', 'Nom_Districte', 'Codi_Barri', 'isAvailability', 'Nom_Activitat', 'Num_Activitats_Districte','Longitud','Latitud']
locals_dt=locals_dt[newOrder]

rename = ['localName', 'address','districtID','district', 'Codi_Barri', 'isAvailability','activityType','totalDistrictStore','longitude','latitude']
locals_dt.columns = rename


# #2. Nuestro dataset complementario: Prices_dt

# In[11]:


#Creamos una lista de columnas a transformar
columnas_cat = ['DOSZEROONZE', 'DOSZERODEU', 'DOSZEROZERONOU', 'DOSZEROZEROVUIT']


# In[12]:


# Iterar sobre cada columna en la lista y convertirla a numérica
for columna in columnas_cat:
    df_lloguer[columna] = pd.to_numeric(df_lloguer[columna], errors='coerce')

for columna in columnas_cat:
    df_venta[columna] = pd.to_numeric(df_venta[columna], errors='coerce')


# In[13]:


#Juntar los 2 conjuntos de datos sobre el numero de Index
df_merged = df_lloguer.merge(df_venta, left_index=True, right_index=True)
df_merged.rename(columns={"DTE_x" : "districtId", "DOSZEROONZE_x" : "2011_rent", "DOSZEROONZE_y":"2011_sales"}, inplace=True)


# In[14]:


#Eliminar las columnas para quedarnos con los precios de alquiler y de venta de 2011
df_merged.drop(columns=['DOSZERODEU_x', 'DOSZEROZERONOU_x', 'DOSZEROZEROVUIT_x', 'DTE_y', 'BARRIS_y', 'DOSZERODEU_y', 'DOSZEROZERONOU_y', 'DOSZEROZEROVUIT_y'], inplace=True)



# In[15]:


#Convertir la columna BARRIS_x para extraer el ID de Barrio
df_ID_BARRIO = df_merged['BARRIS_x'].str.split('.', expand=True)

#Añadir la columna ID_BARRIO al dataset df_merged
df_merged['ID_BARRIO'] =  df_ID_BARRIO[0]
df_merged['ID_BARRIO']=df_merged['ID_BARRIO'].astype(int)

#Eliminar la columna de nombre de los Barrios
df_merged.drop(columns='BARRIS_x', inplace=True)

#Cambiar el nombre del dataset
prices_dt = df_merged


# In[16]:



# #3. Merged_dt

# In[17]:


#Unimos nuestros dos datasets con ID_Barrio como variable común para agrupar los precios de forma correcta
locals_dt=pd.merge(locals_dt, prices_dt, left_on='Codi_Barri', right_on='ID_BARRIO', how='inner')

#Eliminamos las columnas innecesarias o duplicadas
locals_dt.drop(columns=['ID_BARRIO','Codi_Barri','districtId'],inplace=True)


# In[18]:


#Eliminamos las  con valores nulos
locals_dt = locals_dt.dropna()

#Reiniciamos el índice y añadimos un nº identificativo para cada local definido en nuestro dataset
locals_dt.reset_index(inplace=True)
locals_dt.drop(columns='index',inplace=True)
locals_dt.reset_index(inplace=True)
locals_dt['index']=locals_dt['index'].astype(str)
locals_dt['index']= 'loc'+ locals_dt['index']


# In[19]:


#Renombramos las columnas
rename = ['localID','localName', 'address','districtID','district','isAvailability','activityType','totalDistrictStore','longitude','latitude','rentalPrice','salePrice']
locals_dt.columns = rename


# In[22]:


df=locals_dt.copy()


# In[23]:


# Tu DataFrame original
# Filtrar por "activityType"
selected_activities = ['Restaurants', 'Vestir', 'Bars   / CIBERCAFÈ','Locals buits en venda i lloguer']
filtered_df = df[df['activityType'].isin(selected_activities)]

# Dividir el DataFrame en grupos por "district"
district_groups = filtered_df.groupby('district')

# Función para muestrear 100 muestras por grupo con las condiciones especificadas
def sample_rows(group):
    num_samples = min(100, len(group))
    num_true = int(0.3 * num_samples)
    num_false = num_samples - num_true

    true_rows = group[group['isAvailability'] == True].sample(n=num_true, replace=True)
    false_rows = group[group['isAvailability'] == False].sample(n=num_false, replace=True)



    return pd.concat([true_rows, false_rows])

# Aplicar la función de muestreo a cada grupo y concatenar los resultados
result_df = district_groups.apply(sample_rows).reset_index(drop=True)


# In[24]:
result_df['isGolden']=False

result_df.drop_duplicates(subset=['localID'],inplace=True)

# In[ ]:

#MUESTREO
#result_df.groupby(['district','activityType','isAvailability']).count()


try:
    connection=mysql.connector.connect(
        host='localhost',
        port=3306,
        user='root',
        password='admin1234',
        db='locales'
    )
    if connection.is_connected():
        print("conexion exitosa")
        info_server=connection.get_server_info()
        print(info_server)
        cursor=connection.cursor()
        cursor.execute("SELECT DATABASE()")
        row = cursor.fetchone()
        print("Conectado a la base de datos:{}".format(row))

except Exception as ex:
    print(ex)


# Convertir el DataFrame a una lista de tuplas
data_tuples = [tuple(x) for x in result_df.to_numpy()]

# Definir la consulta de inserción
insert_query = """
    INSERT INTO locales_comerciales
    (localId, localName, address, districtId, district, isAvailability,
    activityType, totalDistrictStore, longitude, latitude, rentalPrice, salePrice,isGolden)
    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
"""

try:
    # ... (código de conexión a la base de datos)

    if connection.is_connected():
        print("Conexión exitosa")
        info_server = connection.get_server_info()
        print("Versión del servidor MySQL:", info_server)

        cursor = connection.cursor()

        # Selecciona la base de datos (en tu caso 'locales')
        cursor.execute("USE locales")

        cursor.execute("""
            DROP TABLE IF EXISTS locales_comerciales 
        """)

        # Crear la tabla si no existe
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS locales_comerciales (
                localId VARCHAR(255) PRIMARY KEY,
                localName VARCHAR(255),
                address VARCHAR(255),
                districtId VARCHAR(255),
                district VARCHAR(255),
                isAvailability BOOLEAN,
                activityType VARCHAR(255),
                totalDistrictStore INT,
                longitude DOUBLE,
                latitude DOUBLE,
                rentalPrice DECIMAL(10, 2),
                salePrice DECIMAL(10, 2),
                isGolden BOOLEAN
            )
        """)

        # Ejecutar la inserción
        cursor.executemany(insert_query, data_tuples)
        connection.commit()  # Confirmar los cambios en la base de datos

except Exception as ex:
    print(ex)

finally:
    if connection.is_connected():
        cursor.close()
        connection.close()
        print("Conexión cerrada")
