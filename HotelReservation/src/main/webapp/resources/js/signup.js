/**
 * 
 */
function autoHypenPhone(str){
	str = str.replace(/[^0-9]/g, '');
	var tmp = '';
	if( str.length < 4){
		return str;
	}else if(str.length < 7){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	}else if(str.length < 11){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
        tmp += '-';
        tmp += str.substr(6);
        return tmp;
	}else{              
		tmp += str.substr(0, 3);
		tmp += '-';
        tmp += str.substr(3, 4);
        tmp += '-';
        tmp += str.substr(7);
        return tmp;
	}
	return str;
}
//phone form에서 키 입력이 됬을 때, 동작
function setPhoneForm(phone){
	var _val = phone.value.trim();
    phone.value = autoHypenPhone(_val) ;
}
// 주소 검색
function searchAddress(){
	 new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	        	var roadAddress = data.roadAddress
	        	document.getElementById('address').value = roadAddress
	        }
	    }).open();
}