console.log('getall.js');
getAll();
function getAll(){
    let list=document.querySelector('#productListBox')
    let html='';

    $.ajax({
        async:false, method:'get', url:'/product/getAll',
        success: r=>{console.log(r);
            // 여러개 제품, r: 여러개 제품들 리스트, product: 각 제품,
            // fileNames: 각 제품들의 이미지들 리스트, imgName: 각 이미지 1개
            r.forEach(product =>{
            html+=`<div class="productBox">`
            // 여러개 이미지 파일
            product.fileNames.forEach(imgName =>{
            html+=`<img src="/upload/${imgName}"/>`
        })
            html+=`</div>`
        })
    },
        error: e=>{console.log(e);}
    })
    list.innerHTML=html;
}
