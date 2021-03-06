package com.merchant.merchant.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.merchant.merchant.bean.Merchant;
import com.merchant.merchant.bean.Product;
import com.merchant.merchant.bean.User;
import com.merchant.merchant.bean.UserPointHistory;
import com.merchant.merchant.dao.CountryRepository;
import com.merchant.merchant.dao.ProductRepository;
import com.merchant.merchant.service.*;
import com.merchant.merchant.util.FileUploadUtil;
import com.merchant.merchant.util.QRCodeUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import java.io.IOException;
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
    UserService userService;
    @Autowired
    UserPointHistoryService userPointHistoryService;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ServletContext servletContext;

    public String generalMsg="{\"status\":\"200\",\"message\":\"";
    public String endMsg="}";

    public String prepareMsg(String msg)
    {
        return generalMsg+msg+"\""+endMsg;
    }
    public String prepareMsgWithID(String msg,Integer id)
    {
        return generalMsg+msg+"\",\"Id\":"+id+endMsg;
    }


    @PostMapping(path="/loginApp")
    public ResponseEntity getLogin(@RequestParam("username") String username,@RequestParam("password") String password)
    {
        Merchant merchant=null;
         Merchant merchant1 = merchantService.getMerchantByEmail(username);
        if (null!=merchant1 && merchant1.getPassword().equals(password)){
            merchant=merchant1;
            merchant.setImage("/merchantimage/"+merchant.getImage());
            return ResponseEntity.status(200).body(merchant);
        }
        else{

            return ResponseEntity.status(200).body(prepareMsg("UserName & password doesn't match"));
        }
    }

    @PostMapping(path="/addMerchant")
    public String addAndSaveMerchant(@RequestBody Merchant merchant)
    {
        String msg="";
        try {
            if(null==merchant.getMerchantId())
            {
                msg= "Merchant added with merchantID:";
            }
            else {
                msg= "Merchant Updated with merchantID:";
            }
            merchant=merchantService.addMerchant(merchant);
            msg+=merchant.getMerchantId();
        }
        catch (Exception ex)
        {
           msg="something wrong please try again";

        }
        if(msg.contains(":"))
        {
            String[] arr=msg.split(":");
            return prepareMsgWithID(arr[0],Integer.parseInt(arr[1]));
        }
        return prepareMsg(msg);
    }

    @PostMapping(value="/viewMerchantByID")
    public ResponseEntity viewMerchantByID(@RequestParam("id") Integer id )
    {
        //MerchantPOJO merchantPOJO=new MerchantPOJO();
        Merchant merchant=merchantService.viewMerchantByID(id);
        //merchantPOJO=MerchantToPOJOConverter.convertMerchantToPOJO(merchant,merchantPOJO);
        if(null!=merchant) {
            return ResponseEntity.status(200).body(merchant);
        }
        else{
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    @GetMapping(value ="/viewMerchant")
    public ResponseEntity viewMerchant()
    {
        List<Merchant> merchantList=merchantService.viewMerchant();
        if(null!=merchantList && merchantList.size()>0) {
            return ResponseEntity.status(200).body(merchantList);
        }
        else{
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    @GetMapping(value="/viewProduct")
    public ResponseEntity viewProduct()
    {

        List<Product> productList=productService.viewProduct();
        List<Product> productList1=new ArrayList<>();
        if(null!=productList && productList.size()>0) {
            for (Product p:productList) {
            p.setImage( p.getImage());
            productList1.add(p);
            }
          return ResponseEntity.status(200).body(productList1);
        }
        else{
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    @PostMapping(path="/viewProductByMerchant")
    public ResponseEntity viewProductByMerchant(@RequestParam("id") Integer id)
    {
        List<Product> productList=productService.viewProductByMerchantID(id);
        List<Product> productList1=new ArrayList<>();

        if(null!=productList && productList.size()>0) {
            for (Product p:productList) {
                p.setImage(p.getImage());
                productList1.add(p);
            }
            return ResponseEntity.status(200).body(productList1);
        }
        else{
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @param productId
     * @return product entiry or not found msg
     */
    @PostMapping(value="/viewProductByID")
    public ResponseEntity viewProductByID(@RequestParam("productId") Integer productId )
    {

        Product product=productService.viewProductByID(productId);
        if(null!=product) {
            return ResponseEntity.status(200).body(product);
        }
        else{
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    @PostMapping(path="/addProduct")
    public String addAndSaveProduct(@RequestBody Product product)
    {
        String msg="";
        Product product1=null;
        try {
            if(null==product.getProductId())
            {
                msg= "Product added with productID:";
            }
            else {
                msg= "Product Updated with productID:";
            }
           // product.setStatus("Active");
            product1=productService.addProduct(product);
            //String QRdata=product1.getProductId()+":"+product1.getProductName()+":"+product1.getMechantID()+":"+product1.getProductPoint()+":"+product1.getDescription()+":"+product1.getOtherInfo()+":"+product1.getShowOnDay();
           /* String QRdata=product1.toString();
            String productQR=product1.getProductId()+product1.getProductName();
            QrCodeProcess(QRdata,productQR);*/
            msg+=product1.getProductId();

        }
        catch (Exception ex)
        {
            msg="something wrong please try again";
        }

        if(msg.contains(":"))
        {
            String[] arr=msg.split(":");
            return prepareMsgWithID(arr[0],Integer.parseInt(arr[1]));
        }
        return prepareMsg(msg);
    }



    /**
     *
     * @param multipartFile
     * @param id
     * @return
     */
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
            Product product1=productService.addProduct(product);
            msg="Product image uploaded for:"+product.getProductId();
        }catch (Exception ex)
        {
            msg=ex.getMessage();
        }

        if(msg.contains(":"))
        {
            String[] arr=msg.split(":");
            return prepareMsgWithID(arr[0],Integer.parseInt(arr[1]));
        }

        return prepareMsg(msg);

    }

    @PostMapping(path="/addUser")
    public String addAndSaveUser(@RequestBody User user)
    {
        String msg="";
        User user1=null;

        //check user already exist
        String contactName=user.getContactName();
        User user2=userService.getUserByContactName(contactName);
        if(null!=user2)
        {
         user.setId(user2.getId());
        }

        try {

            if(null==user.getId())
            {
                msg= "User added with userID:";
            }
            else {
                msg= "User Updated with userID:";
            }
            // product.setStatus("Active");
            user1=userService.saveUSer(user);

            msg+=user.getId();

        }
        catch (Exception ex)
        {
            msg="something wrong please try again";
        }
        if(msg.contains(":"))
        {
            String[] arr=msg.split(":");
            return prepareMsgWithID(arr[0],Integer.parseInt(arr[1]));
        }
        return prepareMsg(msg);
    }

    @PostMapping(path="/captureProductByUser")
    public String captureProductByUser(@RequestParam("userId") String userid, @RequestParam("productId") Integer productId)
    {
        String msg="";
        try {
            // get product
          Product product=productService.viewProductByID(productId);
          // get user
          User user=userService.getUserByID(userid);
          // check user and product available
          if(null!=user && null!=product)
          {
              int productPoint=product.getProductPoint();
              // chekc user avialable point is greater than product point
              if(Integer.parseInt(user.getPoint())>productPoint){
                  // calculate point
                  int remainPoint=Integer.parseInt(user.getPoint())-productPoint;
                  user.setPoint(String.valueOf(remainPoint));
                  // updtae user point
                  User user1=userService.saveUSer(user);
                  // create history object
                  UserPointHistory userPointHistory=new UserPointHistory();
                  userPointHistory.setUserId(user1.getUserId());
                  userPointHistory.setProductPoint(String.valueOf(productPoint));
                  userPointHistory.setProductId(String.valueOf(product.getProductId()));
                  // save history in storage
                  UserPointHistory userPointHistory1=userPointHistoryService.saveUserPointHistory(userPointHistory);

                  msg="Product Purchased successfully";
              }
              else
              {
                  msg="You don't have Sufficient Point balance";
              }

          }
          else {
              msg="something Wrong ! Might be product or User not found";
          }
        }
        catch (Exception ex)
        {
            msg="something wrong please try again";
        }
        return prepareMsg(msg);
    }



    /**
     *
     * @param data1
     * @param productQR
     * @throws WriterException
     * @throws IOException
     * @throws NotFoundException
     */
    public void QrCodeProcess(String data1,String productQR) throws WriterException, IOException, NotFoundException {
        // The data that the QR code will contain
        String data = data1;

        // The path where the image will get saved
        String bashpath = servletContext.getRealPath("/productqrcode/");
        String path = bashpath+"/QR"+productQR+".png";

        // Encoding charset
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                = new HashMap<EncodeHintType,
                                ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);

        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        QRCodeUtile.createQR(data, path, charset, hashMap, 200, 200);
        System.out.println("QR Code Generated!!! ");
    }



}
