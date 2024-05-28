import csv
import uuid
from couchbase.cluster import Cluster, ClusterOptions
from couchbase.auth import PasswordAuthenticator
from couchbase.bucket import Bucket
from couchbase.exceptions import CouchbaseException
from couchbase.mutation_state import MutationState

cluster = Cluster('couchbase://localhost', ClusterOptions(PasswordAuthenticator('username', 'password')))
bucket = cluster.bucket('Song')
collection = bucket.default_collection()

def save_to_couchbase(row):
    try:
        document_id = row['track_id']
        result = collection.upsert(document_id, row)
        print(f"Document saved with ID: {document_id}")
    except CouchbaseException as e:
        print(f"Error saving document to Couchbase: {e}")

with open('spotify_songs.csv', mode='r', encoding='ISO-8859-1') as file:
    csv_reader = csv.DictReader(file)
    for row in csv_reader:
        save_to_couchbase(row)

print("Data has been successfully saved to Couchbase.")
