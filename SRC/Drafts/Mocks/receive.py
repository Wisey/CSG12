from flask import Flask, request
import json
import sys
import os

app = Flask(__name__)

TEST = True
strings_types = (unicode, str)

def shutdown_server():
    func = request.environ.get('werkzeug.server.shutdown')
    if func is None:
        raise RuntimeError('Not running with the Werkzeug Server')
    func()

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

        if body.has_key('locations') and body.has_key('name'):
            if len(body['locations']) > 0 and type(body['name']) is unicode:
                os.system('figlet IT WORKS')
                if not TEST:
                    shutdown_server()
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
