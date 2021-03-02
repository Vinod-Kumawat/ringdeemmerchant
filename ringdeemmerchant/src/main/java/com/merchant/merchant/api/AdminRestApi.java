package com.merchant.merchant.api;

import com.merchant.merchant.bean.Admin;
import com.merchant.merchant.bean.Country;
import com.merchant.merchant.bean.Merchant;
import com.merchant.merchant.bean.Product;
import com.merchant.merchant.dao.CountryRepository;
import com.merchant.merchant.dto.MerchantPOJO;
import com.merchant.merchant.dto.ProductPOJO;
import com.merchant.merchant.service.AdminService;
import com.merchant.merchant.service.MerchantService;
import com.merchant.merchant.service.ProductService;
import com.merchant.merchant.util.FileUploadUtil;
import com.merchant.merchant.util.MerchantToPOJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class AdminRestApi {
    @Autowired
    AdminService adminService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductService productService;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ServletContext servletContext;


    HttpSession session=null;

  /*  public boolean checkAdminSession(HttpServletRequest request)
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
    }*/

   /* @RequestMapping("/")
    public String Test(Model model, HttpServletRequest request)
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
    }*/
/*
    @PostMapping("/login")
    public String Login(@RequestBody Admin loginForm)
    {
       *//* session=request.getSession();
        if(checkAdminSession(request)){
            // do nothing
        }else {*//*
            if (adminService.loginAdmin(loginForm)) {
                System.out.println("login Successfull");
                session.setAttribute("admin", loginForm);

            }
            else {
                return "login";
            }
       *//* }*//*
        System.out.println("Login"+loginForm.getUserName()+" "+loginForm.getPassWord());
        return "admin/home";
    }*/
/*
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
    }*/

    /*@RequestMapping("admin/home")
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
    }*/

    @PostMapping(path="/addMerchant")
    public String addAndSaveMerchant(@RequestBody Merchant merchant)
    {
        String msg="";
        try {
            if(null==merchant.getMerchantId())
            {
                msg= "Merchant added with merchantID ";
            }
            else {
                msg= "Merchant Updated with merchantID ";
            }
            merchant=merchantService.addMerchant(merchant);
            msg+=merchant.getMerchantId();
        }
        catch (Exception ex)
        {
           msg="something wrong please try again";

        }
        return msg;
    }

    @PostMapping(path="/addMerchantWithFile")
    public String addAndSaveMerchantWithFile(@RequestBody MerchantPOJO merchantPOJO)
    {
        /*String msg=merchantPOJO.getImage().getOriginalFilename();
        System.out.println(merchantPOJO.getImage().getOriginalFilename());

        try {
            merchant=merchantService.addMerchant(merchant);

            if(null==merchant.getMerchantId())
            {
                msg= "Merchant added with merchantID "+merchant.getMerchantId();
            }
            else {
                msg= "Merchant Updated with merchantID "+merchant.getMerchantId();
            }

        }
        catch (Exception ex)
        {
           msg="something wrong please try again";

        }*/
        return "";
    }

    @GetMapping(value="/viewMerchantByID/{id}")
    public Merchant viewMerchantByID(@PathVariable Integer id )
    {
        //MerchantPOJO merchantPOJO=new MerchantPOJO();
        Merchant merchant=merchantService.viewMerchantByID(id);
        //merchantPOJO=MerchantToPOJOConverter.convertMerchantToPOJO(merchant,merchantPOJO);
        return merchant;
    }

    @GetMapping(value ="/viewMerchant")
    public List<Merchant> viewMerchant()
    {
        List<Merchant> merchantList=merchantService.viewMerchant();
        return merchantList;
    }

    @GetMapping(value="/viewProduct")
    public List<Product> viewProduct()
    {

        List<Product> productList=productService.viewProduct();
        List<Product> productList1=new ArrayList<>();
        for (Product p:productList) {
            p.setImage(servletContext.getRealPath("/productimage")+"/"+ p.getImage());
            productList1.add(p);
        }
        return productList1;
    }

    @GetMapping(path="viewProductByMerchant/{id}")
    public List<Product> viewProductByMerchant(@PathVariable Integer id)
    {
        List<Product> productList=productService.viewProductByMerchantID(id);
        List<Product> productList1=new ArrayList<>();
        for (Product p:productList) {
            p.setImage(servletContext.getRealPath("/productimage")+"/"+ p.getImage());
            productList1.add(p);
        }
        return productList1;
    }

    @PostMapping(path="/addProduct")
    public String addAndSaveProduct(@RequestBody Product product)
    {
        String msg="";
        Product product1=null;
        try {
            if(null==product.getProductId())
            {
                msg= "Product added with productID: ";
            }
            else {
                msg= "Product Updated with productID: ";
            }
           // product.setStatus("Active");
            product1=productService.addProduct(product);
            msg+=product1.getProductId();

        }
        catch (Exception ex)
        {
            msg="something wrong please try again";
        }
        return msg;
    }

    @PostMapping(path="/addProduct2")
    public String addAndSaveProduct1(@RequestParam("image") MultipartFile multipartFile,@RequestParam("id") Integer id) {
        String msg="";
        try {

            String filename = id+multipartFile.getOriginalFilename().replaceAll(" ","_");
            String bashpath = servletContext.getRealPath("/productimage");
            FileUploadUtil.uploadFile(multipartFile.getBytes(), bashpath, filename);
            // get product detail
            Product product = productService.viewProductByID(id);
            product.setImage(filename);
            System.out.println(product.toString());
            // update product
            Product product1=productService.addProduct(product);
            msg="Product image uploaded for :"+product.getProductId();
        }catch (Exception ex)
        {
            msg=ex.getMessage();
        }

        return msg;

    }


/*
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
*/
}
