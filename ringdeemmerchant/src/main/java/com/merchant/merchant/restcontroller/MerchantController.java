package com.merchant.merchant.restcontroller;

import com.merchant.merchant.bean.*;
import com.merchant.merchant.dto.MerchantPOJO;
import com.merchant.merchant.dto.MerchantWalletAddPOJO;
import com.merchant.merchant.dto.ProductPOJO;
import com.merchant.merchant.service.*;
import com.merchant.merchant.util.FileUploadUtil;
import com.merchant.merchant.util.MerchantToPOJOConverter;
import com.merchant.merchant.util.ProductToPOJOConverter;
import com.merchant.merchant.util.WalletToPOJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MerchantController {

    public static final String LIVE="Live";
    public static final String DRAFT="Draft";


    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductService productService;

    @Autowired
    UserPointHistoryService userPointHistoryService;

    @Autowired
    MerchantWalletAddService merchantWalletAddService;

    @Autowired
    MerchantQueryService merchantQueryService;

    HttpSession session;

    /* ============== session check ======================== */
    public boolean checkMerchantSession(HttpServletRequest request)
    {
        boolean flag=false;
        session=request.getSession();
        if(null!=session && null!=session.getAttribute("merchant")){
            Merchant merchant=(Merchant) session.getAttribute("merchant");
            session.setAttribute("merchant",merchant);
            System.out.println("in session");
            flag=true;
        }
        System.out.println("Session check properly merchant");
        return flag;
    }

    /* ============== Dash board ======================== */
    public Model dashboardViewModel(Model model)
    {
        Merchant merchant=(Merchant) session.getAttribute("merchant");
        merchant=merchantService.viewMerchantByID(merchant.getMerchantId());
        session.setAttribute("merchant", merchant);
        List<Product> productList=productService.viewProductByMerchantID(merchant.getMerchantId());
        List<UserPointHistory> userPointHistoryList=userPointHistoryService.findByMerchantID(merchant.getMerchantId());
        int totalProduct=productList.size();
        long totalLiveProduct=productList.stream().filter(p->(null!=p.getStatus() && p.getStatus().equals("Live"))).count();
        long totalDraftProduct=productList.stream().filter(p->(null!=p.getStatus() && p.getStatus().equals("Draft"))).count();
        long totalSellPoint=userPointHistoryList.stream().map(u->Integer.parseInt(u.getProductPoint())).reduce(0,Integer::sum);
        model.addAttribute("totalProduct",totalProduct);
        model.addAttribute("totalDraftProduct",totalDraftProduct);
        model.addAttribute("totalLiveProduct",totalLiveProduct);
        model.addAttribute("totalSellPoint",totalSellPoint);
        return model;
    }

    /* ============== Logout ======================== */
    @RequestMapping("/merchant/logout")
    public String logout(HttpServletRequest request,Model model)
    {
        session=request.getSession();
        if(null!=session && null!=session.getAttribute("merchant"))
        {
            //   session.setAttribute("admin",null);
            session.removeAttribute("merchant");
            session.invalidate();
        }
        model.addAttribute("merchantLoginForm", new Merchant());
        return "merchant/login";
    }

    /* ============== Home Login page ======================== */
    @RequestMapping("/merchant/")
    public String Test(Model model,HttpServletRequest request)
    {
        if(checkMerchantSession(request))
        {
            model=dashboardViewModel(model);
            return "merchant/home";
        }
        model.addAttribute("merchantLoginForm", new Merchant());
        System.out.println("Login");
        return "merchant/login";
    }

    /* ============== Login Process ======================== */
    @PostMapping("merchant/login")
    public String Login(@ModelAttribute("merchantLoginForm") Merchant merchantLoginForm,Model model, HttpServletRequest request) {
        session = request.getSession();
        if (checkMerchantSession(request)) {
            // do nothing
        } else {
            Merchant merchant = merchantService.getMerchantByEmail(merchantLoginForm.getMerchantMail());
            if (merchant.getPassword().equals(merchantLoginForm.getPassword())) {
                //if(null==request.getSession()){
                session = request.getSession();
                session.setAttribute("merchant", merchant);

                //}
                System.out.println("login Successfull");
                model=dashboardViewModel(model);
            }
            // System.out.println("Login"+loginForm.getUserName()+" "+loginForm.getPassWord());

        }
        return "merchant/home";
    }

    /* ============== Edit Merchant page  ======================== */
    @RequestMapping(value="/merchant/editMerchant/{id}")
    public String editMerchant(@PathVariable Integer id , Model model, HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }

        MerchantPOJO merchantPOJO=new MerchantPOJO();
        Merchant merchant=merchantService.viewMerchantByID(id);
        merchantPOJO= MerchantToPOJOConverter.convertMerchantToPOJO(merchant,merchantPOJO);

        System.out.println(merchant.toString());
        model.addAttribute("merchantForm",merchantPOJO);
        System.out.println("Edit M");

        return "merchant/editMerchant";
    }

    /* ============== view list of merchant ======================== */
    @RequestMapping("merchant/viewMerchant")
    public String viewMerchant(Model model,HttpServletRequest request){
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }

        List<Merchant> merchantList=merchantService.viewMerchant();
        model.addAttribute("merchantList",merchantList);
        System.out.println("view M");
        return "merchant/merchantDetail";
    }

    /* ============== view list of Product ======================== */
    @RequestMapping("merchant/viewProduct")
    public String viewProduct(Model model,HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }
        Integer mid=getMerchantIDBySession(request);
        if(mid>0) {
            List<Product> productList = productService.viewProductByMerchantID(mid);
            model.addAttribute("productList", productList);
        }
        System.out.println("view P");
        return "merchant/productDetail";
    }

    /* ============== view list of Live Product ======================== */
    @RequestMapping("merchant/viewProductLive")
    public String viewProductLive(Model model,HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }
        Integer mid=getMerchantIDBySession(request);
        if(mid>0) {
            List<Product> productList = productService.viewProductByMerchantID(mid);
                List<Product> productList1 = productList.stream().filter(p -> (null!=p.getStatus() && p.getStatus().equals(LIVE))).collect(Collectors.toList());
                model.addAttribute("productList", productList1);

        }
        System.out.println("view P");
        return "merchant/productDetailLive";
    }

    /* ============== view list of Draft Product ======================== */
    @RequestMapping("merchant/viewProductDraft")
    public String viewProductDraft(Model model,HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }
        Integer mid=getMerchantIDBySession(request);
        if(mid>0) {
            List<Product> productList = productService.viewProductByMerchantID(mid);
            List<Product> productList1 = productList.stream().filter(p -> (null!=p.getStatus() && p.getStatus().equals(DRAFT))).collect(Collectors.toList());
            model.addAttribute("productList", productList1);

        }
        System.out.println("view P");
        return "merchant/productDetailDraft";
    }

    /* ============== Add product Page ======================== */
    @RequestMapping("merchant/addProduct")
    public String addProduct(Model model,HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }
        ProductPOJO productPOJO=new ProductPOJO();
        model.addAttribute("productForm", productPOJO);
        System.out.println("Add P");
        return "merchant/addProduct";
    }

    /* ============== Save or Update of Product in DB ======================== */
    @RequestMapping("merchant/saveProduct")
    public String saveProduct(@ModelAttribute("productForm") ProductPOJO productForm, Model model,HttpServletRequest request)
    {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }

        Product product=new Product();
        String filename="";
        System.out.println(productForm.getImage().getOriginalFilename().replace(" ","_"));
        if(!productForm.getImage().isEmpty()) {
            String basePath=request.getServletContext().getRealPath("/productimage");
            filename=productForm.getProductName()+productForm.getMechantID()+ productForm.getImage().getOriginalFilename().replace(" ","_");
            try {
                    FileUploadUtil.uploadFile(productForm.getImage().getBytes(),basePath,filename);
                }catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
        }
        else{
            if(null!=productForm.getProductId())
            {
                filename=productService.viewProductByID(productForm.getProductId()).getImage();
            }
        }


        try {
            product=ProductToPOJOConverter.convertPojoToProduct(productForm,product,filename);
            product=productService.addProduct(product);
            if(null==productForm.getProductId())
            {
                model.addAttribute("message","Product added with ProductID "+product.getProductId());
            }
            else {

                model.addAttribute("message","Product Updated with productID "+product.getProductId());
            }
            model.addAttribute("productForm", new ProductPOJO());
        }
        catch (Exception ex)
        {
            model.addAttribute("productForm", null!=productForm?productForm: new ProductPOJO());
            model.addAttribute("message","Failed to add/update Product"+product.getProductId());

        }
        return "merchant/addProduct";
    }

    /* ============== Edit Product page ======================== */
    @RequestMapping(value="merchant/editProduct/{id}")
    public String editProduct(@PathVariable Integer id , Model model, HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }

        Product product=productService.viewProductByID(id);
        ProductPOJO productPOJO=new ProductPOJO();
        productPOJO=ProductToPOJOConverter.convertProductToPOJO(product,productPOJO);
        System.out.println(product.toString());
        model.addAttribute("productForm",productPOJO);
        System.out.println("Edit P");
        return "merchant/editProduct";
    }

    /* ============== Delete Product ======================== */
    @RequestMapping(value = "merchant/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id,Model model,HttpServletRequest request)
    {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }
        System.out.println("delete product"+ id);
        productService.deleteProductByID(id);
        Integer mid=getMerchantIDBySession(request);
        if(mid>0) {
            List<Product> productList = productService.viewProductByMerchantID(mid);
            model.addAttribute("productList", productList);
        }
        System.out.println("view P");
        return "merchant/productDetail";
    }

    /* ============== Wallet topup page ======================== */
    @RequestMapping("merchant/addwallet")
    public String addWallet(Model model,HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }
        MerchantWalletAddPOJO merchantWalletAddPOJO=new MerchantWalletAddPOJO();
        model.addAttribute("wallet", merchantWalletAddPOJO);
        System.out.println("Add w");
        return "merchant/topupwallet";
    }

    /* ============== Save/ Update Wallet balance ======================== */
    @RequestMapping("merchant/savewallet")
    public String saveWallet(@ModelAttribute("wallet") MerchantWalletAddPOJO merchantWalletAddPOJO,Model model,HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }
        try {
            // upload image
            String filename = "";
            if (!merchantWalletAddPOJO.getImage().isEmpty()) {
                String basePath = request.getServletContext().getRealPath("/walletimage");
                filename = "AMT" + merchantWalletAddPOJO.getAmount() + "MID" + merchantWalletAddPOJO.getMerchantId() + merchantWalletAddPOJO.getImage().getOriginalFilename().replace(" ", "_");
                try {
                    FileUploadUtil.uploadFile(merchantWalletAddPOJO.getImage().getBytes(), basePath, filename);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

            // merchant wallet record added
            MerchantWalletAdd merchantWalletAdd = new MerchantWalletAdd();
            merchantWalletAdd = WalletToPOJOConverter.convertPojoToMerchantWallet(merchantWalletAddPOJO, merchantWalletAdd, filename);
            merchantWalletAdd = merchantWalletAddService.saveWallet(merchantWalletAdd);
            if (null != merchantWalletAdd.getWalletID() && merchantWalletAdd.getWalletID() > 0) {
                model.addAttribute("message", "Your wallet balance " + merchantWalletAdd.getAmount() + " posted with wallet ref no: " + merchantWalletAdd.getWalletID());
            }
           /* Integer merchantId = merchantWalletAdd.getMerchantId();
            long point = merchantService.getMerchantPoint(merchantId);
            point = point + merchantWalletAdd.getAmount();
            merchantService.updateMerchantPoint(point, merchantId);*/
            MerchantWalletAddPOJO merchantWalletAddPOJO1 = new MerchantWalletAddPOJO();
            model.addAttribute("wallet", merchantWalletAddPOJO);
            System.out.println("Add w");
        }
        catch (Exception exception)
        {
            model.addAttribute("message","failed to topup balance ");
            model.addAttribute("wallet", merchantWalletAddPOJO);
        }

        return "merchant/topupwallet";
    }

    @RequestMapping("merchant/addQuery")
    public String addQuery(Model model,HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }
        MerchantQuery merchantQuery=new MerchantQuery();
        model.addAttribute("query", merchantQuery);
        System.out.println("Add w");
        return "merchant/query";
    }

    @RequestMapping("merchant/saveQuery")
    public String saveQuery(@ModelAttribute("query") MerchantQuery mquery, Model model,HttpServletRequest request) {
        if(!checkMerchantSession(request))
        {
            return logout(request,model);
        }

        MerchantQuery merchantQuery=new MerchantQuery();
        mquery.setDate(new Date(System.currentTimeMillis()));
        merchantQuery=merchantQueryService.saveQuery(mquery);
        if(null!=merchantQuery && merchantQuery.getMerchantQueryId()>0){
            model.addAttribute("message","Your query has been posted with Ref No. "+merchantQuery.getMerchantQueryId());
        }
        model.addAttribute("query", new MerchantQuery());
        System.out.println("Add w");
        return "merchant/query";
    }

    /*

    @RequestMapping("admin/home")
    public String Test1()
    {
        System.out.println("Home");
        return "admin/home";
    }

    @RequestMapping("/addMerchant")
    public String addMerchant(Model model)
    {
        model.addAttribute("merchantForm", new Merchant());
        System.out.println("Add M");
        return "admin/addmerchant";
    }

    @RequestMapping("/saveMerchant")
    public String saveMerchant(@ModelAttribute("merchantForm") Merchant merchantForm,Model model)
    {
        Merchant merchant=null;
        try {
             merchant=merchantService.addMerchant(merchantForm);
            System.out.println(merchantForm.getCompanyName());

            // model.addAttribute("merchantForm", new Merchant());
            System.out.println("Add M: "+merchant);

            if(null==merchantForm.getMerchantId())
            {
                model.addAttribute("message","Merchant added with merchantID "+merchant.getMerchantId());
            }
            else {
                model.addAttribute("message","Merchant Updated with merchantID "+merchant.getMerchantId());
            }

            model.addAttribute("merchantForm", new Merchant());


        }
        catch (Exception ex)
        {
            model.addAttribute("merchantForm", null!=merchant?merchant: new Merchant());
            model.addAttribute("message","Merchant added with merchantID "+merchant.getMerchantId());

        }
                return "admin/addmerchant";
    }

    @RequestMapping(value="/editMerchant/{id}")
    public String editMerchant(@PathVariable Integer id , Model model) {
        Merchant merchant=merchantService.viewMerchantByID(id);
        System.out.println(merchant.toString());
        model.addAttribute("merchantForm",merchant);
        System.out.println("Edit M");

        return "admin/editMerchant";
    }

    @RequestMapping("/deleteMerchant")
    public String deleteMerchant() {
        System.out.println("Delete M");
        return "admin/merchantDetail";
    }

    @RequestMapping("/viewMerchant")
    public String viewMerchant(Model model){

        List<Merchant> merchantList=merchantService.viewMerchant();
        model.addAttribute("merchantList",merchantList);
        System.out.println("view M");
        return "admin/merchantDetail";
    }

    @RequestMapping("/viewProduct")
    public String viewProduct() {
        System.out.println("view P");
        return "admin/productDetail";
    }

    @RequestMapping("/viewTransaction")
    public String viewTransaction() {
        System.out.println("view T");
        return "admin/transactionDetail";
    }

    @RequestMapping("/viewTotalSell")
    public String viewTotalSell() {
        System.out.println("view T S");
        return "admin/totalSellDetail";
    }

    @RequestMapping("/viewPoint")
    public String viewPoint() {
        System.out.println("view c point");
        return "admin/comsumePointByMerchant";
    }*/

    public  Integer getMerchantIDBySession(HttpServletRequest request){
        Integer merchantID;
        session=request.getSession();
        if(null!=session.getAttribute("merchant")){
            Merchant merchant=(Merchant) session.getAttribute("merchant");
            merchantID=merchant.getMerchantId();
        }
        else
        {
            merchantID=0;
        }
        return merchantID;
    }


}
