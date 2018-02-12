FROM docker.elastic.co/logstash/logstash:6.2.1

RUN rm -f /usr/share/logstash/pipeline/logstash.conf

COPY logstash.conf /usr/share/logstash/pipeline/
