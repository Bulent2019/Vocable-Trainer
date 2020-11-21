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