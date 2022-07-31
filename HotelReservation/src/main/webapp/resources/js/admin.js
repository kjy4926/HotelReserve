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
function hotelAddConfirm(){
	if(confirm('호텔을 등록하시겠습니까?')){
		alert('호텔이 등록되었습니다.')
		return true;
	}
	return false;
}
function hotelChangeConfirm(){
	if(confirm('호텔을 수정하시겠습니까?')){
		alert('호텔이 수정되었습니다.')
		return true;
	}
	return false;
}