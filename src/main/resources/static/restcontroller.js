console.log('restcontroller.js');

function rest3get(){
    console.log('rest3get');
    let option={
        url:"/example/rest3?key=qwe",
        method:'get',
        success:function(response){
            console.log(response);
        }
    }
    $.ajax(option);
}
function rest3post(){
    console.log('rest3post');
    $.ajax({
        url:"/example/rest3?key=qwe",
        method:"post",
        success:function(response){
            console.log(response);
        }
    })
}
function rest3put(){

    let value=document.querySelector('#value').value
    console.log('rest3put');
    $.ajax({
        url:"/example/rest3",
        method:"put",
        data:{'key':value},
        success:function(response){
            console.log(response);
        }
    })
}
function rest3delete(){
    let value=document.querySelector('#value').value
    console.log('rest3delete');
    $.ajax({
        url:"/example/rest3",
        data:{'key':value},
        method:"delete",
        success:function(response){
            console.log(response);
        }
    })
}