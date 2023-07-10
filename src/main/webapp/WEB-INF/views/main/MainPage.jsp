<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기상건축도우미</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<link href="/build/resources/css/style.css" rel="stylesheet">
<!-- kakao 로그인 / 로그아웃 -->
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<style>

.abc{	justify-content: flex-end; }
    
  

</style>
<body>

<nav class="navbar navbar-expand-md fixed-top my-navbar ">
  <div class="container-fluid top">
    <a class="navbar-brand" href="#">기상건축도우미</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse abc" id="navbarCollapse">
      <ul class="navbar-nav  mb-2 mb-md-0 fjgkd">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#videoinbackground">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#asd1">사이트 가이드</a>
        </li>
        <li class="nav-item mr-3">
          <a class="nav-link" href="#asd2">채용정보</a>
        </li>
        <c:if test="${sessionScope.Email != null}">
        <li class="nav-item">
          <button class="btn btn-warning" id="outforlog" onclick="unlinkApp(),location='/build/logout'" style="FONT-WEIGHT: 900;">
          로그아웃</button>
        </li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>



<%-- 여기서부터 메인 화면 나오는 곳 --%>
<main>
  <div class=" rounded">
    
    <!-- ======= videoinbackground Start ======= -->
    <section id="videoinbackground" class="d-flex align-items-center">
        <video autoplay muted loop id="myVideo">
            <source src="/build/resources/upload/city-at-night.mp4" type="video/mp4">
        </video>
        <h1>기상건축도우미</h1>
        <h3>기상데이터를 활용한 건축도우미 서비스, 간편하게 카카오로 이용해보세요.</h3>
        <div>
<c:if test="${sessionScope.Email == null}">
<a id="kakao-login-btn"></a>
</c:if>
<c:if test="${sessionScope.Email != null}">	        
        <button class="btn btn-primary mr-4" onclick="location='/build/conmap'">시공</button>
        <button class="btn btn-primary" onclick="location='/build/demap'">설계</button>        
</c:if>        
	</div>
</section>
<script type="text/javascript">
// 로그아웃 : unlink로 연결 해제 및 session을 같이 지워준다.
  function unlinkApp() {
    Kakao.API.request({
      url: '/v1/user/unlink',
      success: function(res) {
        alert('success: ' + JSON.stringify(res))
      },
      fail: function(err) {
        alert('fail: ' + JSON.stringify(err))
      },
    })
  }
</script>
 
<script type="text/javascript">
// 로그인 : 정보 동의 nickname(필수) + 카카오계정[이메일] (선택), 성별 (선택)
	Kakao.init('${key}');
	console.log(Kakao.isInitialized());
	Kakao.Auth.createLoginButton({
    	container: '#kakao-login-btn',
    	success: function(authObj) {
      		Kakao.API.request({
        		url: '/v2/user/me',
        		// url: 'http://localhost:8080/build/kakao',
        		success: function(result) {
          			$('#result').append(result);
          			id = result.id;
          			connected_at = result.connected_at;
          			kakao_account = result.kakao_account;
          			$('#result').append(kakao_account);
          			resultdiv="<h2>로그인 성공 !!";
          			resultdiv += '<h4>id: '+id+'<h4>';
          			resultdiv += '<h4>connected_at: '+connected_at+'<h4>';
          			console.log(kakao_account);
          			nick = kakao_account.nickname;
          			var email = kakao_account.email;
          			
          			//window.open('/build/session?email='+email ,"new" 
          			//,"width=370, height=360, resizable=no, scrollbars=no, status=no, location=no, directories=no;");
          			
          			resultdiv += '이메일 : ' + email;
          			resultdiv += '<h4>nick: '+nick+'<h4>';
          			$('#result').append(resultdiv);
          			$.ajax({
                        url : '/build/session',
                        data : {email : email},
                        success : function(result){
                           // $("#kakao-login-btn").css("display","none");
                           // $("#outforlog").css("display","block");
                        	location.reload();
                        }
                     });
        		},
        		fail: function(error) {
          			alert('login success, but failed to request user information: ' +JSON.stringify(error))
        	},
		})
    },
    fail: function(err) {
      alert('failed to login: ' + JSON.stringify(err))
    },
    scope: 'account_email,gender' 
  })
</script>

    
  <!-- videoinbackground End -->
  
  
  
<section  class="container" id="asd1" style="width:100%;height:920px;">
<h2 class="pt-5"></h2>
<h1 class="text-center pt-5"> 사이트 가이드 </h1>
<h4 class="text-center pt-4"> 기상건축도우미에서 제공하고 있는 시공/설계의 데이터를 기상공공데이터를 활용해서<br /> 건축업종 사용자에게 안전한 솔루션을 제공하고 있습니다. </h4>
<div class="row justify-content-center pt-5">
<!-- 시공 -->  
<div class="card pt-3 " style="width: 18rem;height: 25rem;margin-right:20px">
<img src="/build/resources/upload/mainimg/sigo.jpg" class="card-img-top" alt="...">
<div class="card-body text-center">
<p class="card-text">시공페이지에서 기상데이터를 사용하여, 도움을 제공해주고 있습니다. 자세한 내용은 아래 버튼을 눌러 사진과 같이 확인하실 수 있습니다.</p>

