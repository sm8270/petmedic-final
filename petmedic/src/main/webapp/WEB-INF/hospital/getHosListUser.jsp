<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../../header.jsp"%>
<%@ include file="../../petmedic_navbar.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/reviewList.css">
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <script src="https://kit.fontawesome.com/your-font-awesome-kit.js" crossorigin="anonymous"></script>
<title>병원목록</title>
<script>

//리뷰쓰기 이동
function revIns(val) {
	location.href = "getReserveForReview";
}
//예약내역으로 이동 
function getMyActionReserve(val) {
	location.href = "myActionReserve";
}
//찜병원으로 이동	
function getMyActionZzim(val) {
	location.href = "myActionZzim";
}



</script>
<script>
//       리뷰보이기 글자제한
      document.addEventListener("DOMContentLoaded", function () {
        var reviewElements = document.getElementsByClassName("review");
        for (var i = 0; i < reviewElements.length; i++) {
          var reviewText = reviewElements[i].textContent;
          if (reviewText.length > 100) {
            reviewElements[i].textContent = reviewText.substring(0, 100) + "...";
          }
        }
      });

//       블러처리 해제
      document.addEventListener("DOMContentLoaded", function () {
        var reviewContainer = document.getElementById("reviewContainer");
        var showMoreButton = document.getElementById("showMoreButton");

        var userid = '<%=session.getAttribute("users_id")%>';
        
        if(userid == null){
        	// Add the following code to check the user's login status
       		 var isLoggedIn = false; // Set this variable based on the user's login status
        } else {
        	var isLoggedIn = true;
        }
        
        if (isLoggedIn) {
          reviewContainer.style.filter = "none"; // Remove the blur effect
          reviewContainer.style.pointerEvents = "auto"; // Enable interaction with the review container
          showMoreButton.style.display = "none"; // Hide the "Show More" button
        }
      });
      
      function hosDetail(val){
	  		location.href = "/getHos?hos_id="+val;
	  }
      
      function listbtnhos(){
    	  location.href = "/ListHosUser";
      }
  
    </script>
    <script>
    //더보기 
  $(document).ready(function() {
    var visibleItems = 10;
    
    // Show/hide items based on the visibility count
    function toggleItemsVisibility() {
      $('.hidden-item').slice(0, visibleItems).removeClass('hidden-item');
      $('.hidden-item').slice(visibleItems).addClass('hidden-item');
      
      if ($('.hidden-item').length === 0) {
        $('#view-more-btn').hide();
      } else {
        $('#view-more-btn').show();
      }
    }
    
    // Initial toggle
    toggleItemsVisibility();
    
    // "View More" button click event
    $('#view-more-btn').click(function() {
      visibleItems += 10; // Increase the number of visible items
      toggleItemsVisibility();
    });
  });
    
  // 맨위로
  function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  // Function to show/hide the go-to-top button based on scroll position
  function handleScroll() {
    var scrollToTopBtn = document.getElementById('go-to-top-btn');
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
      scrollToTopBtn.style.display = 'block';
    } else {
      scrollToTopBtn.style.display = 'none';
    }
  }

  // Attach the scroll event listener to the window
  window.addEventListener('scroll', handleScroll);
    
    
</script>
<body>

	<div class="container structure">
		<h2 class="text-left">병원검색</h2>
		<hr class="hr-line-color">
		<div class="search-box">
		<div class="search-box-rev-around">
				<form action="getHosListUser" method="post">
					<select class="search-select" id="searchCondition"
						name="searchCondition" style="border: none;">
						<option value="hos_name">병원명</option>
						<option value="hos_addr">주소</option>
						<option value="hos_cate">카테고리</option>
					</select> <input type="text" name="searchKeyword" placeholder="검색어를 입력하세요"
						class="search-input-rev" style="border: none;" />
					<button type="submit" class="search-btn-rev-around">
						<i class="fa fa-search"></i>
					</button>
				</form>
				
				</div>
				<button type="button" onclick="listbtnhos()" class="search-button">
						전체목록
					</button>	
			</div>
		
		
		<c:set var="sti" value="0" />
		<div class="row" id="reviewContainer">
			<c:choose>
        <c:when test="${empty hospital}">
            <div class="col-md-12">
                해당되는 병원이 없습니다.
            </div>
        </c:when>
        <c:otherwise>
			<c:forEach items="${hospital}" var="hospital" varStatus="loop">
  				<div class="col-md-6 mt-sm-4 ${loop.index > 9 ? 'hidden-item' : ''}" 
  				onclick="hosDetail('${hospital.hos_id}')">
						
					<div class="revShadow review-box">
						<div class="user-info-hos-list">
							
							<div class="profileupper col-5" >
								<strong class="hos-title-style">${hospital.hos_name}</strong>
								<div class="star-rating" id="st-${sti = sti+1}">
									<script>
								       $(function(){generateStarRating(${sti}, ${hospital.hos_star});}) ;
								    </script>
								</div>
								
							</div>
							<div class="col-7">
							<img src="${pageContext.request.contextPath}/img/${hospital.hos_pic}" alt="mdo"
								class="rounded-circle hos-search-profilepic"
								onerror="this.src='${pageContext.request.contextPath}/img/dogdefault.png'" />
							</div>
						</div>
						<hr class="hr-line-color">
						<div class="review-details" style="font-size: 14px;">
							<div><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png"> ${hospital.hos_addr3}</div>
							<div><b>전문진료동물</b> - ${hospital.hos_cate}</div>
<%-- 							<div>진료비 - ${hospital.hos_price}</div> --%>
                    <div><b>진료비</b> - <fmt:formatNumber value="${hospital.hos_price}" type="number" groupingUsed="true" />원</div>
						</div>
						
					</div>
					
				</div>
				
			</c:forEach>
			</c:otherwise>
    </c:choose>
			<script>
								//이전별점
						          function generateStarRating(idx, starValue) {
						        	  	console.log('starValue', starValue);
						        	if(starValue > 0) {
							            for (var i = 1; i <= 5; i++) {
							           	  var starHtml = '';
							              starHtml += '<input type="radio" id="' + i + '-stars" name="rev_star" value="' + i + '"';
							             
								              if (i<= starValue) {
								            	  if(i== starValue) starHtml += ' checked';
									              starHtml += ' /><label for="' + i + '-stars" class="star starChk">&#9733;</label>';
								              }else{
									              starHtml += ' /><label for="' + i + '-stars" class="star">&#9733;</label>';
								              }
								         $("#st-"+idx).append(starHtml);
							            }
						        	}
						          }
				 </script>
				 
				 
		</div>
		<div class="text-center">
		<hr>
		<button class="search-button" style="width: 100%;" id="view-more-btn">더보기</button>
		</div>
		 <div id="go-to-top-btn" class="go-to-top" onclick="scrollToTop()">
		      <i class="fa fa-arrow-up"></i>
		    </div>
	</div>
	<%@ include file="../../footer.jsp"%>	
</body>
</html>