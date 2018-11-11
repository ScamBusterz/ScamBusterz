# Download the Python helper library from twilio.com/docs/python/install
from twilio.rest import Client
from flask import Flask, request
from twilio.twiml.voice_response import VoiceResponse

# Your Account Sid and Auth Token from twilio.com/user/account
account_sid = "AC15fff78d66398c702486ba9ad8dd435e"
auth_token = "83992c344d3307e5cde926cf77cbac89"
client = Client(account_sid, auth_token)

app = Flask(__name__)

@app.route("/sms", methods = ['POST'])

def sms():
    message_body = request.form['Body']
    message_body = str(message_body)
call = client.calls.create(
	to=message_body,
	from_="+18577631353",
	url="http://demo.twilio.com/docs/classic.mp3"
)


# if __name__ == "__main__":
# 	app.run()
print(call.sid)