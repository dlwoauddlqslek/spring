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
            document.querySelector('#name1').innerHTML=result.name;
            document.querySelector('#email').innerHTML=result.email;
            document.querySelector('#phone1').innerHTML=result.phone;
        }
    })
}

function doUpdate(){
    let newName=document.querySelector('#name').value;
    let pw=document.querySelector('#pw').value;
    let newPw=document.querySelector('#newPw').value;
    let newPhone=document.querySelector('#phone').value;
    let info={name:newName,pw:pw,newPw:newPw,phone:newPhone};
    $.ajax({
        async:false,
        method:'put',
        url:"/member/update",
        data:JSON.stringify(info),
        contentType:"application/json",
        success:(result)=>{
            if(result){
                alert('수정완료');
                location.href="/member/mypage"
            }else{alert('수정실패')}
        },
        error: e =>{console.log(e)}
    })
}