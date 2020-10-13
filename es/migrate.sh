curl -XDELETE "localhost:9200/omtm"

# TODO: localhost -> scraper API ec2 IP address
curl -XGET "localhost:9000/recipes/youtube" | \
#cat test.json | \

# JSON processor - refer to 'https://stedolan.github.io/jq/'
jq -c '.[] | .["data"] | .[] | {"index": {"_index": "omtm", "_type": "recipe"}}, .' | \

# TODO: localhost -> elasticsearch ec2 IP address
curl  -s -H "Content-Type: application/x-ndjson" -XPOST "localhost:9200/_bulk" --data-binary @-
