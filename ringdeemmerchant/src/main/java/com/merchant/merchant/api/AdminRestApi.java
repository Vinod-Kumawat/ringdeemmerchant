package com.merchant.merchant.api;


import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.merchant.merchant.bean.*;
import com.merchant.merchant.dao.CountryRepository;
import com.merchant.merchant.dao.MerchantRepository;
import com.merchant.merchant.dao.UserPointHistoryRepository;
import com.merchant.merchant.service.*;
import com.merchant.merchant.util.FileUploadUtil;
import com.merchant.merchant.util.HistoryUtil;
import com.merchant.merchant.util.QRCodeUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/v1")
public class AdminRestApi {

    public static final String LIVE = "Live";
    public static final String DRAFT = "Draft";
    public static final String ARCH = "Archive";
    public static final String CATE_POPULAR = "Popular";
    public static final String CATE_MEAL_VEG = "Meals(Veg)";
    public static final String CATE_MEAL_NON_VEG = "Meals(Non-Veg)";
    public static final String CATE_COMBO = "Combos";
    public static final String CATE_DESSERT = "Desserts";
    public static final String CATE_NEW = "New";


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
    CategoryService categoryService;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    ServletContext servletContext;

    public String generalMsg = "{\"status\":\"200\",\"message\":\"";
    public String endMsg = "}";

    /**
     *
     * @param msg
     * @return String
     */
    public String prepareMsg(String msg) {
        return generalMsg + msg + "\"" + endMsg;
    }

    /**
     *
     * @param msg
     * @param id
     * @return String:response message with ID
     */
    public String prepareMsgWithID(String msg, Integer id) {
        return generalMsg + msg + "\",\"Id\":" + id + endMsg;
    }

    /**
     *
     * @param msg
     * @param amount
     * @return String: resonse message amount
     */
    public String prepareMsgWithAmount(String msg, int amount) {
        return generalMsg + msg + "\",\"amount\":" + amount + endMsg;
    }


