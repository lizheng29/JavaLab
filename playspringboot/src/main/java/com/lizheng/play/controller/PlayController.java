package com.lizheng.play.controller;

import com.lizheng.play.model.Person;
import com.lizheng.play.respository.PersonRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG
//
//         本模块已经经过开光处理，绝无可能再产生bug
//
//=====================================================

@Controller
@RequestMapping(value = "/")
@Validated
public class PlayController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping(value = "/")
    @Transactional(readOnly = true)
    public String getPersons(Model model) {

        Person empty = new Person();
        // 使用自定义语句方式查询并排序
        //List<Person> persons= personRepository.findAllOrderByAgeAsc();
        // 使用原生sql查询并排序
        //List<Person> persons = personRepository.myfindAll();
        // 使用Sort对象排序(单个或多个，但是同种排序)
        //Sort sort = new Sort(Sort.Direction.ASC,"age","length");
        //使用Order数组排序(多个)
        Order[] orders = {new Order(Sort.Direction.ASC, "age"), new Order(Sort.Direction.ASC, "length")};
        Sort sort = new Sort(orders);
        List<Person> persons = personRepository.findAll(sort);
        // 查找所有年龄小于50岁的
        //List<Person> persons = personRepository.findByAgeLessThanEqual(50);
        // 查询分页对象并排序
        //Page<Person> page = personRepository.findAll(new PageRequest(0,10,Direction.ASC,"age","length"));
        // 使用流式查询
        /*try(Stream<Person> personStream = personRepository.streamAll()){
            personStream.forEach(person -> System.out.println(person.getName()));
			personStream.close();
		}*/

        model.addAttribute("random", Math.random());
        model.addAttribute("persons", persons);
        model.addAttribute("emptyPerson", empty);
        return "MyHtml";

    }


    @PostMapping(value = "/add")
    @RequiresRoles("admin")
    //@RequiresPermissions("添加666")
    public String addPerson(HttpServletResponse response, Person emptyPerson) {
        emptyPerson.setAge((int) (Math.random() * 100));
        emptyPerson.setLength((int) (Math.random() * 10 + 10));

		/*Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole("admin")){
			System.out.println("has role admin");
		}
		if(subject.isPermitted("添加")){
			System.out.println("has permition 添加");
		}*/
        personRepository.save(emptyPerson);
		/*try {
			response.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        return "redirect:/";
    }

    @GetMapping(value = "/delete/{id}")
    public void deletePerson(HttpServletResponse response, @PathVariable("id") String id) {
        personRepository.delete(Integer.parseInt(id));
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/403")
    public String unAuth() {
        return "403";
    }

    /**
     * ajax登录请求
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.GET)
    //@ResponseBody
    public String submitLogin(String username, String password, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {

            AuthenticationToken token = new UsernamePasswordToken("lizheng", "lizheng");
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");

        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return "redirect:/";
    }


    @PostMapping("/validate")
    @ResponseBody
    //@Validated
    public String validate(@Valid @RequestBody Person person, BindingResult validateResult, @Size(min = 0, max = 3,
            message = "{param.max}") @RequestParam(required = false) String param) {

        //int i = 10/0;

        if (validateResult.hasErrors()) {
            return validateResult.getFieldError().getDefaultMessage();
        }
        return "没毛病";
    }

    @PostMapping("/asr")
    @ResponseBody
    public String testAsr(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result";
    }


}
