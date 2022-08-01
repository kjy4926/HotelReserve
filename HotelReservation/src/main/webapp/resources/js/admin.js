const SERVER_ADDRESS = 'http://localhost:8080'

function setImg(input){
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

function roomAddConfirm(){
	if(confirm('방을 등록하시겠습니까?')){
		alert('방이 등록되었습니다.')
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
function roomChangeConfirm(){
	if(confirm('방을 수정하시겠습니까?')){
		alert('방이 수정되었습니다.')
		return true;
	}
	return false;
}
function hotelDeleteConfirm(seq){
	if(confirm('호텔을 삭제하시겠습니까?')){
		$.ajax({
			url:`${SERVER_ADDRESS}/admin/hotel/delete`,
			type:'post',
			data:{seq: seq},
		       success: function(result){
		       	if(result){
		       		alert('호텔이 삭제되었습니다.')
		       		location.href=`${SERVER_ADDRESS}/admin/hotel`
		       	}else{
		       		alert('알 수 없는 이유로 인하여 삭제하지 못했습니다.')
		       	}
		       }
		});
	}
}
function roomDeleteConfirm(hseq, rseq){
	if(confirm('방을 삭제하시겠습니까?')){
		$.ajax({
			url:`${SERVER_ADDRESS}/admin/room/delete`,
			type:'post',
			data:{seq: rseq},
	        success: function(result){
	        	if(result){
	        		alert('방이 삭제되었습니다.')
	        		location.href=`${SERVER_ADDRESS}/admin/room/${hseq}`
	        	}else{
	        		alert('알 수 없는 이유로 인하여 삭제하지 못했습니다.')
	        	}
	        }
		});
	}
}