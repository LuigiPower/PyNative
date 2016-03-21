from flask import Flask, request
from jsonBuilder import *


app = Flask(__name__)
# el_type, el_id, el_text, el_action_type, el_action_screen
cose = []
for i in range(0, 10):
	cose.append(elementBuilder("button", "42", "Asciugamani", "goto", "view2").getElement())
	cose.append(elementBuilder("textView", "43", "Ciao", "", "").getElement())

@app.route('/ciao', methods=['GET', 'POST'])
def  interfaceBuilder():
	requestedScreenname = request.args.get('screenname')
	requestedView = viewBuilder(requestedScreenname, "Prova", cose)
	return toJson(requestedView.getView())

if __name__ == "__main__":
    app.run(host="0.0.0.0" )