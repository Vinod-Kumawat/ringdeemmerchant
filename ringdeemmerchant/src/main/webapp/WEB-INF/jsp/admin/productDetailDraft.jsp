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
                        <h4 class="page-title">Product</h4>
                        <div class="ml-auto text-right">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">View Product</li>
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
                                <h5 class="card-title">Product Details</h5>
                                <c:if test="${not empty message}">
                                    <div class="alert alert-success">
                                          <strong>Success!</strong> ${message}.
                                    </div>
                                </c:if>


                                <div class="table-responsive">
                                    <table id="zero_config" class="table table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Product ID</th>
                                                <th>Merchant ID</th>
                                                <th>Product Name</th>
                                                <th>Product Point</th>
                                                <th>Category</th>
                                                <th>Price</th>
                                                <th>Discount Price</th>

                                                <th>Description</th>
                                                <th>Other Info</th>
                                                <th>Photo</th>
                                                <th>Status</th>
                                                <th>Start Date</th>
                                                <th>End Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <!-- product data here -->
                                        <c:if test="${not empty productList}">

                                            <c:forEach var="product" items="${productList}">
                                            <tr>
                                                <td><a href="<%=request.getContextPath()%>/updateProduct/${product.productId}" class="btn btn-success btn-cm">${product.productId}</a></td>
                                                <td>${product.mechantID}</td>
                                                <td>${product.productName}</td>
                                                <td>${product.productPoint}</td>
                                                <td>${product.category}</td>
                                                <td>${product.price}</td>
                                                <td>${product.discountprice}</td>

                                                <td>${product.description}</td>
                                                <td>${product.otherInfo}</td>
                                                <td><img src="<%=request.getContextPath()%>/productimage/${product.image}" alt="product" width="50" height="60"></td>
                                                <td>${product.status}</td>
                                                <td>${product.startdate}</td>
                                                <td>${product.enddate}</td>
                                                <td>
                                                    <c:if test="${product.status=='Live'}">
                                                    <a href="<%=request.getContextPath()%>/updateProduct/${product.productId}" class="btn btn-cyan btn-sm"> ${product.status} </a>
                                                    </c:if>
                                                    <c:if test="${product.status=='Draft'}">
                                                    <a href="<%=request.getContextPath()%>/updateProduct/${product.productId}" class="btn btn-warning btn-sm"> ${product.status} </a>
                                                    </c:if>
                                                </td>

                                            </tr>
                                            </c:forEach>
                                        </c:if>
                                        <!-- product data end -->
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Product ID</th>
                                                <th>Merchant ID</th>
                                                <th>Product Name</th>
                                                <th>Product Point</th>
                                                <th>Category</th>
                                                <th>Price</th>
                                                <th>Discount Price</th>

                                                <th>Description</th>
                                                <th>Other Info</th>
                                                <th>Photo</th>
                                                <th>Status</th>
                                                <th>Start Date</th>
                                                <th>End Date</th>
                                                <th>Action</th>
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