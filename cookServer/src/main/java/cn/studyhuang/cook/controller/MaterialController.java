package cn.studyhuang.cook.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Material;
import cn.studyhuang.cook.pojo.Step;
import cn.studyhuang.cook.service.MaterialService;

/**
 * 食材控制层
 * @author huang
 *
 * 2018年1月7日 下午4:42:45
 */
@Controller
@RequestMapping("material")
public class MaterialController {
	@Resource
	private MaterialService materialService;
	@RequestMapping("save")
	@ResponseBody
	public CallResult save() {
		CallResult callResult =new CallResult();
		
		return callResult;
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public CallResult listMaterials(Integer page,Integer limit) {
		CallResult callResult =new CallResult();
		if (page == null) {
			page =1;
		}
		if (limit == null) {
			limit =5;
		}
		
		Integer row = (page-1) * limit;
		PageHelper.startPage(page,limit);
		List<Material> materials = materialService.listMaterials(null,null);
		PageInfo pageInfo = new PageInfo<>(materials);
		callResult.setData(pageInfo);
		//callResult.setData(materials);
		return callResult;
	}
	
	@RequestMapping("get/{id}")
	@ResponseBody
	public CallResult getMaterials(@PathVariable Long id) {
		CallResult callResult =new CallResult();
		Material material = materialService.get(id);
		callResult.setData(material);
		return callResult;
	}
	
	@RequestMapping("getByMid/{mid}")
	@ResponseBody
	public CallResult getByMId(@PathVariable Long mid) {
		CallResult callResult =new CallResult();
		System.out.println("=========="+mid);
		List<Material> materials = materialService.findMaterialsByMid(mid);
		if (CollectionUtils.isEmpty(materials)) {
			callResult.fail("该菜谱没有上传食材列表");
		}
	//	System.out.println(materials.get(2).getMaterialName());
		callResult.setData(materials);
		return callResult;
	}
	
	@RequestMapping("delete/{id}")
	@ResponseBody
	public CallResult deleteById(@PathVariable Long id) {
		CallResult callResult = new CallResult();
		materialService.delete(id);
		callResult.setMsg("删除成功");
		return callResult;
	}
	

}
