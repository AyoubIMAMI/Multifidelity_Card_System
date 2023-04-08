import json
from flask import Flask, request, Response

app = Flask(__name__)

@app.route('/claim', methods=['POST'])
def claim():
    data = request.get_json()

    if 'licensePlate' in data and 'parkingID' in data:
        print('OK for parking')
        return Response(status=201)

    else:
        print('NOK for parking, license plate or parking id is missing')
        error_message = {'error': 'license plate or parking id is missing', 'request': str(request), 'data': data}
        return Response(json.dumps(error_message), status=400, mimetype='application/json')

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=9191)