    /**
     *
     * @param username
     * @param password
     * @return String message with MErchant details
     */
    @PostMapping(path = "/loginApp")
    public ResponseEntity getLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        Merchant merchant = null;
        Merchant merchant1 = merchantService.getMerchantByEmail(username);
        if (null != merchant1 && merchant1.getPassword().equals(password)) {
            merchant = merchant1;
            merchant.setImage("/merchantimage/" + merchant.getImage());
            return ResponseEntity.status(200).body(merchant);
        } else {

            return ResponseEntity.status(200).body(prepareMsg("UserName & password doesn't match"));
        }
    }

    /**
     *
     * @param merchant
     * @return
     */
    @PostMapping(path = "/addMerchant")
    public String addAndSaveMerchant(@RequestBody Merchant merchant) {
        String msg = "";
        try {
            if (null == merchant.getMerchantId()) {
                msg = "Merchant added with merchantID:";
            } else {
                msg = "Merchant Updated with merchantID:";
            }
            merchant = merchantService.addMerchant(merchant);
            msg += merchant.getMerchantId();
        } catch (Exception ex) {
            msg = "something wrong please try again";

        }
        if (msg.contains(":")) {
            String[] arr = msg.split(":");
            return prepareMsgWithID(arr[0], Integer.parseInt(arr[1]));
        }
        return prepareMsg(msg);
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/viewMerchantByID")
    public ResponseEntity viewMerchantByID(@RequestParam("id") Integer id) {
        //MerchantPOJO merchantPOJO=new MerchantPOJO();
        Merchant merchant = merchantService.viewMerchantByID(id);
        //merchantPOJO=MerchantToPOJOConverter.convertMerchantToPOJO(merchant,merchantPOJO);
        if (null != merchant) {
            return ResponseEntity.status(200).body(merchant);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/viewMerchant")
    public ResponseEntity viewMerchant() {
        List<Merchant> merchantList = merchantService.viewMerchant();
        if (null != merchantList && merchantList.size() > 0) {
            return ResponseEntity.status(200).body(merchantList);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/viewProduct")
    public ResponseEntity viewProduct() {

        List<Product> productList = productService.viewProduct();
        List<Product> productList1 = new ArrayList<>();
        if (null != productList && productList.size() > 0) {
            for (Product p : productList) {
                p.setImage(p.getImage());
                productList1.add(p);
            }
            return ResponseEntity.status(200).body(productList1);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     */
    @PostMapping(path = "/viewProductByMerchant")
    public ResponseEntity viewProductByMerchant(@RequestParam("id") Integer id, @RequestParam("status") String status) {
        if (null != status && status.equals(LIVE)) {
            return viewLiveProductByMerchant(id);
        } else if (null != status && status.equals(DRAFT)) {
            return viewDraftProductByMerchant(id);
        } else if (null != status && status.equals(ARCH)) {
            return viewArchProductByMerchant(id);
        }

        List<Product> productList = productService.viewProductByMerchantID(id);
        List<Product> productList1 = new ArrayList<>();

        if (null != productList && productList.size() > 0) {
            for (Product p : productList) {
                p.setImage(p.getImage());
                productList1.add(p);
            }
            return ResponseEntity.status(200).body(productList1);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     * @param productId
     * @return product entiry or not found msg
     */
    @PostMapping(value = "/viewProductByID")
    public ResponseEntity viewProductByID(@RequestParam("productId") Integer productId) {

        Product product = productService.viewProductByID(productId);
        if (null != product) {
            return ResponseEntity.status(200).body(product);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @param product
     * @return
     */
    @PostMapping(path = "/addProduct")
    public String addAndSaveProduct(@RequestBody Product product) {
        String msg = "";
        Product product1 = null;
        try {
            if (null == product.getProductId()) {
                msg = "Product added with productID:";
            } else {
                msg = "Product Updated with productID:";
            }
            // product.setStatus("Active");
            if (null != product.getProductId()) {
                String filename = productService.viewProductByID(product.getProductId()).getImage();
                product.setImage(filename);
            }
            product1 = productService.addProduct(product);
            //String QRdata=product1.getProductId()+":"+product1.getProductName()+":"+product1.getMechantID()+":"+product1.getProductPoint()+":"+product1.getDescription()+":"+product1.getOtherInfo()+":"+product1.getShowOnDay();
           /* String QRdata=product1.toString();
            String productQR=product1.getProductId()+product1.getProductName();
            QrCodeProcess(QRdata,productQR);*/
            msg += product1.getProductId();

        } catch (Exception ex) {
            msg = "something wrong please try again";
        }

        if (msg.contains(":")) {
            String[] arr = msg.split(":");
            return prepareMsgWithID(arr[0], Integer.parseInt(arr[1]));
        }
        return prepareMsg(msg);
    }


    /**
     * @param multipartFile
     * @param id
     * @return
     */
    @PostMapping(path = "/addProduct2")
    public String addAndSaveProduct1(@RequestParam("image") MultipartFile multipartFile, @RequestParam("id") Integer id) {
        String msg = "";
        try {
            String filename = id + multipartFile.getOriginalFilename().replaceAll(" ", "_");
            String bashpath = servletContext.getRealPath("/productimage");
            FileUploadUtil.uploadFile(multipartFile.getBytes(), bashpath, filename);
            // get product detail
            Product product = productService.viewProductByID(id);
            product.setImage(filename);
            System.out.println(product.toString());
            Product product1 = productService.addProduct(product);
            msg = "Product image uploaded for:" + product.getProductId();
        } catch (Exception ex) {
            msg = ex.getMessage();
        }

        if (msg.contains(":")) {
            String[] arr = msg.split(":");
            return prepareMsgWithID(arr[0], Integer.parseInt(arr[1]));
        }

        return prepareMsg(msg);

    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping(path = "/addUser")
    public String addAndSaveUser(@RequestBody User user) {
        String msg = "";
        User user1 = null;

        //check user already exist
        String userID = user.getUserId();
        User user2 = userService.getUserByID(userID);
        if (null != user2) {
            user.setId(user2.getId());
            user.setCreateddate(user2.getCreateddate());
        } else {
            user.setCreateddate(new Date(System.currentTimeMillis()));
        }

        try {

            if (null == user.getId()) {
                msg = "User added with userID:";
            } else {
                msg = "User Updated with userID:";
            }
            // product.setStatus("Active");
            user1 = userService.saveUSer(user);

            msg += user.getId();

        } catch (Exception ex) {
            msg = "something wrong please try again";
        }
        if (msg.contains(":")) {
            String[] arr = msg.split(":");
            return prepareMsgWithID(arr[0], Integer.parseInt(arr[1]));
        }
        return prepareMsg(msg);
    }

    /**
     *
     * @param userid
     * @param productId
     * @return
     */
    @PostMapping(path = "/captureProductByUser")
    public String captureProductByUser(@RequestParam("userId") String userid, @RequestParam("productId") Integer productId) {
        String msg = "";
        try {
            // get product
            Product product = productService.viewProductByID(productId);
            // get user
            User user = userService.getUserByID(userid);
            // check user and product available
            if (null != user && null != product && product.getStatus().equals(LIVE)) {
                int productPoint = product.getProductPoint();
                // chekc user avialable point is greater than product point
                if (Integer.parseInt(user.getPoint()) > productPoint) {
                    // calculate point
                    int remainPoint = Integer.parseInt(user.getPoint()) - productPoint;
                    user.setPoint(String.valueOf(remainPoint));
                    // updtae user point
                    User user1 = userService.saveUSer(user);

                    // update mewrchant point as well
                    long point = merchantRepository.findMerchantPoint(product.getMechantID());
                    // merchant Point is consider amount
                    point = point - product.getDiscountprice();
                    merchantRepository.updateMerchantPoint(point, product.getMechantID());

                    // create history object
                    UserPointHistory userPointHistory = HistoryUtil.createHistoryOnPurchase(user1, product);
                    // save history in storage
                    UserPointHistory userPointHistory1 = userPointHistoryService.saveUserPointHistory(userPointHistory);

                    msg = "Product Purchased successfully";
                    return prepareMsgWithAmount(msg,remainPoint);
                } else {
                    msg = "You don't have Sufficient Point balance";
                }

            } else {
                msg = "something Wrong ! Might be Product not live, Product or User not found ";
            }
        } catch (Exception ex) {
            System.err.println(ex);
            msg = "something wrong please try again" + ex.getMessage();
        }
        return prepareMsg(msg);
    }


    /**
     * @param data1
     * @param productQR
     * @throws WriterException
     * @throws IOException
     * @throws NotFoundException
     */
    public void QrCodeProcess(String data1, String productQR) throws WriterException, IOException, NotFoundException {
        // The data that the QR code will contain
        String data = data1;

        // The path where the image will get saved
        String bashpath = servletContext.getRealPath("/productqrcode/");
        String path = bashpath + "/QR" + productQR + ".png";

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


    /**
     *
     * @param id
     * @return
     */
    @PostMapping(path = "/viewLiveProductByMerchant")
    public ResponseEntity viewLiveProductByMerchant(@RequestParam("id") Integer id) {
        List<Product> productList = productService.viewProductByMerchantID(id);
        if(null==productList || productList.size()<1)
        {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }

        List<Product> productList1 = new ArrayList<>();
        // filter live
        productList = productList.stream().filter(p -> (null != p.getStatus() && p.getStatus().equals(LIVE))).collect(Collectors.toList());

        // filter by end date should not be less to today date
        productList=productList.stream().filter(p->(null!=p.getEnddate() && (p.getEnddate().compareTo(new Date(System.currentTimeMillis())))>0)).collect(Collectors.toList());

        if (null != productList && productList.size() > 0) {
            for (Product p : productList) {
                p.setImage(p.getImage());
                productList1.add(p);
            }
            return ResponseEntity.status(200).body(productList1);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping(path = "/viewDraftProductByMerchant")
    public ResponseEntity viewDraftProductByMerchant(@RequestParam("id") Integer id) {
        List<Product> productList = productService.viewProductByMerchantID(id);
        if(null==productList || productList.size()<1)
        {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
        List<Product> productList1 = new ArrayList<>();
        productList = productList.stream().filter(p -> (null != p.getStatus() && p.getStatus().equals(DRAFT))).collect(Collectors.toList());

        if (null != productList && productList.size() > 0) {
            for (Product p : productList) {
                p.setImage(p.getImage());
                productList1.add(p);
            }

            return ResponseEntity.status(200).body(productList1);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     * Return already purchased product
     *
     * @param id
     * @return
     */
    @PostMapping(path = "/viewArchProductByMerchant")
    public ResponseEntity viewArchProductByMerchant(@RequestParam("id") Integer id) {
        // get all Product purchased earlier
        List<UserPointHistory> userPointHistoryList = userPointHistoryService.findByMerchantID(id);
        if(null==userPointHistoryList || userPointHistoryList.size()<1)
        {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
        // get all product id from user purchased
        Set<Integer> integerSet = userPointHistoryList.stream().map(u -> Integer.valueOf(u.getProductId())).collect(Collectors.toSet());
        // view product by id set
        List<Product> productList = productService.viewProductByIDIn(integerSet);

        if (null != productList && productList.size() > 0) {
            return ResponseEntity.status(200).body(productList);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping(path = "/viewArchProductByUser")
    public ResponseEntity viewArchProductByUser(@RequestParam("id") String id) {
        // get all Product purchased earlier
        List<UserPointHistory> userPointHistoryList = userPointHistoryService.findByUserId(id);
        if(null==userPointHistoryList || userPointHistoryList.size()<1)
        {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
        // get all product id from user purchased
        Set<Integer> integerSet = userPointHistoryList.stream().map(u -> Integer.valueOf(u.getProductId())).collect(Collectors.toSet());
        // get product list
        List<Product> productList = productService.viewProductByIDIn(integerSet);
        if(null!=productList && productList.size()>0)    {
            return ResponseEntity.status(200).body(productList);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @param country
     * @return
     */
    @PostMapping(path = "/viewMerchantByCountry")
    public ResponseEntity viewMerchantByCountry(@RequestParam("country") String country) {
        /*if (null == country) {
            return ResponseEntity.status(200).body(prepareMsg("Country must not null"));
        }
*/      List<Merchant> merchantList = merchantService.viewMerchant();
        if (null != merchantList && merchantList.size() > 0) {
            List<Merchant> merchantList1 = merchantList.stream().filter(m -> (null != m.getCountry() && m.getCountry().equalsIgnoreCase(country))).collect(Collectors.toList());
            if(null != merchantList && merchantList1.size()>0) {
                return ResponseEntity.status(200).body(merchantList1);
            }
            else {
                return ResponseEntity.status(200).body(prepareMsg("No Record found"));
            }
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping(path = "/viewProductCategoryByMerchant")
    public ResponseEntity viewProductCategoryByMerchant(@RequestParam("id") Integer id){
        if(null!=id && id>0){
            List<Category> categoryList=categoryService.findCategoryByMerchantId(id);
            if(null!=categoryList && categoryList.size()>0) {
                return ResponseEntity.status(200).body(categoryList);
            }
            else
            {
                return ResponseEntity.status(200).body(prepareMsg("No Record Found"));
            }
        } else {
            return ResponseEntity.status(200).body(prepareMsg("Please Enter Valid Merchant Id"));
        }
    }

    /**
     *
     * @param id
     * @param category
     * @return
     */
    @PostMapping(path = "/viewProductByMerchantAndCategory")
    public ResponseEntity viewProductByMerchantAndCategory(@RequestParam("id") Integer id, @RequestParam("category") String category) {

        List<Product> productList = productService.viewProductByMerchantID(id);
        List<Product> productList1 = new ArrayList<>();
      /*
        if(null!=category && category.equalsIgnoreCase(CATE_COMBO)){
            productList1=productList.stream().filter(p->(null!=p && p.getCategory().equalsIgnoreCase(CATE_COMBO))).collect(Collectors.toList());
        }
        else if(null!=category && category.equalsIgnoreCase(CATE_DESSERT)){
            productList1=productList.stream().filter(p->(null!=p && p.getCategory().equalsIgnoreCase(CATE_DESSERT))).collect(Collectors.toList());
        }
        else if(null!=category && category.equalsIgnoreCase(CATE_NEW)){
            productList1=productList.stream().filter(p->(null!=p && p.getCategory().equalsIgnoreCase(CATE_NEW))).collect(Collectors.toList());
        }
        else if(null!=category && category.equalsIgnoreCase(CATE_MEAL_VEG)){
            productList1=productList.stream().filter(p->(null!=p && p.getCategory().equalsIgnoreCase(CATE_MEAL_VEG))).collect(Collectors.toList());
        }
        else if(null!=category && category.equalsIgnoreCase(CATE_POPULAR)){
            productList1=productList.stream().filter(p->(null!=p && p.getCategory().equalsIgnoreCase(CATE_POPULAR))).collect(Collectors.toList());
        }
        else if(null!=category && category.equalsIgnoreCase(CATE_MEAL_NON_VEG)){
            productList1=productList.stream().filter(p->(null!=p && p.getCategory().equalsIgnoreCase(CATE_MEAL_NON_VEG))).collect(Collectors.toList());
        }
        */
        if(null!=category) {
            productList1 = productList.stream().filter(p -> (null != p && p.getCategory().equalsIgnoreCase(category))).collect(Collectors.toList());
        }

        if (null != productList1 && productList1.size() > 0) {
            return ResponseEntity.status(200).body(productList1);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }

    /**
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/viewUserByID")
    public ResponseEntity viewUserByID(@RequestParam("userId") String userId) {

        User product = userService.getUserByID(userId);
        if (null != product) {
            return ResponseEntity.status(200).body(product);
        } else {
            return ResponseEntity.status(200).body(prepareMsg("No Record found"));
        }
    }


}
