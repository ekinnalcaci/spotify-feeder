A small springboot project to use big data handling & processing technologies such as Couchbase, ELK stack, Couchbase-Elasticsearch-Connector. 
All docker files for technologies needed to use this project, such as Zookeper, Kowl etc. are all included in resources. 
Project works on

* A Couchbase repository that holds 30k spotify songs. Dataset that cb has is taken from [joebeachcapital](https://www.kaggle.com/datasets/joebeachcapital/30000-spotify-songs/data), a
  script for inserting the csv can be found under resources.
* Cron jobs that updates song popularities using Kafka messaging.
* Elasticsearch to use queries to make aggregations or make sense of data.
* CBES that updates the elastic index which makes use of Kibana visuals
 <img width="1436" alt="Kibana Dashboard" src="https://github.com/ekinnalcaci/spotify-feeder/assets/100304047/13a8a295-c1a8-4ff8-9374-8062db3eb373">
 
