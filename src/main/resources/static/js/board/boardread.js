//1.



let urlParams = new URL(location.href).searchParams;
let currentBno = urlParams.get("bno") //글번호





boardRead();
//2. 페이지 열릴 때 출력하기
function boardRead(){ // 어디에 무엇을 {boardNo : brdNo, title : bTitle, userId : uId, bDate : writtenDate, bView : view, bContent : content}
    let boardBox = document.querySelector("#boardBox")
    let divHTML = ''
    $.ajax({
        method:'get',
        url:"/board/read",
        data:{bno:currentBno},
        success:result =>{
            divHTML = `<div class="row">카테고리 : ${result.bcname}</div>
            <div class="row">글 번호 : ${result.bno}</div>
            <div class="row">제목 : ${result.btitle}</div>
            <div class="row">내용 : ${result.bcontent}</div>
            <div class="row">아이디 : ${result.id}</div>
            <div class="row">작성일 : ${result.bdate}</div>
            <div class="row">조회수 : ${result.bview}</div>
            <div class="row">파일 : ${result.bfile}</div>
            `
            boardBox.innerHTML=divHTML;
        }
    })
}

function _delete(){
    $.ajax({
        method:'delete',
        url:"/board/delete",
        data:{bno:currentBno},
        success:result =>{
            if (result){
                alert('삭제 성공');
                location.href="/board/getall"
            }else{alert('본인의 작성글만 삭제할 수 있습니다')}
        }
    })
}

function _edit(){
    location.href=`/board/edit?bno=${currentBno}`;
}
