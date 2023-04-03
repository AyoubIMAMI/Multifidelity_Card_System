from flask import Flask, request

app = Flask(__name__)

@app.route('/claim', methods=['POST'])
def claim():
    data = request.get_json()
    if 'licensePlate' in data and 'parkingId' in data:
        return '200 OK'
    else:
        return '400 Malformed'

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=9191)