# DATA SHOKUNIN

## Vue app

Front end of the app

- installed ant-design CSS framework

- Rendering through Markdown 
- Cool code highligting

## Spark environment

Where we run the Spark/Scala code and generate the markdown outputs

## ElasticSearch

- pulled elasticSearch image


docker network create spark_shokunin_backend_network

docker run --rm \
--name elasticsearch \
--net spark_shokunin_backend_network \
-p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
elasticsearch:7.16.3 \
sh

 
 docker run -d --name elasticsearch3 -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.16.3 bash


ROADMAP

12 march 2022

- building a scala REST API app.
- being able to use elasticsearch to push data with the app 
- being able to pull data from elasticsearch with the rest API.



What is spark shokunin?. 

A methodical way to think about data processing. Classification, categories.