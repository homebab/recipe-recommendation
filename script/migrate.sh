apt-get update
apt-get -y install jq

curl -XDELETE "http://localhost:9200/omtm"

# TODO: localhost -> scraper API ec2 IP address
curl -XGET "http://omtmalb-1788113492.ap-northeast-2.elb.amazonaws.com/omtm/scraper/recipes/youtube" | \
# JSON processor - refer to 'https://stedolan.github.io/jq/'
jq -c '.[] | .["data"] | .[] | {"index": {"_index": "omtm", "_type": "recipe"}}, .' | \
# TODO: localhost -> elasticsearch ec2 IP address
curl  -s -H "Content-Type: application/x-ndjson" -XPOST "http://localhost:9200/_bulk" --data-binary @-