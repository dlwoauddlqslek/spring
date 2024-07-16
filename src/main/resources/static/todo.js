//let todoList = ["밥먹기,X"];
print();
function add() {
    let todoInput = document.querySelector(`#todoInput`).value;
    console.log(todoInput);
    $.ajax({
        url: "/example/todo1?key="+todoInput,
        method: "post",
        //data: { 'content': todoInput },
        success: function (response) {
            console.log(response);
            if(response){alert(`리스트 저장 성공`);
                todoInput.value='';
                print();
            }
            else{alert('등록 실패')}
        }
    });
    
    
}



function print() {
    let todoBox = document.querySelector(`#todoBox`);
    let html = ``;

 
        let option = {
                url: "/example/todo2",
                method: "get",
                success: function (response) {
                        console.log(response);
                        for( let i = 0 ; i<response.length ; i++ ){
                            console.log( response[i] )
                            console.log( response[i].memberCode )
                            console.log( response[i].content )
                            console.log( response[i].color )
                                                    // e == 'X'는 기본값으로 등록했을 때 화이트 박스가 나오게 하기 위함.
                                html += `<div id=${response[i].color==0?'whiteBox':'blackBox'}>
                                            <span> ${response[i].content} </span>
                                            <div>
                                                <button type="button" onclick="change(${response[i].memberCode})">변경</button>
                                                <button type="button" onclick="remove(${response[i].memberCode})">삭제</button>
                                            </div>
                                        </div>`;
                            
                        }
                        todoBox.innerHTML = html;
                        }
                }
        $.ajax(option);
            }

    //  1. 배열 내 특정 인덱스[i]의 요소 삭제

    //  2. 삭제가 되면 배열의 상태가 변경되므로 배열의 상태를 다시 출력 - 화면 업데이트
    function change(i) {
        $.ajax({
         url:"/example/todo3",
         data:{'key':i},
         method:'put',
         success: function(result){
             if(result){alert('수정 성공');
                 print();
             }else{alert('수정 실패')}
         }
        })
        
     }




    function remove(i){
         $.ajax({
            url:'/example/todo4?key='+i,
            method:'delete',
            success:result =>{
                if(result){alert('삭제 성공');
                    print();
                }else{alert('삭제 실패')}
            }
         })
        
         
       
    }     


