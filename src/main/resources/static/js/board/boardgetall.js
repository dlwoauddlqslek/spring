console.log('getall.js');
getall(1,0);
function getall(page,bcno){
    console.log(page);
    console.log(bcno);
    let pageDto={};
    let board=document.querySelector('#list');
    let html='';
    $.ajax({
        async:false,
        method:'get',
        url:"/board/all", data:{page:page,bcno:bcno},
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
        pageHTML+= `<li class="page-item"><a class="page-link" onclick="getall(${page-1<1?1:page-1},${bcno})">Previous</a></li>`
        for(let current=startBtn;current<=endBtn;current++){
        pageHTML+= `<li class="page-item"><a class="page-link ${current==page?'active':''}" onclick="getall(${current},${bcno})">${current}</a></li>`
        }
        pageHTML+= `<li class="page-item"><a class="page-link" onclick="getall(${page+1>totalPage?totalPage:page+1},${bcno})">Next</a></li>`
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