<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvas1" aria-controls="offcanvasRight">
시공 페이지 알아보기
</button>
</div>
</div>

<div class="offcanvas offcanvas-end asd" tabindex="-1" id="offcanvas1" aria-labelledby="offcanvasRightLabel" style="width:1550px">
  <div class="offcanvas-header">
    <h5 id="offcanvasRightLabel">시공</h5>
    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    <div id="carouselExampleCaptions" class="carousel carousel-dark slide" data-bs-ride="carousel" style="width:1500px">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="/build/resources/upload/mainimg/con1.PNG" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>카카오맵을 이용하여 시공할 지역을 선택하세요.</h5>
        <p>오른쪽 지역버튼을 사용하면 간편하게 시군구 위치로 이동해줍니다.</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="/build/resources/upload/mainimg/con2.PNG" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>시공할 지역을 선택하고 이동하면 날짜와 공정을 설정해주는 페이지로 이동합니다.</h5>
        <p>원하는 날짜 아래에 텍스트 박스에 공정을 입력하고 버튼을 누르면 안전지침을 정보를 제공합니다.</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="/build/resources/upload/mainimg/con3.PNG" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>기상데이터를 활용해 건축물의 안전지침이 나타납니다.</h5>
        <p>기상API를 사용해서 가져온 데이터로 기상에 맞는 안전 지침을 제공합니다.</p>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
  </div>
</div>
<!-- 시공 끝 -->

<!-- 설계 --> 
<div class="card pt-3" style="width: 18rem;height: 25rem">
<img src="/build/resources/upload/mainimg/surge.png" class="card-img-top" alt="...">
<div class="card-body  text-center">
<p class="card-text">설계페이지에서 기상데이터를 사용하여, 도움을 제공해주고 있습니다. 자세한 내용은 아래 버튼을 눌러 사진과 같이 확인하실 수 있습니다.</p>
<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvas2" aria-controls="offcanvasRight">
설계 페이지 알아보기
</button>
</div>
</div>

<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvas2" aria-labelledby="offcanvasRightLabel" style="width:1550px">
  <div class="offcanvas-header">
    <h5 id="offcanvasRightLabel">설계</h5>
    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
  
    <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel" style="width:1500px">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 01"></button>
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 02"></button>
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 03"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active" data-bs-interval="8000">
      <img src="/build/resources/upload/mainimg/de1.PNG" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>카카오맵을 이용하여 설계할 지역을 선택하세요.</h5>
        <p>오른쪽 지역버튼을 사용하면 간편하게 시군구 위치로 이동해줍니다.</p>
      </div>
    </div>
    <div class="carousel-item"  data-bs-interval="3000">
      <img src="/build/resources/upload/mainimg/de2.PNG" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>설계할 지역을 선택하면, 해당 지역의 평균기상 데이터가 나타납니다.</h5>
        <p>각각의 지역의 평균기상 데이터는 기상청 데이터를 활용하였습니다.</p>
      </div>
    </div>
    <div class="carousel-item" data-bs-interval="3000">
      <img src="/build/resources/upload/mainimg/de3.PNG" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>설계 지침 버튼을 누르면, Chat-GPT를 사용하여 설계 지침을 볼 수 있습니다.</h5>
        <p>다른 설계 지침을 원하는 경우, 다시 한번 설계 지침 버튼을 누르시면 다시 설정해줍니다.</p>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>  

  </div>
</div>
</div>
</section>
  
 
  
  
  <section id="asd2" style="width:100%;height:900px;background-image: url('/build/resources/upload/employment.jpg');background-size: cover;">
  <h1> 채용정보 </h1>
  <h2> 최신 건축업종관련 정보를 제공해드리고 있습니다. </h2>
  <h4 class="st"> 더 많은 채용정보를 희망하시면 최신 정보 더보기를,</h4>
  <h4 class="nd"> 자세한 정보를 보고 싶으시다면 희망하는 채용 정보를 눌러 원하는 정보를 체험하실 수 있습니다. </h4>
  
  <a class="btn btn-lg btn-primary" role="button" onclick="location='/build/career'">보러가기</a>
  
  
  </section>
    
  </div>
</main>
<script>
window.addEventListener('DOMContentLoaded', function() {
	  window.addEventListener('scroll', function() {
	    var scroll = window.pageYOffset || document.documentElement.scrollTop;
	    var navbar = document.querySelector('.my-navbar');
	    if (scroll > 1) {
	      navbar.style.background = '#3F3E3D';
	    } else {
	      navbar.style.background = 'transparent';
	    }
	  });
	});
</script>


<!-- 자바스크립트 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>