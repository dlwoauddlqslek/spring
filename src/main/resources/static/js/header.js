console.log('header.js')

doLoginCheck();
function doLoginCheck(){
    $.ajax({
        async:false,
        method:'get',
        url:"/member/login/check",
        success:(result)=>{console.log(result);
            let=html='';
        if(result!=''){console.log('로그인')
            html+=`<li class="nav-item">${result.id} 님</li>
                    <li class="nav-item"><a class="nav-link" href="#" onclick="doLogout()">로그아웃</a></li>
                    <li class="nav-item"><a class="nav-link" href="/member/mypage">마이페이지</a></li>`
        }
        else{console.log('비로그인')
            html+=`<li class="nav-item"><a class="nav-link" href="/member/signup">회원가입</a></li>
                    <li class="nav-item"><a class="nav-link" href="/member/login">로그인</a></li>`
        }
        document.querySelector('#loginMenu').innerHTML=html;
        }
    })
}

function doLogout(){console.log('doLogout()')
    $.ajax({
        method:'get',
        url:"/member/logout",
        success:(result)=>{console.log(result);
        location.href="/";
        }
    })
}