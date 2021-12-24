// Popup Open
function popupOpen() {
      window.scrollTo(0, 0);
      document.getElementById("editquizform").style.display = "block";
      document.getElementById("overlay").style.display = "block";
}
// Popup Close
function popupClose() {
      document.getElementById("editquizform").style.display = "none";
      document.getElementById("overlay").style.display = "none";
}
function quesPopupOpen() {
      window.scrollTo(0, 0);
      document.getElementById("addquesform").style.display = "block";
      document.getElementById("overlay").style.display = "block";
}
function quesPopupClose() {
      document.getElementById("addquesform").style.display = "none";
      document.getElementById("overlay").style.display = "none";
}
function editQuesOpen(dem) {
      window.scrollTo(0, 0);
      idpopup = "editquesform" + dem;
      document.getElementById(idpopup).style.display = "block";
      document.getElementById("overlay").style.display = "block";
}
function editQuesClose(dem) {
      idpopup = "editquesform" + dem;
      document.getElementById(idpopup).style.display = "none";
      document.getElementById("overlay").style.display = "none";
}