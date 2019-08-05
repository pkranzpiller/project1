function generateTables(){
	const Http = new XMLHttpRequest();
	const url = 'http://localhost:8080/project-1-pkranzpiller/api/main/getRequests';
	Http.open("POST", url);
	Http.send();
	
	Http.onreadystatechange=(e)=>{
//		console.log("first log: ")
//		console.log(Http.responseText);
		
		if (Http.readyState == 4 && Http.status == 200) {
			var myBooks = JSON.parse(Http.responseText);
//			console.log("books is: " + myBooks);
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
		    		console.log(myBooks[i][col[j]] + "imagestuff");
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






