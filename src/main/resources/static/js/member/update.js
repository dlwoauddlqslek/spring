myInfo()
function myInfo(){
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

function doUpdate(){
    let newName=document.querySelector('#newName').value;
    let pw=document.querySelector('#pw').value;
    let newPw=document.querySelector('#newPw').value;
    let newPhone=document.querySelector('#newPhone').value;
    
    $.ajax({
        method:'put',
        url:"/member/update",
        data:{newName:newName,pw:pw,newPw:newPw,newPhone:newPhone},
        success:(result)=>{
            if(result){
                alert('수정완료');
                location.href="/"
            }else{alert('수정실패')}
        }
    })
}