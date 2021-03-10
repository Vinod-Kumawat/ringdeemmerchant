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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/")
    public String Test(Model model,HttpServletRequest request)
    {
        String url="login";
        if(checkAdminSession(request)){
            url="admin/home";
        }
        else {
            model.addAttribute("loginForm", new Admin());
            System.out.println("Login");
        }
        return url;
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute("loginForm") Admin loginForm, HttpServletRequest request)
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
                String basePath=request.getServletContext().getRealPath("/merchantimage");
                filename=merchantForm.getContactName()+ merchantForm.getImage().getOriginalFilename().replace(" ","_");
                try {
                    FileUploadUtil.uploadFile(merchantForm.getImage().getBytes(),basePath,filename);
                }catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
            merchant=MerchantToPOJOConverter.convertPojoToMerchant(merchantForm,merchant,filename);
            merchant=merchantService.addMerchant(merchant);
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
        List<MerchantWalletAdd> merchantWalletAddList=merchantWalletAddService.getAllWallet();
        model.addAttribute("merchantWalletAddList",merchantWalletAddList);

        System.out.println("view P");
        return "admin/walletTopup";
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


    @RequestMapping(value="resolvedQuery/{id})")
    public String resolvedQuery(@PathVariable Integer id, Model model, HttpServletRequest request){
        if(null==id)
        {
            return viewInboxQuery(model,request);
        }

        if(!checkAdminSession(request)){
            return logout(request,model);
        }

            merchantQueryService.changeStatus(id);
         return viewInboxQuery(model,request);
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

}
