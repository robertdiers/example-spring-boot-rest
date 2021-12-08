#! /bin/bash

# start all containers
EXAMPLE_UID=${UID} EXAMPLE_GID=${GID} docker-compose -p summit-stack up --detach --build

# waiting for kafka
echo 'Sleeping 30 sec to execute Kafka init'
sleep 30

# execute Kafka init scripts
echo 'Execute Kafka INIT Scripts'
docker exec -it summit-zookeeper /var/lib/kafka/initializerscripts/execInit.sh
