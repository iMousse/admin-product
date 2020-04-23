package com.admin.controller;

import com.admin.domain.Product;
import com.admin.service.ProductService;
import com.admin.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/product")
@SessionAttributes(value = "productId")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = false, defaultValue = "4") Integer size) {

        ModelAndView mv = new ModelAndView();

        List<Product> products = productService.getAll(page, size);

        PageInfo<Product> pageInfo = new PageInfo<>(products);

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");

        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(Long productId) {

        ModelAndView mv = new ModelAndView();


        Product product = productService.getById(productId);

        mv.addObject("product", product);
        mv.setViewName("product-show");

        return mv;

    }
    @RequestMapping("/save.do")
    public String save(Product product) {

        productService.insert(product);


        return "redirect:findAll.do";
    }

    @RequestMapping("/updateShow.do")
    public ModelAndView updateShow(Long productId) {

        ModelAndView mv = new ModelAndView();

        Product product = productService.getById(productId);

        mv.addObject("product", product);
        mv.setViewName("product-update");

        return mv;
    }
    @RequestMapping("/update.do")
    public String update(Product product) {

        productService.update(product);

        return "redirect:findAll.do";
    }
    @RequestMapping("/delete.do")
    public String delete(Long productId) {

        productService.delete(productId);


        return "redirect:findAll.do";
    }


    @RequestMapping("/upload.do")
    public String upload(HttpServletRequest request, MultipartFile upload, Long productId) throws Exception {
        //获取文件路径
        String path = request.getSession().getServletContext().getRealPath("/uploads/");

        //创建文件对象
        File file = new File(path);

        //如果文件不存在则创建文件夹
        if (!file.exists()) { file.mkdirs(); }

        String productContractUrl = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        productContractUrl = uuid + "_" + productContractUrl;

        upload.transferTo(new File(path,productContractUrl));

        productService.updateSelective(productId,productContractUrl);

        return "redirect:findAll.do";
    }




    @RequestMapping("/generator.do")
    public ModelAndView generator(Long productId) {

        Product product = productService.getById(productId);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("product-orders");
        mv.addObject("product", product);

        return mv;
    }

}
