let urlParams = new URL(location.href).searchParams;
let currentBno = urlParams.get("bno") //글번호
loginCheck();
function loginCheck(){
    $.ajax({
        async : false,
        method : "GET",
        url : "/member/my/info",
        success : response => {
            if (!response.id){
                alert("먼저 로그인해 주세요.")
                location.href="/member/login"
            }
        }
    })
}
function doBoardEdit(){
    let bcno=document.querySelector('#bcno').value;
    let btitle=document.querySelector('#btitle').value;
    let bcontent=document.querySelector('#bcontent').value;
    $.ajax({
        method:'put',
        url:"/board/edit",
        data:{bcno:bcno,btitle:btitle,bcontent:bcontent,bno:currentBno},
        success:result =>{
            if(result){
                alert("수정 성공")
                location.href=`/board/getread?bno=${currentBno}`
            }else{alert("본인의 작성글만 수정할 수 있습니다")}
        }
    })

}