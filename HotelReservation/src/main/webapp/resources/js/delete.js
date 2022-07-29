/**
 * 
 */
const SERVER_ADDRESS = 'http://localhost:8080'

function reviewDelete(type, seq){
	if(confirm('리뷰를 삭제하시겠습니까?')){
		$.ajax({
			url:`${SERVER_ADDRESS}/mypage/review/delete`,
			type:'post',
			data:{seq: seq,
	            type: type
	        },
	        success: function(result){
	        	if(result){
	        		alert('리뷰가 삭제되었습니다.')
	        		location.replace(location.href)
	        	}else{
	        		alert('알 수 없는 이유로 인하여 삭제를 실패하였습니다.')
	        	}
	        }
		});
	}
}

function reserveDelete(reserve, seq){
	var now = new Date()
	var today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
	var reserveDate = new Date(reserve)

	if(today.toLocaleDateString() == reserveDate.toLocaleDateString()){
		alert('당일 예약은 취소 불가합니다.')
	}
	else{
		if(confirm('예약을 취소하시겠습니까?')){
			$.ajax({
				url:`${SERVER_ADDRESS}/mypage/reservation/delete`,
				type:'post',
				data:{seq: seq},
		        success: function(result){
		        	if(result){
		        		alert('예약이 취소되었습니다.')
		        		location.replace(location.href)
		        	}else{
		        		alert('알 수 없는 이유로 인하여 예약을 취소하지 못했습니다.')
		        	}
		        }
			});
		}
	}
}

function starsDelete(seq){
	if(confirm('찜하기를 취소하시겠습니까?')){
		$.ajax({
			url:`${SERVER_ADDRESS}/mypage/stars/delete`,
			type:'post',
			data:{seq: seq},
	        success: function(result){
	        	if(result){
	        		alert('찜하기가 취소되엇습니다.')
	        		location.replace(location.href)
	        	}else{
	        		alert('알 수 없는 이유로 인하여 취소를 실패하였습니다.')
	        	}
	        }
		});
	}
}