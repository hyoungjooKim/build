<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개발환경</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body style="background-color: #383C3F;">
<h1 class="text-center pb-1" style="color: white;"><b>개발 환경</b></h1>
<h4 class="text-center" style="color: white;"><b>개발자 : 김형주 , 김창희</b></h4>
<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        <strong>개발자 정보</strong>
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>김형주</strong><br /> 
        <strong>연락처 : </strong>010-6889-1674<br />
        <strong>이메일 : </strong>kim167429@gmail.com<br /><br />
        
        <strong>김창희</strong><br /> 
        <strong>연락처 : </strong>010-8985-2589<br />
        <strong>이메일 : </strong>rambo71025453@gmail.com 
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingTwo">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
        <strong>협업툴</strong>
      </button>
    </h2>
    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong> Github / Google docs / Discode</strong> </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingThree">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
        <strong>개발툴</strong>
      </button>
    </h2>
    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>Java(JDK) : 11/ Spring MVC : Spring 3/ Tomcat : 9.0.46/ <br />
        SQL developer : 22.2.1.234/ Oracle EX : 19c/ <br />
        Bootstrap : 5.2.3/ Kakao API( login / kakao map ) / <br />
        공공데이터 API (기상데이터/ 건축업 채용정보) / Chat-GPT API/ <br />
		Geojson (행정구역 구분)
		</strong></div>
    </div>
  </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>