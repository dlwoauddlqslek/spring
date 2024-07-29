/* 
data.go.kr
활용신청 후 사용가능
*/

// 1. 부평구 주유소 현황
console.log('api1()');
api1();
function api1(){
    $.ajax({
        async:false,
        method:"get",
        url:"https://api.odcloud.kr/api/15102672/v1/uddi:5e2a4b30-28fb-4e8f-bc44-9a6db8a6a8db?page=1&perPage=39&serviceKey=SlTxNRtgwLPvrHOtm6W0lVuW8I22bL7q6t7hhm7xwH2nPDclcE6leS2UhNPAbmVPn%2B2Zb9Zf3MLX0d9VJZOWsw%3D%3D",
        success: r=>{console.log(r);
            let dataArray=r.data;
            console.log(dataArray);

            let html='';
            dataArray.forEach( data =>{
                html+= `<tr> 
                        <td>${data['상호']}</td>
                        <td>${data['전화번호']}</td>
                        <td>${data.주소}</td>
                        </tr>
                        `
            })
            document.querySelector('.api1').innerHTML=html;
        }
    })
}
api2();
// 2. 동구 약국 현황
function api2(){
    $.ajax({
        async:false,
        method:"get",
        url:"https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=35&serviceKey=SlTxNRtgwLPvrHOtm6W0lVuW8I22bL7q6t7hhm7xwH2nPDclcE6leS2UhNPAbmVPn%2B2Zb9Zf3MLX0d9VJZOWsw%3D%3D",
        success: r =>{console.log(r);
            let dataArray=r.data;
            console.log(dataArray);

            let html='';
            dataArray.forEach(data =>{
                html+=`<tr> 
                        <td>${data['약국명']}</td>
                        <td>${data['전화번호']}</td>
                        <td>${data['소재지도로명주소']}</td>
                        </tr>
                        `
            })
            document.querySelector('.api2').innerHTML=html;
            // 카카오지도 출력 함수에 데이터 매개변수 전달
            api3(r.data)
        }
    })
}

// 3. 카카오 지도 api -> 카카오 개발자센터
/* 
내 어플리케이션
앱키, 플랫폼 
*/
function api3(data){


var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(37.4562557, 126.7052062), //지도의 중심좌표.
	level: 5 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

// 마커를 표시할 위치와 title 객체 배열입니다 
    // -1. 리스트명.forEach(); : 리스트내 요소를 하나씩 반환해서 반복
    // -2. 리스트명.map()      : 리스트내 요소를 하나씩 반환해서 반복, 실행문 결과값을 반환 가능, 배열로 최종 반환
var positions = data.map( d =>{
        let location={title:d.약국명, latlng:new kakao.maps.LatLng(d['위도'],d['경도'])}
        return location;
})
console.log(positions);

// 마커 이미지의 이미지 주소입니다
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

for (var i = 0; i < positions.length; i ++) {
    
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image : markerImage // 마커 이미지 
    });
}
}