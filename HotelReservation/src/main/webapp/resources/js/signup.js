function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}
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
function idDuplicateCheck(input){
	var dupck = document.getElementById('dupck')
	
	$.ajax({
		url:`${getContextPath()}/dupCheck`,
		type:"POST",
		data:{userId: input.value},
		success: function(result){
			if(result == true){
				dupck.value = 1
				input.className = "form-control is-valid"
			}else{
				dupck.value = 0
				input.className = "form-control is-invalid"
			}
		}
	});
}
function idValidCheck(input){
	var regex = /^[a-z][a-z0-9]{7,15}$/;
	var idcheckBtn = document.getElementById('idcheck')
	var dupck = document.getElementById('dupck')
	
	if(regex.test(input.value)){
		input.className = 'form-control is-valid'
		idcheckBtn.className = 'btn btn-dark btn-sm'
	}else if(input.value.length == 0){
		input.className = 'form-control'
		idcheckBtn.className = 'btn btn-dark btn-sm disabled'
	}else{
		input.className = 'form-control is-invalid'
		idcheckBtn.className = 'btn btn-dark btn-sm disabled'
	}
	dupck.value = 0
}
function passwordValid(input){
	var password = document.getElementById('password')
	var regex = /^^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{12,20}$/;
	console.log(input.value)
	if(regex.test(input.value)){
		input.className = 'form-control is-valid'
		return true
	}else if(input.value.length == 0){
		input.className = 'form-control'
	}else{
		input.className = 'form-control is-invalid'
	}
	return false
}
function passwordCheckValid(input){
	var password = document.getElementById('password')
	
	if(passwordValid(input)){
		if(password.value != input.value){
			input.className = 'form-control is-invalid'
		}else{
			input.className = 'form-control is-valid'
		}
	}else if(input.value.length == 0){
		input.className = 'form-control'
	}else{
		input.className = 'form-control is-invalid'
	}
}
