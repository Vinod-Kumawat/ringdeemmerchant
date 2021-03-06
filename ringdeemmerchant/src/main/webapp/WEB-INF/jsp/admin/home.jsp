<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
    <title>Ringdeem Merchant - Admin and Merchant Portal</title>
    <!-- Custom CSS -->
    <link href="assets/libs/flot/css/float-chart.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="dist/css/style.min.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
     function setBarChartMerchantByCountry() {

    	var chart = new CanvasJS.Chart("chartContainer",
    	{
    		title: {
    			text: "Graph by Number of Merchant By Country"
    		},
    		dataPointMaxWidth: 15,
    		data: [
    		{
    			type: "column",
    			dataPoints: [
    			 <c:if test="${not empty graphMerchantByCountry}">
                  <c:forEach var="mercahnt" items="${graphMerchantByCountry}">
    				{ label: "${mercahnt.key}", y: ${mercahnt.value} },
    			</c:forEach>
    			</c:if>
    			]
    		}
    		]
    	});
    	chart.render();
    }

      function setBarChartProductByMerchant() {

        	var chart = new CanvasJS.Chart("chartContainer",
        	{
        		title: {
        			text: "Graph by Number of Product Each Merchant"
        		},
        		dataPointMaxWidth: 15,
        		data: [
        		{
        			type: "column",
        			dataPoints: [
        			 <c:if test="${not empty graphProductByMercahnt}">
                      <c:forEach var="product" items="${graphProductByMercahnt}">
        				{ label: "${product.key}", y: ${product.value} },
        			</c:forEach>
        			</c:if>
        			]
        		}
        		]
        	});
        	chart.render();
        }

        function setBarChartProductSellM() {

                	var chart = new CanvasJS.Chart("chartContainer",
                	{
                		title: {
                			text: "Graph by Total Product Sell of each Merchant"
                		},
                		dataPointMaxWidth: 15,
                		data: [
                		{
                			type: "column",
                			dataPoints: [
                			 <c:if test="${not empty graphProductSellM}">
                              <c:forEach var="product" items="${graphProductSellM}">
                				{ label: "${product.key}", y: ${product.value} },
                			</c:forEach>
                			</c:if>
                			]
                		}
                		]
                	});
                	chart.render();
                }

                function setBarChartBusinessM() {

                	var chart = new CanvasJS.Chart("chartContainer",
                	{
                		title: {
                			text: "Graph by Total Business(Amount) of each Merchant"
                		},
                		dataPointMaxWidth: 15,
                		data: [
                		{
                			type: "column",
                			dataPoints: [
                			 <c:if test="${not empty graphBusinessSellM}">
                              <c:forEach var="product" items="${graphBusinessSellM}">
                				{ label: "${product.key}", y: ${product.value} },
                			</c:forEach>
                			</c:if>
                			]
                		}
                		]
                	});
                	chart.render();
                }
    </script>


    <script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>

<body>
    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <header class="topbar" data-navbarbg="skin5">
            <!-- Vinu: header-nav bar include here  -->
            <jsp:include page="../util/header-navbar.jsp"></jsp:include>
        </header>
        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <aside class="left-sidebar" data-sidebarbg="skin5">
            <!-- Sidebar scroll-->
				 <!-- Vinu: side bar include here  -->
				 <jsp:include page="../util/sidebar.jsp"></jsp:include>
            <!-- End Sidebar scroll-->
        </aside>
        <!-- ============================================================== -->
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
             <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-12 d-flex no-block align-items-center">
                        <h4 class="page-title">Dashboard</h4>
                        <div class="ml-auto text-right">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Library</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- ============================================================== -->
                
				 <!-- Vinu: below you can add your card, static, form , data table  -->
				 <jsp:include page="../util/card.jsp"></jsp:include>
				 <jsp:include page="../util/chart.jsp"></jsp:include>

				<!--
               -->
                
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- footer -->
            <!-- ============================================================== -->
            <footer class="footer text-center">
                Designed and Developed by <a href="www.vinodkumawat.in">@Vinu</a>.
            </footer>
            <!-- ============================================================== -->
            <!-- End footer -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <script src="dist/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="dist/js/custom.min.js"></script>
    <!--This page JavaScript -->
    <!-- <script src="dist/js/pages/dashboards/dashboard1.js"></script> -->
    <!-- Charts js Files -->
    <script src="assets/libs/flot/excanvas.js"></script>
    <script src="assets/libs/flot/jquery.flot.js"></script>
    <script src="assets/libs/flot/jquery.flot.pie.js"></script>
    <script src="assets/libs/flot/jquery.flot.time.js"></script>
    <script src="assets/libs/flot/jquery.flot.stack.js"></script>
    <script src="assets/libs/flot/jquery.flot.crosshair.js"></script>
    <script src="assets/libs/chart/jquery.peity.min.js"></script>
    <script src="assets/libs/chart/matrix.charts.js"></script>
    <script src="assets/libs/chart/jquery.flot.pie.min.js"></script>
    <script src="assets/libs/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
    <script src="assets/libs/chart/turning-series.js"></script>
    <script src="dist/js/pages/chart/chart-page-init.js"></script>



</body>

</html>