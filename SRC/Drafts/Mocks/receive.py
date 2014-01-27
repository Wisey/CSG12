from flask import Flask, request
import json
app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def hello_world():
    if request.method == 'POST':
        print json.loads(request.data.strip())
        
    else:
        return 'POST to this url, please\n'

if __name__ == '__main__':
    app.debug = True
    app.run()
