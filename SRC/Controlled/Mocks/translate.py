#!/usr/bin/env python

from flask import Flask, request
import json
import sys
import os
import urllib2
import sys

app = Flask(__name__)

TEST = True
strings_types = (unicode, str)

@app.route('/', methods=['GET', 'POST'])
def hello_world():
    if request.method == 'POST':
        print request.data.strip()
        body = json.loads(request.data.strip())

        fields = (body[name] for name in (
                      'name',
                      'short-description',
                      'long-description'
                      )
                  )
        if False:
            for loc in body['locations']:
                loc['image'] = 'test'

        if body.has_key('locations') and body.has_key('name'):
            if len(body['locations']) > 0 and type(body['name']) is unicode:
                os.system('figlet IT WORKS')
                print '1'
                req = urllib2.Request("http://nyaa.kragniz.eu:443/~group/upload.php")
                print '2'
                req.add_header('Content-Type', 'application/json')
                print '3'
                print json.dumps(body)
                response = urllib2.urlopen(req, json.dumps(body))
                print '4'
            else:
                os.system('figlet MALFORMED')

        else:
            os.system('figlet BADNESS')

        return 'success!'
        
    else:
        os.system('figlet GETed')
        return 'POST to this url, please\n'

if __name__ == '__main__':
    app.debug = True
    app.run(port=9090, host='0.0.0.0')
