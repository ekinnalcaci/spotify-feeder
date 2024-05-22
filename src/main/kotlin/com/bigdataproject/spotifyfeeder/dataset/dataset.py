from ssl import _PasswordType
from couchbase.cluster import Cluster
from couchbase.options import ClusterOptions
import csv

# cb server info
cluster = Cluster('couchbase://localhost', ClusterOptions(
  _PasswordType('spotify', 'spotify123')
))
bucket = cluster.bucket('spotify')
collection = bucket.default_collection()

# csv dosyası aktarılır
def upload_csv_to_couchbase(file_path):
    with open(file_path, newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            document_id = row['id']  # id ye göre data aktarma
            document = {
                "id": row['id'],
                "country": row['country'],
                "date": row['date'],
                "position": row['position'],
                "uri": row['uri'],
                "track": row['track'],
                "title": row['title'],
                "artist": row['artist']
            }
            collection.upsert(document_id, document)

# csv dosyasının path
csv_file_path = '/opt/spotify-dataset.csv'

# csv dosyasını cb ye upload et
upload_csv_to_couchbase(csv_file_path)
