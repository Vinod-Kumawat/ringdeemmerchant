<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="com.merchant.merchant.bean.Merchant"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/libs/select2/dist/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/libs/jquery-minicolors/jquery.minicolors.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/libs/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/libs/quill/dist/quill.snow.css">
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
                        <h4 class="page-title">Merchant</h4>
                        <div class="ml-auto text-right">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Add Product</li>
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
                    <div class="col-md-12">
                        <div class="card">
                        <c:if test="${not empty message}">
                            <div class="alert alert-success">
                              <strong>Success!</strong> ${message}.
                            </div>
                        </c:if>
                            <form:form method="post" modelAttribute="query"  action="${contextPath}/merchant/saveQuery" class="form-horizontal"  enctype="multipart/form-data">
                                <div class="card-body">
                                    <h4 class="card-title">Add Wallet Balance</h4>
                                    <div class="row">
                                        <div class="form-group col-sm-12">
                                            <label for="company" class="col-sm-3 text-left control-label col-form-label">Merchant ID</label>
                                            <div class="col-sm-9">
                                            <spring:bind path="mechantID">
                                                <form:input type="text" path="mechantID" value="<%=merchant.getMerchantId() %>" class="form-control" id="mechantID" placeholder="mechant ID" readonly="true"></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-sm-12">
                                            <label for="productName" class="col-sm-3 text-left control-label col-form-label">Merchant Name</label>
                                            <div class="col-sm-9">
                                            <spring:bind path="merchantName">
                                                <form:input  type="text" path="merchantName" value="<%=merchant.getContactName() %>" class="form-control" id="merchantName" placeholder="merchant Name "  readonly="true"></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-sm-12">
                                            <label for="productName" class="col-sm-3 text-left control-label col-form-label">Message </label>
                                            <div class="col-sm-9">
                                            <spring:bind path="msgquery">
                                                <form:textarea  type="text" path="msgquery" class="form-control" id="msgquery" placeholder="Message here "></form:textarea>
                                            </spring:bind>
                                            </div>
                                        </div>
                                    </div>




                                </div>
                                <div class="border-top">
                                    <div class="card-body">
                                         <button class="btn btn-success float-left" type="submit">Post Query</button>
                                    </div>
                                </div>
                            </form:form>
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
    <!-- This Page JS -->
    <script src="<%=request.getContextPath()%>/assets/libs/inputmask/dist/min/jquery.inputmask.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/dist/js/pages/mask/mask.init.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/select2/dist/js/select2.full.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/select2/dist/js/select2.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/jquery-asColor/dist/jquery-asColor.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/jquery-asGradient/dist/jquery-asGradient.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/jquery-asColorPicker/dist/jquery-asColorPicker.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/jquery-minicolors/jquery.minicolors.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/quill/dist/quill.min.js"></script>
    <script>
        //***********************************//
        // For select 2
        //***********************************//
        $(".select2").select2();

        /*colorpicker*/
        $('.demo').each(function() {
        //
        // Dear reader, it's actually very easy to initialize MiniColors. For example:
        //
        //  $(selector).minicolors();
        //
        // The way I've done it below is just for the demo, so don't get confused
        // by it. Also, data- attributes aren't supported at this time...they're
        // only used for this demo.
        //
        $(this).minicolors({
                control: $(this).attr('data-control') || 'hue',
                position: $(this).attr('data-position') || 'bottom left',

                change: function(value, opacity) {
                    if (!value) return;
                    if (opacity) value += ', ' + opacity;
                    if (typeof console === 'object') {
                        console.log(value);
                    }
                },
                theme: 'bootstrap'
            });

        });
        /*datwpicker*/
        jQuery('.startdate').datepicker({
            format: 'yyyy-mm-dd'
        });
        jQuery('.enddate').datepicker({
        format: 'yyyy-mm-dd'
        });
        $(".startdate[value='']").datepicker("setDate", "-0d");
        $(".enddate[value='']").datepicker("setDate", "+3d");

       /*
       $(".enddate").on('change',function(){
             if($(".enddate").val()>=$(".startdate").val())
             {
                console.log(true);
             }
             else
             {
                      alert("End Date Should not be less to start date");
                      $(".enddate[value='']").datepicker("setDate", "-0d");
                console.log(false);
             }
        });
        */

        jQuery('#datepicker-autoclose').datepicker({
            autoclose: true,
            todayHighlight: true
        });
        var quill = new Quill('#editor', {
            theme: 'snow'
        });

    </script>
</body>

</html>