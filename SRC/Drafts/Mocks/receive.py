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
        body = json.loads(request.data.strip())

        fields = (body[name] for name in (
                      'name',
                      'short-description',
                      'long-description'
                      )
                  )
        print [f for f in fields]
        field_types = (type(f) in strings_types for f in fields)
        print field_types

        if all_str and len(body['locations']) > 0:
            os.system('figlet IT WORKS')
            if not TEST:
                shutdown_server()

        return 'success!'
        
    else:
        return 'POST to this url, please\n'

if __name__ == '__main__':
    app.debug = True
    app.run()
