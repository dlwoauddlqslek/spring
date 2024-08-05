console.log('getall.js');
// * 페이지 정보들을 관리하는 객체 , 전역변수 , 함수의 매개변수로 관리가힘듬. */
let pageInfo = { page : 1 ,  bcno : 0 , searchKey : 'btitle' ,  searchKeyword : '' }
    // 1. page : 현재페이지[기본값 1페이지] , 2.bcno : 현재카테고리[기본값 0전체보기] // 3.searchKey:현재검색필드[기본값:제목필드] 4.searchKeyword:현재검색값[기본값:공백]
// 5. 검색 상태 제거/초기화 함수
function onSearchClear(){
    // 1. 입력창 초기화
    document.querySelector('.searchKey').value = `btitle`
    document.querySelector('.searchKeyword').value=``;
    // 2. 전역변수 초기화
    pageInfo.searchKey = 'btitle';
    pageInfo.searchKeyword = '';
}
// 4. 카테고리 클릭 했을떄
function onCategory( bcno ){
    onSearchClear();
    // 1. 전역변수에 bcno 대입
    pageInfo.bcno = bcno;       console.log( '카테고리 변경 '); console.log( pageInfo );
    getall( 1 );   // 2. 새로고침 , 1페이지
} // f end
// 3. 카테고리 호출
function getCategory(){
    // 1. 어디에
   let categoryBox = document.querySelector('.categoryBox');
   // 2. 무엇을
   let html = `<div class="${ pageInfo.bcno == 0 ? 'categoryActive' : '' }"
                   style="width:50px" onclick="onCategory( 0 )"> 전체보기 </div>`
       $.ajax({
           async : false , method:'get', url:'/board/getcategory',
           success : r => { console.log(r);
               r.forEach( c => {
                   html += `<div class="${ pageInfo.bcno == c.bcno ? 'categoryActive' : '' }" style="width:50px"
                               onclick="onCategory( ${ c.bcno  } )"> ${ c.bcname } </div>`
               })
           }
       })
   // 3. 출력
   categoryBox.innerHTML = html;
} // f end
// 2. 검색 버튼을 클릭 했을때
function onSearch( ){
    // 1. 입력받고
    let searchKey =  document.querySelector('.searchKey').value;
    let searchKeyword = document.querySelector('.searchKeyword').value;
    // 2. 전역변수에 대입
    pageInfo.searchKey = searchKey;
    pageInfo.searchKeyword = searchKeyword;
    // 3. 새로고침 , 1페이지
    getall( 1 )
}
// 매개변수: page : 보고자 하는 현재 페이지번호 , 초기값 : 1 , 첫페이지를 1페이지로 하기 위해서
getall(1);
function getall(page){
    pageInfo.page=page;
    getCategory();
    let pageDto={};
    let board=document.querySelector('#list');
    let html='';
    $.ajax({
        async:false,
        method:'get',
        url:"/board/all", data:pageInfo,
        success:result =>{console.log(result);
            pageDto=result
        }
    })
    pageDto.data.forEach(result =>{
        html+=`<tr>
                <th>${result.bno}</th>
                <td><a href="/board/getread?bno=${result.bno}" >${result.btitle}</a></td>
                <td>${result.id}</td>
                <td>${result.bdate}</td>
                <td>${result.bview}</td>
            </tr>`;
    });
    board.innerHTML=html;

    let startBtn=pageDto.startBtn;//페이지 마다 시작버튼
    let endBtn=pageDto.endBtn;//페이지 마다 끝버튼

    let totalPage=pageDto.totalPage;
    let pagination=document.querySelector(".pagination");
    let pageHTML='';
        pageHTML+= `<li class="page-item"><a class="page-link" onclick="getall(${page-1<1?1:page-1})">Previous</a></li>`
        for(let current=startBtn;current<=endBtn;current++){
        pageHTML+= `<li class="page-item"><a class="page-link ${current==page?'active':''}" onclick="getall(${current})">${current}</a></li>`
        }
        pageHTML+= `<li class="page-item"><a class="page-link" onclick="getall(${page+1>totalPage?totalPage:page+1})">Next</a></li>`
    pagination.innerHTML=pageHTML;
    //current == page? 'active':'':current번째 값이 현재 페이지 값과 일치할 때 페이지버튼 활성화
}


function boardRead(bno){
    console.log("bRead")
    $.ajax({
        method : "GET",
        url : "/board/read?bno="+bno,
    })
}

function boardwrite(){
    console.log('boardwrite()');
    location.href='/board/write';
}