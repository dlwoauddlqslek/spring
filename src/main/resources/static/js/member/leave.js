console.log('leave.js');
function doLeave(){
    let pw=document.querySelector('#pw').value;
    $.ajax({
        method:'delete',
        url:"/member/leave",
        data:{pw:pw},
        success:(result)=>{
            if(result){
                alert('탈퇴완료')
                location.href="/"
            }else{alert('비밀번호 불일치')}
        }
    })
}