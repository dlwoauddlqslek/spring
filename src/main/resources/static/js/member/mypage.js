console.log('mypage.js');
myPage()
function myPage(){console.log('myPage()');
    $.ajax({
        method:'get',
        url:"/member/my/info",
        success:(result)=>{console.log(result);
            if(result==''){
                alert('로그인 후 이용가능')
                location.href='/member/login'}
            document.querySelector('#id').innerHTML=result.id;
            document.querySelector('#name').innerHTML=result.name;
            document.querySelector('#email').innerHTML=result.email;
            document.querySelector('#phone').innerHTML=result.phone;
        }
    })
}