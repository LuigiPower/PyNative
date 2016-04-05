from flask import Flask, request
from jsonBuilder import *


app = Flask(__name__)
# el_type, el_id, el_text, el_action_type, el_action_screen
#cose = []
#for i in range(0, 10):
#	cose.append(ElementBuilder("button", "42", "Asciugamani", "goto", "view2").getElement())
#	cose.append(ElementBuilder("textView", "43", "Ciao", "", "").getElement())

@app.route('/ciao', methods=['GET', 'POST'])
def  interfaceBuilder():
	requestedScreenname = request.args.get('screenname')
	requestedView = ViewBuilder(requestedScreenname, requestedScreenname)

        requestedView.addElement(ElementBuilder.createTextView("77", "Sono la schermata %s" % requestedScreenname))

        for i in range(0, 10):
		requestedView.addElement(ElementBuilder.createTextView("%s" % (i * 2), "Asciugamani"))
		requestedView.addElement(ElementBuilder.createGotoButton("%d" % (i * 2 + 1), "GOTO %d" % i, "screen%d" % i))

	return toJson(requestedView.getView())

if __name__ == "__main__":
    app.run(host="0.0.0.0" )
