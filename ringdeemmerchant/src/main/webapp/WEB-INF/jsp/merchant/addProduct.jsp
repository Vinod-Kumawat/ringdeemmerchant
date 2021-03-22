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

<style>
.error{
color:red
}
</style>

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
                            <form:form method="post" modelAttribute="productForm" id="productForm" action="${contextPath}/merchant/saveProduct" class="form-horizontal"  enctype="multipart/form-data">
                                <div class="card-body">
                                    <h4 class="card-title">Add New Product</h4>
                                    <div class="row">
                                        <div class="form-group col-sm-6">
                                            <label for="company" class="col-sm-3 text-left control-label col-form-label">Merchant ID</label>
                                            <div class="col-sm-9">
                                            <spring:bind path="mechantID">
                                                <form:input type="text" path="mechantID" value="<%=merchant.getMerchantId() %>" class="form-control" id="mechantID" placeholder="mechant ID" readonly="true"></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label for="productName" class="col-sm-3 text-left control-label col-form-label">Product Name</label>
                                            <div class="col-sm-9">
                                            <spring:bind path="productName">
                                                <form:input  type="text" path="productName" class="form-control" id="productName" placeholder="Product Name"></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-sm-6">
                                            <label for="productPoint" class="col-sm-3 text-left control-label col-form-label">Product Point</label>
                                            <div class="col-sm-9">
                                            <spring:bind path="productPoint">
                                                <form:input  type="text" path="productPoint" class="form-control" id="productPoint" placeholder="Product Point" readonly="true"></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label for="category" class="col-sm-3 text-left control-label col-form-label">Category</label>
                                            <div class="col-sm-9">
                                            <spring:bind path="category">
                                               <form:select path="category" class="form-control" id="category">
                                                    <form:option value=""  label="Select" />
                                                    <form:options items="${categoryList}"/>
                                               </form:select>
                                            </spring:bind>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-sm-6">
                                            <label for="price" class="col-sm-3 text-left control-label col-form-label">Price </label>
                                            <div class="col-sm-9">
                                            <spring:bind path="price">
                                                <form:input  type="text" path="price" class="form-control" id="price" placeholder="Product Price" ></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>

                                        <div class="form-group col-sm-6">
                                            <label for="discountprice" class="col-sm-3 text-left control-label col-form-label">Discount Price</label>
                                            <div class="col-sm-9">
                                            <spring:bind path="discountprice">
                                                <form:input  type="text" path="discountprice" class="form-control" id="discountprice" placeholder="Product discount price" ></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="form-group col-sm-6">
                                            <label for="description" class="col-sm-3 text-left control-label col-form-label">Description </label>
                                            <div class="col-sm-9">
                                            <spring:bind path="description">
                                                <form:input type="text" path="description" class="form-control" id="description" placeholder="description"></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label for="otherInfo" class="col-sm-3 text-left control-label col-form-label">Other Info</label>
                                            <div class="col-sm-9">
                                            <spring:bind path="otherInfo">
                                               <form:input type="text" path="otherInfo" class="form-control" id="otherInfo" placeholder="Other Info"></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">

                                        <div class="form-group col-sm-6">
                                            <label for="image" class="col-sm-3 text-left control-label col-form-label">Image </label>
                                            <div class="col-sm-9">
                                            <spring:bind path="image">
                                               <form:input type="file" path="image" class="form-control" id="image" placeholder="Mobile Number here"></form:input>
                                            </spring:bind>
                                            </div>
                                        </div>

                                        <div class="form-group col-sm-6">
                                            <label for="otherInfo" class="col-sm-3 text-left control-label col-form-label">Status</label>
                                            <div class="col-sm-9">
                                            <spring:bind path="status">
                                                <form:input type="text" path="status" class="form-control" id="otherInfo" value="Draft" placeholder="Other Info" readonly="true"></form:input>

                                            </spring:bind>
                                            </div>
                                        </div>
                                     </div>

                                    <div class="row">
                                       <div class="form-group col-sm-6">
                                        <label>Start Date</label>
                                        <div class="input-group">
                                            <spring:bind path="startdate">
                                                <form:input type="text" id="startdate" path="startdate" class="form-control startdate" placeholder="YYY-mm-dd"  required="true"></form:input>
                                            </spring:bind>
                                            <div class="input-group-append">
                                                <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                                            </div>
                                        </div>
                                       </div>
                                        <div class="form-group col-sm-6">
                                        <label>End Date</label>
                                        <div class="input-group">
                                            <spring:bind path="enddate">
                                                <form:input type="text" id="enddate" path="enddate" class="form-control enddate" placeholder="YYY-mm-dd" required="true"></form:input>
                                            </spring:bind>
                                            <div class="input-group-append">
                                                <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                                            </div>

                                        </div>
                                    </div>


                                </div>
                                <div class="border-top">
                                    <div class="card-body">
                                         <button class="btn btn-success float-right" type="submit">Save Product</button>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
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
        $(".enddate[value='']").datepicker("setDate", "+5d");

      /*  $(".startdate").on('change',function(){
        var today = new Date();

        var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
                     if($(".startdate").val()>=date)
                     {
                        console.log(true);
                     }
                     else
                     {
                             // alert("Start Date Should be today date or next date");
                              $(".enddate[value='']").datepicker("setDate", "-0d");
                        console.log(false);
                     }
                });


       $(".enddate").on('change',function(){
             if($(".enddate").val()>=$(".startdate").val())
             {
                console.log(true);
             }
             else
             {
                      //alert("End Date Should not be less to start date");
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

        jQuery.validator.addMethod("greaterThan",
        function(value, element, params) {

            if (!/Invalid|NaN/.test(new Date(value))) {
                return new Date(value) > new Date($(params).val());
            }

            return isNaN(value) && isNaN($(params).val())
                || (Number(value) > Number($(params).val()));
        },'Must be greater than {0}.');

         jQuery.validator.addMethod("currentDate",
                function(value, element) {

                    var dt=new Date(value);
                    var dt2=new Date();
                        return (dt.getFullYear()>=dt2.getFullYear() && dt.getMonth()>=dt2.getMonth() && dt.getDate()>=dt2.getDate());
                },'Must be greater than or equal to current Date.');


        $(document).ready(function(){
                       $("#productForm").validate({
                         // Specify validation rules
                         rules: {
                           productName: {
                             required: true,
                             minlength: 3
                           },
                           category: {
                             required: true
                           },
                           price: {
                             required: true
                           },
                           discountprice: {
                             required: true
                           },
                           startdate:{
                           required:true,
                           date:true,
                           currentDate: true
                           },
                           enddate:{
                           required:true,
                           date:true,
                           greaterThan: "#startdate"
                           }
                         },

                       });
                     });

    </script>
</body>

</html>