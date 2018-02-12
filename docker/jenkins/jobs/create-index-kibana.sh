#!/bin/bash

url="http://kibana:5601"
index_pattern="logstash-*"
time_field="@timestamp"
# Create index pattern and get the created id
# curl -f to fail on error
id=$(curl -s -f -XPOST -H "Content-Type: application/json" -H "kbn-xsrf: anything" \
  "$url/api/saved_objects/index-pattern" \
  -d"{\"attributes\":{\"title\":\"$index_pattern\",\"timeFieldName\":\"$time_field\"}}" \
  | jq -r '.id')
# Create the default index
curl -s -XPOST -H "Content-Type: application/json" -H "kbn-xsrf: anything" \
  "$url/api/kibana/settings/defaultIndex" \
  -d"{\"value\":\"$id\"}"
