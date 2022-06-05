# DATA SHOKUNIN

- Front: Vue app
- BackEnd Server: Akka


## Vue app

Front end of the app

- installed ant-design CSS framework
- Rendering through Markdown 
- Cool code highligting

### Dev environment 

Coding with vsc inside a devcontainer. See .devcontainer in vue_app. 



## Spark environment

Where we run the Spark/Scala code and generate the markdown outputs

## ElasticSearch

https://github.com/elastic/elasticsearch/blob/master/docs/reference/setup/install/docker/docker-compose.yml

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

## High Level process

- User can edit a markdown file locally, 


Data updated from Markdown file.
Data updated directly on the UI.

Putting data in the right format for display D3
Putting data in the right format to create path field.

# ROADMAP

12 march 2022

- building a scala REST API app.
- being able to use elasticsearch to push data with the app 
- being able to pull data from elasticsearch with the rest API.



What is spark shokunin?. 

A methodical way to think about data processing. Classification, categories.

# May 28th 2022

- Having an elastic instance running
- Setup an Akka Server.
- Worked on cors policy with Akka.
- Managed to make a first request from the vue app to the akka backend app. ping/pong.
curl --cacert ca/ca.crt --user elastic:elastic_pass -X PUT "https://localhost:9200/my-index-00002?pretty"

# June 3rd 2022

- Managed to get first md text from elasticSearch into vue app.

# June 5th 2022

