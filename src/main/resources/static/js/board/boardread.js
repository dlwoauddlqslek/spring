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
            <div class="row">첨부파일 : ${result.bfile==null?"":result.bfile.split('_')[1]}<a href="/file/download?filename=${result.bfile}">${result.bfile==null?"":"다운로드"}</a></a></div>
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

// 댓글쓰기
function onReplyWrite(){console.log('onReplyWrite()');
    // 1.
    let brcontent = document.querySelector('.brcontent').value
    
   
    let info={brindex:0, //댓글분류, 0이면 상위댓글
         brcontent:brcontent, bno:currentBno // 현재 보고 있는 게시물 번호
    } 
    console.log(info);
    $.ajax({
        async:false, method:'post',
        url:"/board/reply/write",
        data:JSON.stringify(info),
        contentType:"application/json",
        // contentType: false , --> contentType:multipart/form-data 첨부파일(바이너리)
        // contentType: "application/x-www-form-urlencoded" : ajax 기본값(생략시)
        success:r=>{console.log(r);
            if(r){alert('댓글 작성 완료')
                document.querySelector('.brcontent').value='';
            }
            else{alert('댓글 작성 실패 먼저 로그인 해주세요')}
        } // success end
    }) // ajax end
    brPrint();
} // f end
brPrint();
// 댓글 출력
function brPrint(){
    let brPrint=document.querySelector('.brPrint');
    let html='';
    $.ajax({
        async:false, method:'get',
        url:"/board/reply/print",
        data:{bno:currentBno},
        success:r=>{console.log(r);
            r.forEach(r=>{
                html+=`<div>${r.brcontent}</div>`
            })
            brPrint.innerHTML=html;
            
        }
    })
}
