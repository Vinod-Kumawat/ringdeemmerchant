<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="com.merchant.merchant.bean.Merchant"%>
<%
 // get session attribute
                 Merchant merchant=null;
                 if(null!=session.getAttribute("merchant")){
                   merchant=(Merchant)session.getAttribute("merchant");
                 }
 %>
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
    <link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/assets/images/favicon.png">
    <title>Ringdeem Merchant - Admin and Merchant Portal</title>
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/extra-libs/multicheck/multicheck.css">
    <link href="<%=request.getContextPath()%>/assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Data table end -->
        <link href="<%=request.getContextPath()%>/assets/extra-libs/DataTables/Buttons-1.7.0/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/assets/extra-libs/DataTables/Buttons-1.7.0/css/buttons.bootstrap4.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/assets/extra-libs/DataTables/Buttons-1.7.0/css/buttons.dataTables.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/assets/extra-libs/DataTables/Buttons-1.7.0/css/buttons.foundation.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/assets/extra-libs/DataTables/Buttons-1.7.0/css/buttons.jqueryui.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/assets/extra-libs/DataTables/Buttons-1.7.0/css/buttons.semanticui.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/assets/extra-libs/DataTables/DataTables-1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">

    <!-- Data table end -->

    <link href="<%=request.getContextPath()%>/dist/css/style.min.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
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
            <jsp:include page="../merchantutil/header-navbar.jsp"></jsp:include>
        </header>
        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <aside class="left-sidebar" data-sidebarbg="skin5">
            <!-- Sidebar scroll-->
            <jsp:include page="../merchantutil/sidebar.jsp"></jsp:include>
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
                        <h4 class="page-title">Transaction</h4>
                        <div class="ml-auto text-right">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Transaction </li>
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
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-12">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Transaction</h5>


                                <div class="table-responsive">
                                    <table id="zero_config" class="table table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th>User ID</th>
                                                <th>Tx Date</th>
                                                <th>Point</th>
                                                <th>Amount</th>
                                                <th>Discount Price</th>
                                                <th>ProductID</th>
                                                <th>Country</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <!-- Query data here -->
                                        <c:if test="${not empty userPointHistoryList}">

                                            <c:forEach var="tx" items="${userPointHistoryList}">
                                            <tr>
                                                <td>${tx.userId}</a></td>
                                                <td>${tx.datetime}</a></td>
                                                <td>${tx.productPoint}</td>
                                                <td>${tx.amount}</td>
                                                <td>${tx.discountprice}</td>
                                                <td>${tx.productId}</td>
                                                <td>${tx.country}</td>
                                                <td style="color:green">${tx.status}</td>
                                            </tr>
                                            </c:forEach>
                                        </c:if>
                                        <!-- Query data end -->
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>User ID</th>
                                                <th>Tx Date</th>
                                                <th>Point</th>
                                                <th>Amount</th>
                                                <th>Discount Price</th>
                                                <th>ProductID</th>
                                                <th>Country</th>
                                                <th>Status</th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- End PAge Content -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Right sidebar -->
                <!-- ============================================================== -->
                <!-- .right-sidebar -->
                <!-- ============================================================== -->
                <!-- End Right sidebar -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- footer -->
            <!-- ============================================================== -->
            <footer class="footer text-center">
                Developed by <a href="#">@Vinu</a></a>.
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
    <script src="<%=request.getContextPath()%>/assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="<%=request.getContextPath()%>/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="<%=request.getContextPath()%>/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <script src="<%=request.getContextPath()%>/dist/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="<%=request.getContextPath()%>/dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="<%=request.getContextPath()%>/dist/js/custom.min.js"></script>
    <!-- this page js -->
    <script src="<%=request.getContextPath()%>/assets/extra-libs/multicheck/datatable-checkbox-init.js"></script>
    <script src="<%=request.getContextPath()%>/assets/extra-libs/multicheck/jquery.multicheck.js"></script>
   <!-- data table -->
       <script src="<%=request.getContextPath()%>/assets/extra-libs/DataTables/datatables.min.js"></script>
       <script src="<%=request.getContextPath()%>/assets/extra-libs/DataTables/DataTables-1.10.16/js/jquery.dataTables.min.js"></script>
       <script src="<%=request.getContextPath()%>/assets/extra-libs/DataTables/Buttons-1.7.0/js/buttons.bootstrap4.min.js"></script>
       <script src="<%=request.getContextPath()%>/assets/extra-libs/DataTables/Buttons-1.7.0/js/dataTables.buttons.min.js"></script>
       <script src="<%=request.getContextPath()%>/assets/extra-libs/DataTables/Buttons-1.7.0/js/buttons.html5.js"></script>
       <script src="<%=request.getContextPath()%>/assets/extra-libs/DataTables/JSZip-2.5.0/jszip.js"></script>

       <script>
                       /****************************************
                        *       Basic Table                   *
                        ****************************************/
                       $('#zero_config').DataTable({
                       dom:'Bfrtip',
                       buttons:['excelHtml5','csvHtml5']
                       });
                   </script>
    <!-- Data table end-->


</body>

</html>