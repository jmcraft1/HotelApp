function loadReservations() {
	let email = getElementById("email").value;
	System.out.println("Entered loadDoc");
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("reservationList").innerHTML = xhr.responseText;
		} else {
			console.log("error");
		}
	}
	xhr.open('GET', '/NewWorldHotelsApp/ReservationServlet');
	xhr.send();
}

function getSelection() {
	let selection = getElementById("availRooms").value;
	let xhr = new XMLHttpRequest();
	console.log(selection);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200 ) {
				document.getElementById("roomList").innerHTML = xhr.responseText;
			} else {
				console.log("error");
			}
		}
	}
	xhr.open('GET', '/NewWorldHotelsApp/RoomServlet');
	xhr.send();
}

function getMessages() {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        console.log(this.readyState);

        if (this.readyState == 4) {
            if (this.status == 200) {
                document.getElementById('messToAndfrom').innerHTML = xhr.responseText;
            } else {
                console.log("error");
            }   
        }
    }

    xhr.open('GET', '/NewWorldHotelsApp/MessageServlet');
    xhr.send();
}

function uploadPic() {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        console.log(this.readyState);

        if (this.readyState == 4) {
            if (this.status == 200) {
                
            } else {
                console.log("error");
            }   
        }
    }

    xhr.open('GET', '/NewWorldHotelsApp/Pixservlet');
    xhr.send();
}

function viewMessages () {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById('messageReply').innerHTML = xhr.responseText;
		} else {
			console.log("error");
		}
	}
	

	xhr.open('GET', 'messReply.txt');
	xhr.send();
}