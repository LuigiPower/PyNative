import json

class elementBuilder():
	def __init__(self, el_type, el_id, el_text, el_action_type, el_action_screen):
		self.el_type = el_type
		self.el_id = el_id
		self.el_text = el_text
		self.el_action_screen = el_action_screen
		self.el_action_type = el_action_type

	def getElement(self):
		action = {
			"type": self.el_action_type,
			"screen": self.el_action_screen
		}
		return {
			"type": self.el_type,
			"id": self.el_id,
			"text": self.el_text,
			"action": action 
		}


class viewBuilder():
	def __init__(self, view_screenname, view_title, view_elements):
		self.view_screenname = view_screenname
		self.view_title = view_title
		self.view_elements = view_elements

	def getView(self):
		return {
			"screenname": self.view_screenname,
			"title": self.view_title,
			"layout":{
				"elements":self.view_elements
			}
		}



def toJson(view):
	return json.dumps(view)
