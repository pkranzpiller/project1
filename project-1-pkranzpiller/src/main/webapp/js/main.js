//let login = [];
//let authenticated = false;
//let loginElement = document.querySelector('#login');
//let requests = [];
//
//let username;
//let password;
//
//function onSubmit(){
//}

//function getLogin(){
//	let xhr = new HMLHttpRequest();
//	xhr.open('get', 'api/main/login');
//	xhr.onload = function(){
//		login = JSON.parse(xhr.responseText);
//	};
//	xhr.send();
//}

//function generateTables(){
//	let xhr = new HMLHttpRequest();
//	xhr.open('get', 'api/main/getRequests');
//	xhr.onload = function(){
//		requests = JSON.parse(xhr.responseText);
////		document.write(requests[0]);
//	};
//	xhr.send();
//

//function generateTables(){
//	console.log("rawr");
//}

//function generateTables(){
//	
//		var xmlhttp = new XMLHttpRequest();
//		xmlhttp.onreadystatechange = function() {
//			if (this.readyState == 4 && this.status == 200) {
//				var myBooks = JSON.parse(this.responseText);
//				//document.getElementById("data").innerHTML = data[0].name;
//				//----------------build table---------------------------------
//				
//				
//				// EXTRACT VALUE FOR HTML HEADER. 
//				// ('Book ID', 'Book Name', 'Category' and 'Price')
//				var col = [];
//				for (var i = 0; i < myBooks.length; i++) {
//				    for (var key in myBooks[i]) {
//				        if (col.indexOf(key) === -1) {
//				            col.push(key);
//				        }
//				    }
//				}
//
//				// CREATE DYNAMIC TABLE.
//				var table = document.createElement("table");
//
//				// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.
//
//				var tr = table.insertRow(-1);                   // TABLE ROW.
//
//				for (var i = 0; i < col.length; i++) {
//				    var th = document.createElement("th");      // TABLE HEADER.
//				    th.innerHTML = col[i];
//				    tr.appendChild(th);
//				}
//
//				// ADD JSON DATA TO THE TABLE AS ROWS.
//				for (var i = 0; i < myBooks.length; i++) {
//
//				    tr = table.insertRow(-1);
//
//				    for (var j = 0; j < col.length; j++) {
//				        var tabCell = tr.insertCell(-1);
//				        tabCell.innerHTML = myBooks[i][col[j]];
//				    }
//				}
//
//				// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
//				var divContainer = document.getElementById("data");
//				divContainer.innerHTML = "";
//				divContainer.appendChild(table);
//				
//				
//				
//				
//				
//				//-------------------------end building table---------------------------------
//				
//			 	}
//		};
//		xmlhttp.open("POST",'http://localhost:8080/project-1-pkranzpiller/api/main/getRequests', true);
//		xmlhttp.send();
//	}




function generateTables(){
	const Http = new XMLHttpRequest();
	const url = 'http://localhost:8080/project-1-pkranzpiller/api/main/getRequests';
	Http.open("POST", url);
	Http.send();
	
	Http.onreadystatechange=(e)=>{
//		console.log("first log: ")
		console.log(Http.responseText);
		
		if (Http.readyState == 4 && Http.status == 200) {
			var myBooks = JSON.parse(Http.responseText);
			console.log("books is: " + myBooks);
			//document.getElementById("data").innerHTML = data[0].name;
			//----------------build table---------------------------------
			
			
			// EXTRACT VALUE FOR HTML HEADER. 
			// ('Book ID', 'Book Name', 'Category' and 'Price')
			var col = [];
			for (var i = 0; i < myBooks.length; i++) {
			    for (var key in myBooks[i]) {
			        if (col.indexOf(key) === -1) {
			            col.push(key);
			        }
			    }
			}

			// CREATE DYNAMIC TABLE.
			var table = document.createElement("table");

			// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

			var tr = table.insertRow(-1);                   // TABLE ROW.

			for (var i = 0; i < col.length; i++) {
			    var th = document.createElement("th");      // TABLE HEADER.
			    th.innerHTML = col[i];
			    tr.appendChild(th);
			}

			// ADD JSON DATA TO THE TABLE AS ROWS.
			for (var i = 0; i < myBooks.length; i++) {

			    tr = table.insertRow(-1);

			    for (var j = 0; j < col.length; j++) {
			        var tabCell = tr.insertCell(-1);
			        tabCell.innerHTML = myBooks[i][col[j]];
			    }
			}

			// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
			var divContainer = document.getElementById("data");
			divContainer.innerHTML = "";
			divContainer.appendChild(table);
			
			//-------------------------end building table---------------------------------
			
		 	}
		
	}
	
}






