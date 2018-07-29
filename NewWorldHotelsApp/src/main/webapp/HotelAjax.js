function loadDoc() {
	System.out.println("Entered loadDoc");
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("butClicked").innerHTML = xhr.responseText;
		} else {
			console.log("error");
		}
	}
	xhr.open('GET', '/NewWorldHotelsApp/MyController');
	xhr.send();
}

function Validate() {
	let firstName = document.getElementById("fName").value;
	let lastName = document.getElementById("lName").value;
	let addy = document.getElementById("address").value;
	let cty = document.getElementById("city").value;
	let st = document.getElementById("state").value;
	let zp = document.getElementById("zip").value;
	let phNum = document.getElementById("phone").value;
	let em = document.getElementById("email").value;
	let pa = document.getElementById("pass").value;
	let cpa = document.getElementById("confirmPass").value;
	console.log("Entered createCust, " + firstName + ", pass: " + pa);
	
	let fName = document.forms["vform"]["fName"];
	let lName = document.forms["vform"]["lName"];
	let address = document.forms["vform"]["address"];
	let city = document.forms["vform"]["city"];
	let state = document.forms["vform"]["state"];
	let zip = document.forms["vform"]["zip"];
	let phone = document.forms["vform"]["phone"];
	let email = document.forms["vform"]["email"];
	let pass = document.forms["vform"]["pass"];
	let confirmPass = document.forms["vform"]["confirmPass"];
	
	let fNameError = document.getElementById("fNameError");
	let lNameError = document.getElementById("lNameError");
	let addyError = document.getElementById("addyError");
	let ctyError = document.getElementById("ctyError");
	let stError = document.getElementById("stError");
	let zipError = document.getElementById("zipError");
	let emError = document.getElementById("emError");
	let phError = document.getElementById("phError");
	let paError = document.getElementById("paError");
	let cpaError = document.getElementById("cpaError");
	let mismatchError = document.getElementById("mismatchError");
	
	fName.addEventListener("blur", fNameVerify, true);
	lName.addEventListener("blur", lNameVerify, true);
	address.addEventListener("blur", addressVerify, true);
	city.addEventListener("blur", cityVerify, true);
	state.addEventListener("blur", stateVerify, true);
	zip.addEventListener("blur", zipVerify, true);
	phone.addEventListener("blur", phoneVerify, true);
	email.addEventListener("blur", emailVerify, true);
	pass.addEventListener("blur", passVerify, true);
	confirmPass.addEventListener("blur", confirmPassVerify, true);
	
	if (fName.value == "") {
		fName.style.border = "1px solid red";
		fNameError.textContent = "First name is required!";
		fName.focus();
		return false;
	}
	if (lName.value == "") {
		lName.style.border = "1px solid red";
		lNameError.textContent = "Last name is required!";
		lName.focus();
		return false;
	}
	if (address.value == "") {
		address.style.border = "1px solid red";
		addyError.textContent = "Address is required!";
		address.focus();
		return false;
	}
	if (city.value == "") {
		city.style.border = "1px solid red";
		ctyError.textContent = "City is required!";
		city.focus();
		return false;
	}
	if (state.value == "") {
		state.style.border = "1px solid red";
		stError.textContent = "State is required!";
		state.focus();
		return false;
	}
	if (zip.value == "") {
		zip.style.border = "1px solid red";
		zipError.textContent = "Zip code is required!";
		zip.focus();
		return false;
	}
	if (phone.value == "") {
		phone.style.border = "1px solid red";
		phError.textContent = "Phone number is required!";
		phone.focus();
		return false;
	}
	if (email.value == "") {
		email.style.border = "1px solid red";
		emError.textContent = "Email address is required!";
		email.focus();
		return false;
	}
	if (pass.value == "") {
		pass.style.border = "1px solid red";
		paError.textContent = "Password is required!";
		pass.focus();
		return false;
	}
	if (confirmPass.value == "") {
		confirmPass.style.border = "1px solid red";
		cpaError.textContent = "Please confirm password!";
		fName.focus();
		return false;
	}
	if (pass.value != confirmPass.value) {
		pass.style.border = "1px solid red";
		confirmPass.style.border = "1px solid red";
		mismatchError.textContent = "Passwords do not match!";
		confirmPass.focus();
		return false;
	}

	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				document.getElementById('mainColumn').innerHTML = responseText;
			}
		}
	}
	xhr.open('POST', '/NewWorldHotelsApp/CreateCustomerServlet', true);
	xhr.send();
	
}
function fNameVerify() {
	if(fName.value !="") {
		fName.style.border = "1px solid #5E6E66";
		fNameError.innerHTML = "";
		return true;
	}
}
function lNameVerify() {
	if(lName.value !="") {
		lName.style.border = "1px solid #5E6E66";
		lNameError.innerHTML = "";
		return true;
	}
}
function addressVerify() {
	if(address.value !="") {
		address.style.border = "1px solid #5E6E66";
		addyError.innerHTML = "";
		return true;
	}
}
function cityVerify() {
	if(city.value !="") {
		city.style.border = "1px solid #5E6E66";
		ctyError.innerHTML = "";
		return true;
	}
}
function stateVerify() {
	if(state.value !="") {
		state.style.border = "1px solid #5E6E66";
		stError.innerHTML = "";
		return true;
	}
}
function zipVerify() {
	if(zip.value !="") {
		zip.style.border = "1px solid #5E6E66";
		zipError.innerHTML = "";
		return true;
	}
}
function phoneVerify() {
	if(phone.value !="") {
		phone.style.border = "1px solid #5E6E66";
		phError.innerHTML = "";
		return true;
	}
}
function emailVerify() {
	if(email.value !="") {
		email.style.border = "1px solid #5E6E66";
		emError.innerHTML = "";
		return true;
	}
}
function passVerify() {
	if(pass.value !="") {
		pass.style.border = "1px solid #5E6E66";
		paError.innerHTML = "";
		return true;
	}
}
function confirmPassVerify() {
	if(confirmPass.value !="" && confirmPass.value == pass.value) {
		pass.style.border = "1px solid #5E6E66";
		paError.innerHTML = "";
		return true;
	}
}
function SignInVal() {
	let em = document.forms["siform"]["email"];
	let pw = document.forms["siform"]["pass"];
	
	let emailError = document.getElementById("emailError");
	let passError = document.getElementById("passError");
	
	em.addEventListener("blur", eVerify, true);
	pw.addEventListener("blur", pVerify, true);
	
	if (em.value == "") {
		em.style.border = "1px solid red";
		emailError.textContent = "Email address is required!";
		em.focus();
		return false;
	}
	if (pw.value == "") {
		pw.style.border = "1px solid red";
		passError.textContent = "Password is required!";
		pw.focus();
		return false;
	}
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				document.getElementById('mainColumn').innerHTML = responseText;
			}
		}
	}
	xhr.open('POST', '/NewWorldHotelsApp/SignInServlet', true);
	xhr.send();
}
function eVerify() {
	if(em.value !="") {
		em.style.border = "1px solid #5E6E66";
		emailError.innerHTML = "";
		return true;
	}
}
function pVerify() {
	if(pw.value !="") {
		pw.style.border = "1px solid #5E6E66";
		passError.innerHTML = "";
		return true;
	}
}
