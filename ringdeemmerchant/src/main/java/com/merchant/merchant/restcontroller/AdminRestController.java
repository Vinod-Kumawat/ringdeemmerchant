package com.merchant.merchant.restcontroller;

import com.merchant.merchant.bean.*;
import com.merchant.merchant.dao.CountryRepository;
import com.merchant.merchant.dto.MerchantPOJO;
import com.merchant.merchant.dto.ProductPOJO;
import com.merchant.merchant.service.*;
import com.merchant.merchant.util.FileUploadUtil;
import com.merchant.merchant.util.MerchantToPOJOConverter;
import com.merchant.merchant.util.ProductToPOJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class AdminRestController {

    public static final String LIVE="Live";
    public static final String DRAFT="Draft";
    public static final String QUERY_STATUS_P="pending";
    public static final String QUERY_STATUS_R="resolved";


    @Autowired
    AdminService adminService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductService productService;

    @Autowired
    MerchantWalletAddService merchantWalletAddService;

    @Autowired
    MerchantQueryService merchantQueryService;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    UserPointHistoryService userPointHistoryService;


    HttpSession session=null;

    public boolean checkAdminSession(HttpServletRequest request)
    {
        boolean flag=false;
        session=request.getSession();
        if(null!=session && null!=session.getAttribute("admin")){
            Admin admin=(Admin) session.getAttribute("admin");
            session.setAttribute("admin",admin);
            System.out.println("in session");
            flag=true;
        }
        System.out.println("Session check properly");
        return flag;
    }

    public Model dashboardViewModelAdmin(Model model)
    {
        //Merchant merchant=(Merchant) session.getAttribute("merchant");
      //  merchant=merchantService.viewMerchantByID(merchant.getMerchantId());
     //   session.setAttribute("merchant", merchant);
        List<Merchant> merchantList=merchantService.viewMerchant();
        List<Product> productList=productService.viewProduct();
        List<MerchantWalletAdd> merchantWalletAddList=merchantWalletAddService.findByStatus("Pending");
        List<MerchantQuery> merchantQueryList=merchantQueryService.findByStatus(QUERY_STATUS_P);

        long totalMerchant=merchantList.size();
        int totalProduct=productList.size();
        long totalLiveProduct=productList.stream().filter(p->(null!=p.getStatus() && p.getStatus().equals("Live"))).count();
        long totalDraftProduct=productList.stream().filter(p->(null!=p.getStatus() && p.getStatus().equals("Draft"))).count();
        long totalPendingWallet=merchantWalletAddList.size();
        long totalPendingQuery=merchantQueryList.size();
      //  long totalSellPoint=userPointHistoryList.stream().map(u->Integer.parseInt(u.getProductPoint())).reduce(0,Integer::sum);
        model.addAttribute("totalMerchant",totalMerchant);
        model.addAttribute("totalProduct",totalProduct);
        model.addAttribute("totalDraftProduct",totalDraftProduct);
        model.addAttribute("totalLiveProduct",totalLiveProduct);
        model.addAttribute("totalPendingWallet",totalPendingWallet);
        model.addAttribute("totalPendingQuery",totalPendingQuery);

        // merchant by Country
        Map<String,Integer> merchantMap=new HashMap<>();
        Set<String> countrySet=merchantList.stream().filter(m-> m!=null && m.getCountry()!=null).map(m-> m.getCountry()).collect(Collectors.toSet());
        for (String s:countrySet){
            try {
                if (null != s && s.length() > 0) {
                    List<Merchant> merchantList1 = merchantList.stream().filter(m -> (null != m && m.getCountry()!=null && s!=null && m.getCountry().equalsIgnoreCase(s))).collect(Collectors.toList());
                    merchantMap.put(s, merchantList1.size());
                }
            }catch (Exception ex){
                // do nothing
            }
        }
        model.addAttribute("graphMerchantByCountry", merchantMap);

        // product by merchant
        Map<String,Integer> productMap=new HashMap<>();
        Set<String> merchantSet=merchantList.stream().map(m->(m.getMerchantId()+":"+m.getCompanyName())).collect(Collectors.toSet());
        for (String s:merchantSet) {
            Integer count=0;
            try {
                if (null != s && s.length() > 0) {
                    List<Product> productList1 = productList.stream().filter(p -> (null != p && p.getMechantID()!=null && s!=null && s.split(":")[0]!=null && p.getMechantID().toString().equals(s.split(":")[0]))).collect(Collectors.toList());
                    count=productList1.size();
                }
            }catch (Exception ex){
                // do nothing
            }
            finally {
                productMap.put(s,count);
            }

        }
        model.addAttribute("graphProductByMercahnt", productMap);

        // product by merchant
        Map<String,Long> productSellMap=new HashMap<>();
        Map<String,Long> businessSellMap=new HashMap<>();

        List<UserPointHistory> userPointHistoryList=userPointHistoryService.findAll();
         for (String s:merchantSet) {
             long productCount=0;
             long busessValue=0;
             if (null != s && s.length() > 0) {
                try {
                    productCount = userPointHistoryList.stream().filter(p -> (null != p && p.getMechantID().toString().equals(s.split(":")[0]))).map(p -> p.getProductId()).count();

                }catch (Exception ex){
                    // nothing
                }
                finally {
                    productSellMap.put(s, productCount);
                }

                try {
                    busessValue = userPointHistoryList.stream().filter(p -> (null != p && p.getMechantID().toString().equals(s.split(":")[0]))).map(p -> Long.valueOf(p.getDiscountprice())).reduce(Long.parseLong("0"), Long::sum);

                }catch (Exception ex){
                    // do nothing
                }finally {
                    businessSellMap.put(s, busessValue);
                }
            }
        }
        model.addAttribute("graphProductSellM", productSellMap);
        model.addAttribute("graphBusinessSellM", businessSellMap);






        return model;
    }

    @RequestMapping("/")
    public String Test(Model model,HttpServletRequest request)
    {
        String url="login";
        if(checkAdminSession(request)){
            model=dashboardViewModelAdmin(model);
            url="admin/home";
        }
        else {
            model.addAttribute("loginForm", new Admin());
            System.out.println("Login");
        }
        return url;
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute("loginForm") Admin loginForm, Model model, HttpServletRequest request)
    {
        session=request.getSession();
        if(checkAdminSession(request)){
            // do nothing
        }else {
            if (adminService.loginAdmin(loginForm)) {
                System.out.println("login Successfull");
                session.setAttribute("admin", loginForm);

            }
            else {
                return "login";
            }
        }
        model=dashboardViewModelAdmin(model);
        System.out.println("Login"+loginForm.getUserName()+" "+loginForm.getPassWord());
        return "admin/home";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,Model model)
    {
        session=request.getSession();
        if(null!=session && null!=session.getAttribute("admin"))
        {
         //   session.setAttribute("admin",null);
            session.removeAttribute("admin");
            session.invalidate();;

        }
        model.addAttribute("loginForm", new Admin());
        return "login";
    }

    @RequestMapping("admin/home")
    public String Test1()
    {
        System.out.println("Home");
        return "admin/home";
    }

    @RequestMapping("/addMerchant")
    public String addMerchant(Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
          return logout(request,model);
        }
        model.addAttribute("merchantForm", new MerchantPOJO());
        System.out.println("Add M");
        return "admin/addmerchant";
    }

    @RequestMapping("/saveMerchant")
    public String saveMerchant(@ModelAttribute("merchantForm") MerchantPOJO merchantForm,Model model,HttpServletRequest request)
    {
        boolean flag=false;
        if(!checkAdminSession(request)){
            return logout(request,model);
        }

        if(null==merchantForm.getMerchantId() && merchantService.isMerchantAlreadyRegisterByEmail(merchantForm.getMerchantMail())){
            model.addAttribute("ErrMessage","Merchant Already register with Email: "+merchantForm.getMerchantMail());
            model.addAttribute("merchantForm", merchantForm);
            return "admin/addmerchant";
        }

        Merchant merchant=new Merchant();
        String filename="";
        try {
            if(!merchantForm.getImage().isEmpty()) {
                String regex= "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(merchantForm.getImage().getOriginalFilename());
                if(!m.matches()){
                    flag=true;
                    model.addAttribute("ErrMessage","image should be jpeg, png, gif or bmp ");
                }else {

                    String basePath = request.getServletContext().getRealPath("/merchantimage");
                    filename = merchantForm.getContactName() + merchantForm.getImage().getOriginalFilename().replace(" ", "_");
                    try {
                        FileUploadUtil.uploadFile(merchantForm.getImage().getBytes(), basePath, filename);
                    } catch (Exception ex) {
                        flag=true;
                        model.addAttribute("ErrMessage","File uploading failed Please try again");
                        System.out.println(ex.getMessage());
                    }
                }
            }

            if(flag){
                // if file upload failed
                model.addAttribute("merchantForm",merchantForm);
            }else {
                merchant = MerchantToPOJOConverter.convertPojoToMerchant(merchantForm, merchant, filename);
                merchant = merchantService.addMerchant(merchant);
                System.out.println(merchantForm.getCompanyName());

                // model.addAttribute("merchantForm", new Merchant());
                System.out.println("Add M: " + merchant);

                if (null == merchantForm.getMerchantId()) {
                    model.addAttribute("message", "Merchant added with merchantID " + merchant.getMerchantId());
                } else {
                    model.addAttribute("message", "Merchant Updated with merchantID " + merchant.getMerchantId());
                }

                model.addAttribute("merchantForm", new Merchant());
            }
        }
        catch (Exception ex)
        {
            model.addAttribute("merchantForm", null!=merchant?merchant: new Merchant());
            model.addAttribute("ErrMessage","Merchant added with merchantID "+ex.getMessage());

        }
      return "admin/addmerchant";
    }

    @RequestMapping(value="/editMerchant/{id}")
    public String editMerchant(@PathVariable Integer id , Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        MerchantPOJO merchantPOJO=new MerchantPOJO();
        Merchant merchant=merchantService.viewMerchantByID(id);
        merchantPOJO=MerchantToPOJOConverter.convertMerchantToPOJO(merchant,merchantPOJO);
        System.out.println(merchant.toString());
        model.addAttribute("merchantForm",merchantPOJO);
        System.out.println("Edit M");

        return "admin/editMerchant";
    }

    @RequestMapping("/deleteMerchant")
    public String deleteMerchant(HttpServletRequest request,Model model)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        System.out.println("Delete M");
        return "admin/merchantDetail";
    }

    @RequestMapping("/viewMerchant")
    public String viewMerchant(Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }

        List<Merchant> merchantList=merchantService.viewMerchant();
        model.addAttribute("merchantList",merchantList);
        System.out.println("view M");
        return "admin/merchantDetail";
    }

    @RequestMapping("/viewProduct")
    public String viewProduct(Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        List<Product> productList=productService.viewProduct();
        model.addAttribute("productList",productList);

        System.out.println("view P");
        return "admin/productDetail";
    }

    @RequestMapping("/viewLiveProduct")
    public String viewLiveProduct(Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        List<Product> productList=productService.viewProductByStatus(LIVE);
        model.addAttribute("productList",productList);

        System.out.println("view P");
        return "admin/productDetailLive";
    }

    @RequestMapping("/viewDraftProduct")
    public String viewDraftProduct(Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        List<Product> productList=productService.viewProductByStatus(DRAFT);
        model.addAttribute("productList",productList);

        System.out.println("view P");
        return "admin/productDetailDraft";
    }

    /*======== Wallet ======================*/
    @RequestMapping("/viewAllWalletTopup")
    public String viewAllWalletTopup(Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        List<MerchantWalletAdd> merchantWalletAddList=merchantWalletAddService.findByStatus("Pending");
        model.addAttribute("merchantWalletAddList",merchantWalletAddList);

        System.out.println("view P");
        return "admin/walletTopup";
    }

    @RequestMapping("/viewApprovedWalletTopup")
    public String viewApprovedWalletTopup(Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        List<MerchantWalletAdd> merchantWalletAddList=merchantWalletAddService.findByStatus("Approved");
        model.addAttribute("merchantWalletAddList",merchantWalletAddList);

        System.out.println("view P");
        return "admin/approvedWalletTopup";
    }


    /*======== inbox Query ======================*/
    @RequestMapping("/viewInboxQuery")
    public String viewInboxQuery(Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        List<MerchantQuery> merchantQueryList=merchantQueryService.findByStatus(QUERY_STATUS_P);
        model.addAttribute("queryList",merchantQueryList);

        System.out.println("view P");
        return "admin/inboxQuery";
    }

    /*======== Resolved Query ======================*/
    @RequestMapping("/viewResolvedQuery")
    public String viewResolvedQuery(Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        List<MerchantQuery> merchantQueryList=merchantQueryService.findByStatus(QUERY_STATUS_R);
        model.addAttribute("queryList",merchantQueryList);

        System.out.println("view P");
        return "admin/resolveQuery";
    }

    @RequestMapping("/viewTransaction")
    public String viewTransaction(HttpServletRequest request,Model model)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        System.out.println("view T");
        return "admin/transactionDetail";
    }

    @RequestMapping("/viewTotalSell")
    public String viewTotalSell(HttpServletRequest request, Model model)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        System.out.println("view T S");
        return "admin/totalSellDetail";
    }

    @RequestMapping("/viewPoint")
    public String viewPoint(HttpServletRequest request, Model model)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        System.out.println("view c point");
        return "admin/comsumePointByMerchant";
    }

    @RequestMapping("viewProductByMerchant/{id}")
    public String viewProductByMerchant(@PathVariable Integer id,Model model,HttpServletRequest request)
    {
        if(!checkAdminSession(request)){
            return logout(request,model);
        }
        List<Product> productList=productService.viewProductByMerchantID(id);
        model.addAttribute("productList",productList);
        System.out.println("view P");
        return "admin/productDetail";
    }

    @ModelAttribute("merchantList2")
    public List<Merchant> getMerchantLowPoint()
    {
        return merchantService.findMerchantByLowPoint();
    }


    @ModelAttribute("countryList")
    public Map<String, String> getCountryList()
    {
        Map<String, String> countryList=new HashMap<>();
        List<Country> countryList1=countryRepository.findAll();
        for(Country country:countryList1)
        {
            countryList.put(country.getName(),country.getName());
        }
        return countryList;
    }

    @RequestMapping(value="updateProduct/{id}")
    public String updateProduct(@PathVariable Integer id , Model model, HttpServletRequest request) {
        if(!checkAdminSession(request))
        {
            return logout(request,model);
        }

        Product product=productService.viewProductByID(id);
        ProductPOJO productPOJO=new ProductPOJO();
        productPOJO= ProductToPOJOConverter.convertProductToPOJO(product,productPOJO);
        System.out.println(product.toString());
        model.addAttribute("productForm",productPOJO);
        System.out.println("Edit P");
        return "admin/updateProduct";
    }

    @RequestMapping(value = "/saveProduct")
    public String saveProduct(@ModelAttribute("productForm") ProductPOJO productPOJO, Model model, HttpServletRequest request)
    {
        if(!checkAdminSession(request))
        {
            return logout(request,model);
        }
        // update product point
        int point=productPOJO.getProductPoint();
        String status=productPOJO.getStatus();
        Integer pid=productPOJO.getProductId();
        productService.updateProductByAdmin(point,status,pid);


        List<Product> productList=productService.viewProduct();
        model.addAttribute("message","Product updated Successfully with PID: "+pid);
        model.addAttribute("productList",productList);
        System.out.println("point updated  P");
        return "admin/productDetail";
    }

    @RequestMapping(value = "/approveWallet/{id}")
    public String approveWallet(@PathVariable Integer id, Model model, HttpServletRequest request){
        if(!checkAdminSession(request))
        {
            return logout(request,model);
        }

        if(null!=id && id>0)
        {
            // approve wallet amount
            merchantWalletAddService.approveWalletByAdmin(id);
        }
        // fetch approved wallet detail
        MerchantWalletAdd merchantWalletAdd=merchantWalletAddService.getWalletByID(id);
        // get merchant id from respective wallet
        Integer merchantId = merchantWalletAdd.getMerchantId();
        // get merchant current point to merchant original  point
        long point = merchantService.getMerchantPoint(merchantId);
        // upgrade merchant Point
        point = point + merchantWalletAdd.getAmount();
        // update merchant point
        merchantService.updateMerchantPoint(point, merchantId);
        return viewAllWalletTopup(model, request);
    }

    @RequestMapping(value = "/resolveQueryByAdmin/{id}")
    public String resolveQueryByAdmin(@PathVariable Integer id, Model model, HttpServletRequest request){
        if(!checkAdminSession(request))
        {
            return logout(request,model);
        }

        if(null!=id && id>0)
        {
            // approve query
            merchantQueryService.changeStatus(id);
        }
        return viewInboxQuery(model, request);
    }

    @RequestMapping(value = "/transaction")
    public String ViewTransaction(Model model, HttpServletRequest request)
    {
        if(!checkAdminSession(request))
        {
            return logout(request,model);
        }
        List<UserPointHistory> userPointHistoryList=userPointHistoryService.findAll();

        model.addAttribute("userPointHistoryList", userPointHistoryList);
        return "admin/transaction";
    }

    @RequestMapping(value = "/viewTransactionByMerchant/{id}")
    public String viewTransactionByMerchant(@PathVariable Integer id, Model model, HttpServletRequest request)
    {
        if(!checkAdminSession(request))
        {
            return logout(request,model);
        }
        List<UserPointHistory> userPointHistoryList=userPointHistoryService.findByMerchantID(id);
        model.addAttribute("merchantt",merchantService.viewMerchantByID(id));
        model.addAttribute("userPointHistoryList", userPointHistoryList);
        return "admin/transaction";
    }

    @RequestMapping(value = "/viewTransactionBycountry/{country}")
    public String viewTransactionBycountry(@PathVariable String country, Model model, HttpServletRequest request)
    {
        if(!checkAdminSession(request))
        {
            return logout(request,model);
        }
        List<UserPointHistory> userPointHistoryList=userPointHistoryService.findAll();
        if(country.equalsIgnoreCase("all")){

        }else {
            userPointHistoryList = userPointHistoryList.stream().filter(u -> (null != u.getCountry() && u.getCountry().equalsIgnoreCase(country))).collect(Collectors.toList());
        }
        model.addAttribute("userPointHistoryList", userPointHistoryList);
        return "admin/transaction";
    }

}
