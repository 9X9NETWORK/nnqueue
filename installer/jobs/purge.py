# - install python package: apt-get install python-amqplib
# - generate queue list
#   1.rabbitmqctl list_queues -p / name > queues.txt
#   2.keep only queue names: remove the first line, last line and number after queue names
# - run the script to purge

from amqplib import client_0_8 as amqp

conn = amqp.Connection(host="127.0.0.1:5672", userid="guest", password="guest", virtual_host="/", insist=False)
conn = conn.channel()

queues = None
with open('queues.txt', 'r') as f:
    queues = f.readlines()

for q in queues:
    if q:
        #print 'deleting %s' % q
        conn.queue_purge(q.strip())

print 'purged %d items' % len(queues)
