console.log('boardwrite.js');
loginCheck();
getCategory();

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

function getCategory(){
    // 어디에
    let category = document.querySelector("#category");
    let html = ``;
    // 무엇을
    $.ajax({
        async:false,
        method:'get',
        url:"/board/getcategory",
        success:(result) =>{
                console.log(result);
                result.forEach(dto =>{
                    
                    html+=`<option value="${dto.bcno}">${dto.bcname}</option>`;
                })
            category.innerHTML=html;
        }
    })
}
/*
function _write(){
    //{"bcno" : bcno, "btitle" : btitle, "bcontent" : bcontent}

    let btitle = document.querySelector("#btitle").value
    let bcontent = document.querySelector("#bcontent").value
    let bcno = document.querySelector("#category").value
    let bfile=document.querySelector("#bfile").value
    let dto = {"btitle" : btitle, "bcontent" : bcontent, "bcno" : bcno}
    console.log("dto=" + dto);
    $.ajax({
        async : false,
        method : "POST",
        url : "/board/write",
        data : JSON.stringify(dto),
        contentType : "application/json",
        success : resp => {
            if (resp) {
                alert("글쓰기 완료.")
                location.href="/board/getall"
            } else {
                alert("글쓰기 오류.")
            }
        },
        error: e =>{console.log(e)}
    })
}
*/
// 2.게시물 쓰기(첨부파일을 전송하는 대용량 form)
function _write(){
    let boardWriteForm=document.querySelector('#writeForm');
    console.log(boardWriteForm)

    let boardWriteFormData=new FormData(boardWriteForm);
    console.log(boardWriteFormData)

    $.ajax({
        method:'post',
        url:"/board/write",
        data:boardWriteFormData,
        contentType:false, processData:false,
        success : resp => {
                    if (resp) {
                        alert("글쓰기 완료.")
                        location.href="/board/getall"
                    } else {
                        alert("글쓰기 오류.")
                    }
                },
                error: e =>{console.log(e)}
    })
}

