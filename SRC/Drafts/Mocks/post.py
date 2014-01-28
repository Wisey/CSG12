import urllib2
import sys

assert len(sys.argv) > 1

req = urllib2.Request(sys.argv[1])
req.add_header('Content-Type', 'application/json')

with open('post_data.json') as f:
    response = urllib2.urlopen(req, f.read())
