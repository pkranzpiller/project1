function approve(){
	console.log("approve called");
}
	
function deny(){
	console.log("deny called");
}





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
			
			
			if(myBooks === undefined || myBooks.length === 0)		//if we have stuff in the table
				col.push("empty table");							//show empty table row if no requests are stored
			else
				col.push("action")									//will use this to add approval/denial
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
		    		if(col[j] === "image"){			//to actually load images
		    			let str1 = '<img height="125" width="125" src="http://localhost:8080/project-1-pkranzpiller/api/main/getImage?imageid='
		    			let str2 = '">'
		    			myBooks[i][col[j]] = str1 + myBooks[i][col[j]] + str2;
		    		}
		    		if(col[j] === "action" && myBooks[i][col[1]] === "pending"){   // 
//		    			console.log(myBooks[i][col[1]]);
		    			var id = myBooks[i][col[3]];
//		    			console.log("id is: " + id);
		    			var btn = '<button onclick="approve()" id = ' + id + '>Approve</button> <button onclick="deny()" id = ' + (id*(-1)) +'>Deny</button>';
//		    			console.log("deny id is: " + (id*-1));
		    			myBooks[i][col[j]] = btn;
		    		}
		    		
		    		
		    		
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