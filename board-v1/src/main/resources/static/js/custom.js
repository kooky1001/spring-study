/**
 * custom.js
 */
 
function serialize (form) {
	
	let formData = new FormData(form);
	let jsonData = {};
	
	for (let [key, value] of formData) {
		let sel = document.querySelectorAll("[name=" + key + "]");
	
		if (sel.length > 1) {
			if (jsonData[key] === undefined) {
				jsonData[key] = [];				
			}
			jsonData[key].push(value);
		} 
		// Other 
		else {
        	jsonData[key] = value;
		}
	}
	
	return jsonData;
}