console.log('header.js')

doLoginCheck();
function doLoginCheck(){
    $.ajax({
        method:'get',
        url:"/member/login/check",
        success:(result)=>{console.log(result);
        if(result==''){console.log('비로그인')}
        else{console.log('로그인')}
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