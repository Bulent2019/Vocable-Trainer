 document.ready(function() {
	 $('#fileImage').change(function() {
		 showImage(this);
	 });
	 
 });
 
 function showImage(fileInput) {
	 file = fileInput.files[0];
	 reader = new FileReader();
	 
	 reader.onload = function (e) {
		 $('#fileImage').attr('src', e.target.result);
	 }
	 reader.readAsDataURL(file);
 }


//var loadFile = function (event) {
//	 file = fileInput.files[0];
//	 reader = new FileReader();
//	 
//     var image = document.getElementById('output');
//     image.src = URL.createObjectURL(event.target.files[0]);
// };