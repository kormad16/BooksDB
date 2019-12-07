function swapMode() {
    NIGHTMODE_ACTIVE = !NIGHTMODE_ACTIVE;
    setCookie("nightmode", NIGHTMODE_ACTIVE ? 1 : 0, -1);

    document.body.style.backgroundColor = NIGHTMODE_ACTIVE ? "#202020" : "#FFFAD9";
    document.body.style.color = NIGHTMODE_ACTIVE ? "white" : "black";

    document.getElementById("title").style.border = NIGHTMODE_ACTIVE ? "5px solid #696969" : "5px solid #f7efbe";
    document.getElementById("bookTable").style.border = NIGHTMODE_ACTIVE ? "3px solid #696969" : "3px solid #f7efbe";
    document.getElementById("nightmodeButton").style.backgroundColor = NIGHTMODE_ACTIVE ? "#202020" : "#fffad9";
    document.getElementById("nightmodeButton").style.color = NIGHTMODE_ACTIVE ? "white" : "black";
    document.getElementById("nightmodeButton").style.border = NIGHTMODE_ACTIVE ? "3px solid white" : "3px solid black";
    document.getElementById("nightmodeButton").innerHTML = NIGHTMODE_ACTIVE ? "Turn on the lights" : "Turn off the lights";

    document.querySelectorAll("tr:nth-of-type(even)").forEach(e => e.style.backgroundColor = NIGHTMODE_ACTIVE ? "#202020" : "#ebe5c3");
    document.querySelectorAll("tr:nth-of-type(odd)").forEach(e => e.style.backgroundColor = NIGHTMODE_ACTIVE ? "#404040" : "#fffad9");
    document.querySelectorAll(".link").forEach(e => e.style.color = NIGHTMODE_ACTIVE ? "white" : "black");
        
}

function getCookie(cname) {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for(var i = 0; i <ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function setCookie(cname, cvalue) {
	var d = new Date();
	document.cookie = cname + "=" + cvalue + ";path=/";
}

var NIGHTMODE_ACTIVE = false;

function initNightmode() {
    NIGHTMODE_ACTIVE = getCookie("nightmode") !== "" ? parseInt(getCookie("nightmode")) === 1 : false;
    if(NIGHTMODE_ACTIVE) {
        NIGHTMODE_ACTIVE = false;
        swapMode();
    }
}