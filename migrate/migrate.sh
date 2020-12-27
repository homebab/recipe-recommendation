#!/bin/bash

# install prerequisite
apt-get update
apt-get -y install jq
apt-get -y install curl

# set environment variables
export ES_URL=https://es01:9200
export ALB_URL=http://omtmalb-1788113492.ap-northeast-2.elb.amazonaws.com

# delete the index already created
curl -XDELETE "$ES_URL/omtm"

# get recipe data using recipe scraper API service. (Internally, it accesses to AWS S3)
curl -XGET "$ALB_URL/scraper/recipes/youtube" | \

# JSON processor - refer to 'https://stedolan.github.io/jq/'
jq -c '.[] | .["data"] | .[] | {"index": {"_index": "omtm", "_type": "recipe"}}, .' | \

# post recipe bulk data to AWS ES
curl  -s -H "Content-Type: application/x-ndjson" -XPOST "$ES_URL/_bulk" --data-binary @-