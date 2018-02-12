# Docker-compose example

## Description

A multi-container Docker application with Docker Compose that would have the following services:

- 1 Redis container
- 2 Apache containers
    - serve one index.php file that
        - outputs a json containing the current date and the IP of the user
        - sets a session variable called `count` that is incremented on each visit
    - save all session information in the Redis container
    - defer all interpreting of PHP code to the PHP-FPM container
    - have error logging and access logging enabled
    - logs need to be sent to the Logstash container
- 1 PHP-FPM container
    - PHP7.1
    - used for interpreting PHP code from the Apache containers
- 1 Load Balancer for the Apache containers
- 1 Jenkins container
    - has a job that runs once per day and deletes indexes older than 30 days from Elasticsearch
- 1 Elasticsearch container
- 1 Kibana container
    - allows viewing the data from the Elasticsearch container
- 1 Logstash container
    - ingests access and error logs from the Apache containers and saves to Elasticsearch


## Installation and run

To build test stand run: `docker-compose up`

After running compose please wait fot jenkins to come up and create kibana search index.
That may take few minutes. Then make few requests to http://localhost:8080,
open http://localhost:8181 and check logs there.

To destroy everything: `docker-compose down -v --remove-orphans --rmi all`


## Access points

Nginx is accessible at http://localhost:8080

Kibana is accessible at http://localhost:8181

Jenkins is accessible at http://localhost:8282


## TODO

1. Put all logs inside logstash, add more patterns.
1. Use `gelf` driver instead of syslog.
1. Eliminate custom ip setting.
