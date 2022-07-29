const SERVER_ADDRESS = 'http://localhost:8080'

function setHotelImg(input){
    var file = input.files[0]

    var img = document.getElementById("img")
    img.src = URL.createObjectURL(file)
}
function searchAddress(){
	 new daum.Postcode({
	        oncomplete: function(data) {
	        	var roadAddress = data.roadAddress
	        	document.getElementById('address').value = roadAddress
	        }
	    }).open();
}
function fileTransfer(){

}