package com.example.sell.controller;

import com.example.sell.po.ProduceCategory;
import com.example.sell.po.ProduceInfo;
import com.example.sell.service.ProduceCategoryService;
import com.example.sell.service.ProduceInfoService;
import com.example.sell.utils.ResultUtil;
import com.example.sell.vo.ProduceInfoVo;
import com.example.sell.vo.ProduceVo;
import com.example.sell.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Api(value = "商品")
@RequestMapping(value = "/produceInfo")
public class ProduceInfoController {
    @Autowired
    ProduceInfoService produceInfoService;
    @Autowired
    ProduceCategoryService produceCategoryService;

    @ApiOperation(value = "查询商品列表")
    @RequestMapping(value = "/getProduceList",method = RequestMethod.GET)
    @ResponseBody
    public ResultVo getProduceList(){
        //查询所有上架商品
        List<ProduceInfo> list = produceInfoService.findUpAll();
        //查询类目
        List<Integer> categoryListType = list.stream().map(e->e.getCategoryType()).collect(Collectors.toList());
        List<ProduceCategory> categoryList = produceCategoryService.findByCategoryTypeIn(categoryListType);
        //数据拼装
        List<ProduceVo> produceVoList = new ArrayList <>();
        for (ProduceCategory produceCategory:categoryList){
            ProduceVo produceVo = new ProduceVo();
            produceVo.setCategoryType(produceCategory.getCategoryType());
            produceVo.setCategoryName(produceCategory.getCategoryName());

            List<ProduceInfoVo> produceInfoVoList = new ArrayList <>();
            for (ProduceInfo produceInfo:list){
                if (produceInfo.getCategoryType().equals(produceCategory.getCategoryType())){
                    ProduceInfoVo produceInfoVo = new ProduceInfoVo();
                    //把一个对象里的之copy到另一个对象
                    BeanUtils.copyProperties(produceInfo,produceInfoVo);
                    //produceInfoVo.setProduceId(produceInfo.getProduceId());
                    produceInfoVoList.add(produceInfoVo);
                }
            }
            produceVo.setProduceInfoVo(produceInfoVoList);
            produceVoList.add(produceVo);
        }
        return ResultUtil.success(produceVoList);
    }
    @ApiOperation(value = "新增商品---后台")
    @RequestMapping(value = "/addProduce",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo addProduce(@RequestBody ProduceInfo produceInfo){
        produceInfoService.save(produceInfo);
        return ResultUtil.success();
    }
    @ApiOperation(value = "查询商品---后台")
    @RequestMapping(value = "/getProduceInfoList",method = RequestMethod.GET)
    @ResponseBody
    public ResultVo getProduceInfoList(
            @ApiParam(name = "num",value = "开始页") @RequestParam(required = true) Integer num,
            @ApiParam(name ="size",value = "每页显示条数") @RequestParam(required = true) Integer size){
        PageRequest pageRequest = new PageRequest(num,size);
        Page<ProduceInfo> produceInfoPage = produceInfoService.findAll(pageRequest);
        return ResultUtil.success(produceInfoPage);
    }
}
