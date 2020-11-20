// freeze the top
window.onscroll = function() {myFreeze()};

var header = document.getElementById("freeze");
var sticky = header.offsetTop;

function myFreeze() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
  } else {
    header.classList.remove("sticky");
  }
}

// searching for a word
function myFunction() {
	  var input, filter, table, tr, td, i, txtValue;
		  input 	= document.getElementById("myInput");
		  filter 	= input.value.toUpperCase();
		  table 	= document.getElementById("table");
		  tr 		= table.getElementsByTagName("tr");
	  
		  for (i = 0; i < tr.length; i++) {
			  td = tr[i].getElementsByTagName("td")[0];
			  if (td) {
				  txtValue = td.textContent || td.innerText;
				  if (txtValue.toUpperCase().indexOf(filter) > -1) {
					  tr[i].style.display = "";
				  } else {
				  tr[i].style.display = "none";
	              }
	          }       
	     }
}

// upload a picture
var loadFile = function (event) {
    var image = document.getElementById('fileImage');
    image.src = URL.createObjectURL(event.target.files[0]);
};