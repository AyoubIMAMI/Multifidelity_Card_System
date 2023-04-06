from flask import Flask, request

app = Flask(__name__)

@app.route('/claim', methods=['POST'])
def claim():
    data = request.get_json()
    print(
          "Received request: {licensePlate='" +
            data['licensePlate'] +
            "', parkingId=" +
            data['parkingId'] +
            '}',
        )
    if 'licensePlate' in data and 'parkingId' in data:
        print('OK for parking')
        return '200 OK'
    else:
        print('NOK for parking, license plate or parking id is missing')
        return '400 Malformed'

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=9191)