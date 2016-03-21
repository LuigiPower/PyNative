import json

class ElementBuilder():

        json = {}

        def __init__(self, jsoninit):
                self.json = jsoninit

        @classmethod
        def createTextView(cls, el_id, text):
                json = {
                        "type": "textview",
                        "id": el_id,
                        "text": text
                }

                return cls(json)

        @classmethod
        def createGotoButton(cls, el_id, text, toScreen):
                json = {
                        "type": "button",
                        "id": el_id,
                        "text": text,
                        "action": {
                                "type": "goto",
                                "screen": toScreen
                        }
                }

                return cls(json)

	def getAsDict(self):
		#action = {
		#	"type": self.el_action_type,
		#	"screen": self.el_action_screen
		#}
		#return {
		#	"type": self.el_type,
		#	"id": self.el_id,
		#	"text": self.el_text,
		#	"action": action
		#}
                return self.json


class ViewBuilder():

        element_list = []

	def __init__(self, view_screenname, view_title):
		self.view_screenname = view_screenname
		self.view_title = view_title
                self.element_list = []

	def getView(self):
		return {
			"screenname": self.view_screenname,
			"title": self.view_title,
			"layout":{
				"elements":self.element_list
			}
		}

        def addElement(self, element):
                self.element_list.append(element.getAsDict())


def toJson(view):
	return json.dumps(view)
